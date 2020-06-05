package serviteur.server.compilation

import cats.effect.IO
import serviteur.api._
import serviteur.http._
import serviteur.server.{given _, _}

private object simplest:

  val responseCode: ToResponseCode[CREATED[JSON, Int]] =
    summon[ToResponseCode[CREATED[JSON, Int]]]

  val renderJSON: MimeRender[JSON, Int] =
    summon[MimeRender[JSON, Int]]

  // FIXME - Should be enabled once the givens can be properly imported
  //val toRes: ToResponse[IO, Handler[IO, CREATED[JSON, Int]]] =
  //  summon[ToResponse[IO, Handler[IO, CREATED[JSON, Int]]]]

  // FIXME - Should be enabled once the givens can be properly imported
  //val hasServerCreated: HasServer[IO, Handler[IO, CREATED[JSON, Int]]] =
  //  summon[HasServer[IO, Handler[IO, CREATED[JSON, Int]]]]

  val handler: Handler[IO, CREATED[JSON, Unit]] = IO.unit
