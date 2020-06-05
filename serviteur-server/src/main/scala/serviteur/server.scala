package serviteur.server

import serviteur.api._
import serviteur.http._
import serviteur.http.status._
import cats.effect.IO
import cats.Functor
import cats.implicits._
import scala.language.implicitConversions

def serveAPI[F[_], API](
  handler: Handler[F, API],
  req: Request,
)(using H: HasServer[F, Handler[F, API]]): F[Option[Response]] =
  H.handleRequest(handler, req)

trait ToResponseCode[A]:
  def toResponseCode: Status

given [A, B] as ToResponseCode[CREATED[A, B]]:
  def toResponseCode = Status.created

/** Render the body */
trait MimeRender[ContentType, A]:
  def mimeRender(a: A): ResponseBody

given [A](using ToJSON: ToJSON[A]) as MimeRender[JSON, A]:
  def mimeRender(a: A) =
    ResponseBody.StringBody(ToJSON.encode(a))

trait ToResponse[F[_], A]:
  def toResponse(a: A): F[Response]

given [F[_], CT, A](
  using C: ToResponseCode[CREATED[CT, A]],
        M: MimeRender[CT, A],
        F: Functor[F]
) as ToResponse[F, Handler[F, CREATED[CT, A]]]:
  def toResponse(fa: F[A]) =
    fa.map(a => Response.Response(
      headers = Map.empty,
      body = M.mimeRender(a),
      status = C.toResponseCode
    ))

trait HasServer[F[_], ApiHandler]:
  def handleRequest(handler: ApiHandler, req: Request): F[Option[Response]]

given [F[_], A](using TR: ToResponse[F, F[A]], F: Functor[F]) as HasServer[F, F[A]]:
  def handleRequest(fa: F[A], req: Request): F[Option[Response]] =
    TR.toResponse(fa).map(Some(_))

given [F[_], A, B](using HS: HasServer[F, F[B]], TR: ToResponse[F, F[B]]) as HasServer[F, A => F[B]]:
  def handleRequest(f: A => F[B], req: Request): F[Option[Response]] = ???

// FIXME Move to test module
private object simple:
  val toRes: ToResponse[IO, Handler[IO, CREATED[JSON, Int]]] =
    summon[ToResponse[IO, Handler[IO, CREATED[JSON, Int]]]]

  val hasServerCreated: HasServer[IO, Handler[IO, CREATED[JSON, Int]]] =
    summon[HasServer[IO, Handler[IO, CREATED[JSON, Int]]]]


// FIXME Remove me!
trait ToJSON[A]:
  def encode(a: A): String

given [A] as ToJSON[A]:
  def encode(a: A) = a.toString

