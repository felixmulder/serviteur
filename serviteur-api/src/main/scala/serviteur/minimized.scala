/*
// This is crazy:
type Get0 = OK_BOOMER[Int, Unit]
def get0: Handler[Get0] = IO[Unit]()

type Get1 = OK_BOOMER[Boolean, Unit]
def get1: Handler[Get1] = IO[Unit]()

// compiles:
type API = Get0 :<|> Get1
def api0: API = HandlerAlt(get0, get1)

// does not compile:
extension ops:
  def [A,B](left: Handler[A]) :<|> (right: Handler[B]): HandlerAlt[A, B] =
    HandlerAlt(left, right)

import ops._
def api1: API =
  get0 :<|> get1


class HandlerAlt[A, B](left: Handler[A], right: Handler[B])

type :<|>[A,B] = HandlerAlt[A, B]

case class IO[A]()
case class OK_BOOMER[A, B]()

type Handler[API] = handler.Go[API]

object handler:
  // Starter for Handler reduction:
  type Go[API] = API match
    case _ =>
      HandlerSingle[API]

  // Reduce an `X` known to be a non `:>` type
  type HandlerSingle[X] = X match
    case OK_BOOMER[_, response] =>
      IO[response]
*/
