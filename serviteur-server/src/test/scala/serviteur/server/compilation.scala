package serviteur.server.compilation

import cats.effect.IO
import serviteur.api._
import serviteur.server._
//import serviteur.server.{given _, _}

private object simplest:
  //val exampleRequest = Request(
  //  RequestMethod.GET,
  //  Nil,
  //  Nil,
  //  Body.EmptyBody,
  //)
  //
  //val exampleHandler =
  //  summon[HandleRequest[Handler[CREATED[JSON, Unit]]]]
  //    .handleRequest(exampleRequest, handler)

  val handler: Handler[CREATED[JSON, Unit]] = IO.unit
