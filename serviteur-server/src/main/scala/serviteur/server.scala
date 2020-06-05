package serviteur.server

import cats.effect.IO
import cats.implicits._
import cats.{Applicative, Functor}
import java.net.URLDecoder
import scala.language.implicitConversions
import serviteur.api._
import serviteur.http._
import serviteur.http.status._

def serveAPI[F[_], API](
  handler: Handler[F, API],
  req: Request,
)(using H: HasServer[F, Handler[F, API]]): F[Option[Response]] =
  H.handleRequest(handler, req)

trait FromRequest[A]:
  def fromRequest(req: Request): Either[String, A]

trait FromHttpApiData[A]:
  def parsePathParam(pathPart: String): Either[String, A]

given FromHttpApiData[String]:
  def parsePathParam(pathPart: String) =
    Right(URLDecoder.decode(pathPart, "UTF-8"))

given [A, B](using FromHttpApiData: FromHttpApiData[A])
as FromRequest[PathParam[A]]:
  def fromRequest(req: Request) =
    req.path match
      case x :: _ => FromHttpApiData.parsePathParam(x).map(PathParam(_))
      case Nil => Left("Couldn't parse path param from empty path")

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

given [F[_], A, B](
  using H: HasServer[F, F[B]],
        R: ToResponse[F, F[B]],
        P: FromRequest[PathParam[A]],
        F: Applicative[F]
)
as HasServer[F, Handler[F, PathParam[A] :> CREATED[JSON, B]]]:
  def handleRequest(f: A => F[B], req: Request): F[Option[Response]] =
    P.fromRequest(req) match
      case Right(a) =>
        H.handleRequest(f(a.param), req.copy(path = req.path.tail))
      case Left(_) =>
        F.pure(None) // TODO: should this be reported?

// FIXME Move to test module
private object simple:
  summon[ToResponse[IO, Handler[IO, CREATED[JSON, Int]]]]
  summon[HasServer[IO, Handler[IO, CREATED[JSON, Int]]]]
  summon[HasServer[IO, Handler[IO, PathParam[String] :> CREATED[JSON, Int]]]]
  summon[HasServer[IO, Handler[IO, GET :> PathParam[String] :> CREATED[JSON, Int]]]]

  // Hmm - this shows the issue of not carrying the type along witht the resolution
  //
  // In this case the `HasServer` should not be based on handler, but keep an
  // internal type that reduces. That way we can carry the types and properly
  // match on path parts like "boats"
  summon[HasServer[IO, Handler[IO, GET :> "boats" :> PathParam[String] :> CREATED[JSON, Int]]]]


// FIXME Remove me!
trait ToJSON[A]:
  def encode(a: A): String

given [A] as ToJSON[A]:
  def encode(a: A) = a.toString

