package serviteur.server

import cats.effect.IO
import serviteur.api._

def serveAPI[API](api: Handler[API], req: Request)(using H: HandleRequest[Handler[API]]): IO[Response] =
  H.handleRequest(req, api) getOrElse
    IO.pure(Response(Nil, Body.EmptyBody, Status.NOTFOUND))

trait HandleRequest[API]:
  def handleRequest(req: Request, handler: API): Option[IO[Response]]

given [L, R](using L: HandleRequest[Handler[L]], R: HandleRequest[Handler[R]]) as HandleRequest[L :<|> R]:
  def handleRequest(req: Request, handler: L :<|> R): Option[IO[Response]] =
    L.handleRequest(req, handler.left) orElse
    R.handleRequest(req, handler.right)

given [API](using API: HandleRequest[Handler[API]]) as HandleRequest[GET :/ API]:
  def handleRequest(req: Request, h: GET :/ API): Option[IO[Response]] =
    if req.method == RequestMethod.GET then
      API.handleRequest(req, h.rest)
    else
      None

given [C, R](using R: MimeRender[C, R]) as HandleRequest[Handler[CREATED[C, R]]]:
  def handleRequest(req: Request, h: Handler[CREATED[C, R]]) =
    Some(h.map(R.toResponse(Status.CREATED, _)))

trait MimeRender[C, A]:
  def toResponse(status: Status, a: A): Response

given MimeRender[JSON, Unit]:
  def toResponse(status: Status, a: Unit) = Response(
    List(Header("Content-Type", "application/json")),
    Body.EmptyBody,
    status,
  )

final case class Request(
  method: RequestMethod,
  pathParts: List[String],
  headers: List[Header],
  body: Body,
)

final case class Header(name: String, value: String)

enum Body:
  case StringBody(value: String)
  case EmptyBody

final case class Response(
  headers: List[Header],
  body: Body,
  status: Status,
)

enum RequestMethod:
  case GET
  case HEAD
  case PUT
  case DELETE
  case CONNECT
  case OPTIONS
  case TRACE
  case PATCH

enum Status(code: Int):
  case OK       extends Status(200)
  case CREATED  extends Status(201)
  case NOTFOUND extends Status(404)

// FIXME:
//  These should be deleted and the lines in:
//    `/serviteur-sever/src/test/scala/serviteur/server/compilation.scala`
//  uncommented.
private val exampleRequest = Request(
  RequestMethod.GET,
  Nil,
  Nil,
  Body.EmptyBody,
)

private val exampleHandler =
  summon[HandleRequest[Handler[CREATED[JSON, Unit]]]]
    .handleRequest(exampleRequest, handler)

private val handler: Handler[CREATED[JSON, Unit]] = IO.unit
