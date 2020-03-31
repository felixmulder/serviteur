package Serviteur.API

case class :>[+A, +B]()

final case class PathParam[A]()
final case class RequestBody[A]()

final case class GET()
final case class CREATED[ContentTypes, ResponseBody]()
final case class OK[ContentTypes, ResponseBody]()
final case class JSON()

type Handler[API] = Handler.Go[API]

private[API] object Handler {
  type Go[API] = API match
    case prev :> last =>
      HandlerAux[prev, HandlerSingle[last]]
    case _ =>
      HandlerSingle[API]

  type HandlerSingle[X] = X match
    case CREATED[_, response] =>
      IO[response]
    case OK[_, response] =>
      IO[response]
    case PathParam[param] =>
      param
    case RequestBody[body] =>
      body

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
}

class IO[A](unsafePerformIO: () => A)

object IO:
  def apply[A](a: => A) = new IO(() => a)
