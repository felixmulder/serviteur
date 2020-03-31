import java.util.UUID

object Main:

  case class :>[A, B]()

  final case class PathParam[A]()
  final case class RequestBody[A]()

  final case class GET()
  final case class CREATED[ContentTypes, ResponseBody]()
  final case class OK[ContentTypes, ResponseBody]()
  final case class JSON()

  type CreateTransaction =
    GET
      :> "v1"
      :> "transactions"
      :> PathParam[UUID]
      :> "other"
      :> RequestBody[SomeRequestBody]
      :> CREATED[JSON, SomeResponse]

  type Handler[API] = API match
    case CREATED[_, response] =>
      IO[response]

    case PathParam[param] :> rest =>
      param => Handler[rest]

    case RequestBody[param] :> rest =>
      param => Handler[rest]

    case String :> rest =>
      Handler[rest]

  type Foo[X] = X match
    case String => Int

  def foo[Foo[String]] = "HAH"

  //def simplest: IO[SomeResponse] = IO(SomeResponse())

  //def testSimplest0: Handler[CREATED[JSON, SomeResponse]] = simplest

  //def testSimplest1: Handler["v1" :> CREATED[JSON, SomeResponse]] = simplest

  //def simple: UUID => IO[SomeResponse] =
  //  _ => IO(SomeResponse())

  //def test0: Handler[PathParam[UUID] :> CREATED[JSON, SomeResponse]] = simple

  //def test1: Handler["v1" :> (PathParam[UUID] :> CREATED[JSON, SomeResponse])] = simple

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
