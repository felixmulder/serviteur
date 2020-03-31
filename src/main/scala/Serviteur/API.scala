package Serviteur.API

/** A binder for APIs, used as a separator same as Servant's operator */
case class :>[+A, +B]()

/** A type representing a path parameter */
final case class PathParam[A]()

/** A type representing a request body */
final case class RequestBody[A]()

/** A type representing that the endpoint is a `GET` */
final case class GET()

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
 *  type GetPony = "pony" :> PathParam[PonyId] :> OK[JSON, Option[Pony]]
 *
 *  def getPony: Handler[GetPony] =
 *    (id: PonyId) => IO {
 *      db.getPony(id)
 *    }
 *  ```
 */
type Handler[API] = Handler.Go[API]

private[API] object Handler:

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
    case RequestBody[body] =>
      body


  // Reduce `API` into a function, keeping `Next` as the result type
  type HandlerAux[API, Next] = API match
    case prev :> last =>
      last match
        case (String & Singleton) =>
          HandlerAux[prev, Next]
        case PathParam[param] =>
          HandlerAux[prev, param => Next]
        case RequestBody[body] =>
          HandlerAux[prev, body => Next]
    case GET =>
      Next
    case (String & Singleton) =>
      Next
    case PathParam[param] =>
      param => Next
    case RequestBody[body] =>
      body => Next

class IO[A](unsafePerformIO: () => A)

object IO:
  def apply[A](a: => A) = new IO(() => a)
