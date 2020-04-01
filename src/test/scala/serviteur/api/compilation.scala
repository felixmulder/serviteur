package serviteur.api.compilation

import cats.effect.IO
import java.util.UUID
import serviteur.api._

object simplest:
  def response: IO[SomeResponse] = IO(SomeResponse())

  def testSimplest0: Handler[CREATED[JSON, SomeResponse]] = response

  def testSimplest1: Handler["v1" :> CREATED[JSON, SomeResponse]] = response

object simple:
  def uuidToIO: UUID => IO[SomeResponse] =
    _ => IO(SomeResponse())

  def test0: Handler[PathParam[UUID] :> CREATED[JSON, SomeResponse]] = uuidToIO

  def test1: Handler["v1" :> PathParam[UUID] :> CREATED[JSON, SomeResponse]] = uuidToIO

  def test2: Handler[GET :> "v1" :> PathParam[UUID] :> CREATED[JSON, SomeResponse]] = uuidToIO

  // This test hihglights the fact that the `:>` operator is left associative
  def test3: Handler[((GET :> "v1") :> PathParam[UUID]) :> CREATED[JSON, SomeResponse]] = uuidToIO

object advanced:
  type CreateTransaction =
    POST
      :> Header["Idempotency-Key", IdempotencyKey]
      :> "transactions"
      :> PathParam[UUID]
      :> RequestBody[SomeRequestBody]
      :> CREATED[JSON, SomeResponse]

  def createTransaction: Handler[CreateTransaction] =
    idempotencyKey => uuid => body => IO(SomeResponse())

  type DeleteTransaction =
    DELETE
      :> "transactions"
      :> PathParam[UUID]
      :> OK[JSON, Unit]

  def deleteTransaction: Handler[DeleteTransaction] =
    (uuid: UUID) => IO.unit // annotation on UUID just to assert no trickz

  type API =
    CreateTransaction :<|> DeleteTransaction

  def api: API =
    :<|>(createTransaction, deleteTransaction)

// Request response
case class SomeRequestBody()
case class SomeResponse()
opaque type IdempotencyKey = String
