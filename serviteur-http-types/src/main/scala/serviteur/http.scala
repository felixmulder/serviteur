package serviteur.http

import serviteur.http.header._
import serviteur.http.method._
import serviteur.http.uri._
import serviteur.http.status._

enum Request:
  case Request
    ( body: RequestBody
    , headers: Map[HeaderName, HeaderValue]
    , host: Host
    , method: Method
    , path: Path
    , port: Int
    , queryParameters: QueryParameters
    )

enum Response:
  case Response
    ( headers: Map[HeaderName, HeaderValue]
    , body: ResponseBody
    , status: Status
    )

enum RequestBody:
  case NoRequestBody

enum ResponseBody:
  case StringBody(contents: String)
  case NoResponseBody
