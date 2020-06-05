package serviteur.api.compilation

import cats.effect.IO
import java.util.UUID
import serviteur.api._

private object simplest:
  def response: IO[SomeResponse] = IO(SomeResponse())

  def testSimplest0: Handler[IO, CREATED[JSON, SomeResponse]] = response

  def testSimplest1: Handler[IO, "v1" :> CREATED[JSON, SomeResponse]] = response

private object simple:
  def uuidToIO: UUID => IO[SomeResponse] =
    _ => IO(SomeResponse())

  def test0: Handler[IO, PathParam[UUID] :> CREATED[JSON, SomeResponse]] = uuidToIO

  def test1: Handler[IO, "v1" :> PathParam[UUID] :> CREATED[JSON, SomeResponse]] = uuidToIO

  def test2: Handler[IO, GET :> "v1" :> PathParam[UUID] :> CREATED[JSON, SomeResponse]] = uuidToIO

  // This test hihglights the fact that the `:>` operator is left associative
  def test3: Handler[IO, ((GET :> "v1") :> PathParam[UUID]) :> CREATED[JSON, SomeResponse]] = uuidToIO

private object advanced:
  type CreateTransaction =
    POST
      :> Header["Idempotency-Key", IdempotencyKey]
      :> "transactions"
      :> PathParam[UUID]
      :> RequestBody[SomeRequestBody]
      :> CREATED[JSON, SomeResponse]

  def createTransaction: Handler[IO, CreateTransaction] =
    idempotencyKey => uuid => body => IO(SomeResponse())

  type DeleteTransaction =
    DELETE
      :> "transactions"
      :> PathParam[UUID]
      :> OK[JSON, Unit]

  def deleteTransaction: Handler[IO, DeleteTransaction] =
    (uuid: UUID) => IO.unit // annotation on UUID just to assert no trickz

  type API =
    CreateTransaction :<|> DeleteTransaction

  def api: Handler[IO, API] =
    :<|>(createTransaction, deleteTransaction)

// Request response
private case class SomeRequestBody()
private case class SomeResponse()
private opaque type IdempotencyKey = String
