package serviteur.api.compilation

import cats.effect.IO
import java.util.UUID
import org.junit.Assert._
import org.junit.Test

import serviteur.api._

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
    :> PathParam[UUID]
    :> RequestBody[SomeRequestBody]
    :> CREATED[JSON, SomeResponse]

def createTransaction: Handler[CreateTransaction] =
  uuid  => body => IO(SomeResponse())

// Request response
case class SomeRequestBody()
case class SomeResponse()
