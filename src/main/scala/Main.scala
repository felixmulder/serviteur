import java.util.UUID

object Main:

  case class :>[+A, +B]()

  final case class PathParam[A]()
  final case class RequestBody[A]()

  final case class GET()
  final case class CREATED[ContentTypes, ResponseBody]()
  final case class OK[ContentTypes, ResponseBody]()
  final case class JSON()

  type Handler[API] = API match
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

  def simplest: IO[SomeResponse] = IO(SomeResponse())

  def testSimplest0: Handler[CREATED[JSON, SomeResponse]] = simplest

  def testSimplest1: Handler["v1" :> CREATED[JSON, SomeResponse]] = simplest


  def simple: UUID => IO[SomeResponse] =
    _ => IO(SomeResponse())

  def test0: Handler[PathParam[UUID] :> CREATED[JSON, SomeResponse]] = simple

  def test1: Handler["v1" :> PathParam[UUID] :> CREATED[JSON, SomeResponse]] = simple

  def test2: Handler[GET :> "v1" :> PathParam[UUID] :> CREATED[JSON, SomeResponse]] = test3

  def test3: Handler[((GET :> "v1") :> PathParam[UUID]) :> CREATED[JSON, SomeResponse]] = simple

  type CreateTransaction =
    GET
      :> "v1"
      :> "transactions"
      :> PathParam[UUID]
      :> "other"
      :> RequestBody[SomeRequestBody]
      :> CREATED[JSON, SomeResponse]

  def createTransaction: Handler[CreateTransaction] =
    (uuid: UUID) => (body: SomeRequestBody) => IO(SomeResponse())

  // Request response
  case class SomeRequestBody()
  case class SomeResponse()

  class IO[A](unsafePerformIO: () => A)
  object IO {
    def apply[A](a: => A) = new IO(() => a)
  }

  def main(args: Array[String]): Unit =
    if true then
      println(msg)
    else
      println(msg)


  def msg =
    "I was compiled by dotty :)"
