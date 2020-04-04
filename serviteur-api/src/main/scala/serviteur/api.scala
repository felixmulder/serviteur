package serviteur.api

import cats.effect.IO

/** A binder for APIs, used as a separator same as Servant's operator */
final class :>[A, B](val first: A, val rest: Handler[B])

/** Combinator for combining different endpoint handlers into one API */
final case class :<|>[A, B](left: Handler[A], right: Handler[B])

/** A type representing a header */
final case class Header[Name, A]()

/** A type representing a path parameter */
final case class PathParam[A]()

/** A type representing a request body */
final case class RequestBody[A]()

/** A type representing that the endpoint is a `GET` */
final case class GET()

/** A type representing that the endpoint is a `HEAD` */
final case class HEAD()

/** A type representing that the endpoint is a `POST` */
final case class POST()

/** A type representing that the endpoint is a `PUT` */
final case class PUT()

/** A type representing that the endpoint is a `DELETE` */
final case class DELETE()

/** A type representing that the endpoint is a `CONNECT` */
final case class CONNECT()

/** A type representing that the endpoint is a `OPTIONS` */
final case class OPTIONS()

/** A type representing that the endpoint is a `TRACE` */
final case class TRACE()

/** A type representing that the endpoint is a `PATCH` */
final case class PATCH()

/** A type representing that the CREATED 201 status will be returned along with
 *  the `ContentType` and `ResponseBody`
 */
final case class CREATED[ContentTypes, ResponseBody]()

/** A type representing that the OK 200 status will be returned along with
 *  the `ContentType` and `ResponseBody`
 */
final case class OK[ContentTypes, ResponseBody]()

/** A type representing the `application/json` content type header */
final case class JSON()

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
type Handler[API] = handler.Go[API]

private[api] object handler:

  // Starter for Handler reduction:
  type Go[API] = API match
    case prev :> last =>
      HandlerAux[prev, HandlerSingle[last]]
    case _ =>
      HandlerSingle[API]

  // Reduce an `X` known to be a non `:>` type
  type HandlerSingle[X] = X match
    case CREATED[_, response] =>
      IO[response]
    case OK[_, response] =>
      IO[response]
    case PathParam[param] =>
      param
    case Header[_, header] =>
      header
    case RequestBody[body] =>
      body


  // Reduce `API` into a function, keeping `Next` as the result type
  type HandlerAux[API, Next] = API match
    case prev :> last =>
      last match
        case (String & Singleton) =>
          HandlerAux[prev, Next]
        case Header[_, header] =>
          HandlerAux[prev, header => Next]
        case PathParam[param] =>
          HandlerAux[prev, param => Next]
        case RequestBody[body] =>
          HandlerAux[prev, body => Next]
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
