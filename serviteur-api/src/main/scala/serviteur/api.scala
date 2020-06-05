package serviteur.api

import cats.effect.IO

/** A binder for APIs, used as a separator same as Servant's operator */
final class :>[A, B](val first: A, val rest: B)

/** Combinator for combining different endpoint handlers into one API */
final case class :<|>[A, B](left: A, right: B)

/** A type representing a header */
final case class Header[Name, A]()

/** A type representing a path parameter */
final case class PathParam[A](param: A)

/** A type representing a request body */
final case class RequestBody[A]()

/** A type representing that the endpoint is a `GET` */
enum GET:
  case GET

/** A type representing that the endpoint is a `HEAD` */
enum HEAD:
  case HEAD

/** A type representing that the endpoint is a `POST` */
enum POST:
  case POST

/** A type representing that the endpoint is a `PUT` */
enum PUT:
  case PUT

/** A type representing that the endpoint is a `DELETE` */
enum DELETE:
  case DELETE

/** A type representing that the endpoint is a `CONNECT` */
enum CONNECT:
  case CONNECT

/** A type representing that the endpoint is a `OPTIONS` */
enum OPTIONS:
  case OPTIONS

/** A type representing that the endpoint is a `TRACE` */
enum TRACE:
  case TRACE

/** A type representing that the endpoint is a `PATCH` */
enum PATCH:
  case PATCH

/** A type representing that the CREATED 201 status will be returned along with
 *  the `ContentType` and `ResponseBody`
 */
final case class CREATED[ContentTypes, ResponseBody]()

/** A type representing that the OK 200 status will be returned along with
 *  the `ContentType` and `ResponseBody`
 */
final case class OK[ContentTypes, ResponseBody]()

/** A type representing the `application/json` content type header */
enum JSON:
  case JSON

/** A type function that reduces an `API` into a function
 *
 *  As an example:
 *
 *  ```
 *  type GetPony =
 *    GET :> "pony" :> PathParam[PonyId] :> OK[JSON, Option[Pony]]
 *
 *  def getPony: Handler[GetPony] =
 *    (id: PonyId) => IO {
 *      db.getPony(id)
 *    }
 *  ```
 */
type Handler[F[_],  API] = API match
  case a :<|> b =>
    Handler[F, a] :<|> Handler[F, b]
  case prev :> last =>
    handler.HandlerAux[F, prev, handler.HandlerSingle[F, last]]
  case _ =>
    handler.HandlerSingle[F, API]

private[api] object handler:

  // Reduce an `X` known to be a non `:>` type
  type HandlerSingle[F[_], X] = X match
    case CREATED[_, response] =>
      F[response]
    case OK[_, response] =>
      F[response]
    case PathParam[param] =>
      param
    case Header[_, header] =>
      header
    case RequestBody[body] =>
      body


  // Reduce `API` into a function, keeping `Next` as the result type
  type HandlerAux[F[_], API, Next] = API match
    case prev :> last =>
      last match
        case (String & Singleton) =>
          HandlerAux[F, prev, Next]
        case Header[_, header] =>
          HandlerAux[F, prev, header => Next]
        case PathParam[param] =>
          HandlerAux[F, prev, param => Next]
        case RequestBody[body] =>
          HandlerAux[F, prev, body => Next]
    case PathParam[param] =>
      param => Next
    case Header[_, header] =>
      header => Next
    case RequestBody[body] =>
      body => Next
    case GET => Next
    case HEAD => Next
    case POST => Next
    case PUT => Next
    case DELETE => Next
    case CONNECT => Next
    case OPTIONS => Next
    case (String & Singleton) => Next
