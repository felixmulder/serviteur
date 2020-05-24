package serviteur.http.status

enum Status:
  case Status(code: Int, message: String)

object Status:
  /** Continue 100 */
  val status100 = Status(100, "Continue")

  /** Continue 100 */
  val continue = status100

  /** Switching Protocols 101 */
  val status101 = Status(101, "Switching Protocols")

  /** Switching Protocols 101 */
  val switchingProtocols = status101

  /** OK 200 */
  val status200 = Status(200, "OK")

  /** OK 200 */
  val ok = status200

  /** Created 201 */
  val status201 = Status(201, "Created")

  /** Created 201 */
  val created = status201

  /** Accepted 202 */
  val status202 = Status(202, "Accepted")

  /** Accepted 202 */
  val accepted = status202

  /** Non-Authoritive Information 203 */
  val status203 = Status(203, "Non-Authoritive Information")

  /** Non-Authoritive Information 203 */
  val nonAuthoritiveInfo = status203

  /** No Content 204 */
  val status204 = Status(204, "No Content")

  /** No Content 204 */
  val noContent = status204

  /** Reset Content 205 */
  val status205 = Status(205, "Reset Content")

  /** Reset Content 205 */
  val resetContent205 = status205

  /** Partial Content 206 */
  val status206 = Status(206, "Partial Content")

  /** Partial Content 206 */
  val partialContent206 = status206

  /** Multiple Choices 300 */
  val status300 = Status(300, "Multiple Choices")

  /** Multiple Choices 300 */
  val multipleChoices300 = status300

  /** Moved Permanently 301 */
  val status301 = Status(301, "Moved Permanently")

  /** Moved Permanently 301 */
  val movedPermanently301 = status301

  /** Found 302 */
  val status302 = Status(302, "Found")

  /** Found 302 */
  val found302 = status302

  /** See Other 303 */
  val status303 = Status(303, "See Other")

  /** See Other 303 */
  val seeOther303 = status303

  /** Not Modified 304 */
  val status304 = Status(304, "Not Modified")

  /** Not Modified 304 */
  val notModified304 = status304

  /** Use Proxy 305 */
  val status305 = Status(305, "Use Proxy")

  /** Use Proxy 305 */
  val useProxy305 = status305

  /** Temporary Redirect 307 */
  val status307 = Status(307, "Temporary Redirect")

  /** Temporary Redirect 307 */
  val temporaryRedirect307 = status307

  /** Permanent Redirect 308 */
  val status308 = Status(308, "Permanent Redirect")

  /** Permanent Redirect 308 */
  val permanentRedirect308 = status308

  /** Bad Request 400 */
  val status400 = Status(400, "Bad Request")

  /** Bad Request 400 */
  val badRequest400 = status400

  /** Unauthorized 401 */
  val status401 = Status(401, "Unauthorized")

  /** Unauthorized 401 */
  val unauthorized401 = status401

  /** Payment Required 402 */
  val status402 = Status(402, "Payment Required")

  /** Payment Required 402 */
  val paymentRequired402 = status402

  /** Forbidden 403 */
  val status403 = Status(403, "Forbidden")

  /** Forbidden 403 */
  val forbidden403 = status403

  /** Not Found 404 */
  val status404 = Status(404, "Not Found")

  /** Not Found 404 */
  val notFound404 = status404

  /** Method Not Allowed 405 */
  val status405 = Status(405, "Method Not Allowed")

  /** Method Not Allowed 405 */
  val methodNotAllowed405 = status405

  /** Not Acceptable 406 */
  val status406 = Status(406, "Not Acceptable")

  /** Not Acceptable 406 */
  val notAcceptable406 = status406

  /** Proxy Authentication Required 407 */
  val status407 = Status(407, "Proxy Authentication Required")

  /** Proxy Authentication Required 407 */
  val proxyAuthenticationRequired407 = status407

  /** Request Timeout 408 */
  val status408 = Status(408, "Request Timeout")

  /** Request Timeout 408 */
  val requestTimeout408 = status408

  /** Conflict 409 */
  val status409 = Status(409, "Conflict")

  /** Conflict 409 */
  val conflict409 = status409

  /** Gone 410 */
  val status410 = Status(410, "Gone")

  /** Gone 410 */
  val gone410 = status410

  /** Length Required 411 */
  val status411 = Status(411, "Length Required")

  /** Length Required 411 */
  val lengthRequired411 = status411

  /** Precondition Failed 412 */
  val status412 = Status(412, "Precondition Failed")

  /** Precondition Failed 412 */
  val preconditionFailed412 = status412

  /** Request Entity Too Large 413 */
  val status413 = Status(413, "Request Entity Too Large")

  /** Request Entity Too Large 413 */
  val requestEntityTooLarge413 = status413

  /** Request-URI Too Long 414 */
  val status414 = Status(414, "Request-URI Too Long")

  /** Request-URI Too Long 414 */
  val requestURITooLong414 = status414

  /** Unsupported Media Type 415 */
  val status415 = Status(415, "Unsupported Media Type")

  /** Unsupported Media Type 415 */
  val unsupportedMediaType415 = status415

  /** Requested Range Not Satisfiable 416 */
  val status416 = Status(416, "Requested Range Not Satisfiable")

  /** Requested Range Not Satisfiable 416 */
  val requestedRangeNotSatisfiable416 = status416

  /** Expectation Failed 417 */
  val status417 = Status(417, "Expectation Failed")

  /** Expectation Failed 417 */
  val expectationFailed417 = status417

  /** I'm a teapot 418 */
  val status418 = Status(418, "I'm a teapot")

  /** I'm a teapot 418 */
  val imATeapot418 = status418

  /** Unprocessable Entity 422 [RFC 6585](https://tools.ietf.org/html/rfc4918) */
  val status422 = Status(422, "Unprocessable Entity")

  /** Unprocessable Entity 422 [RFC 6585](https://tools.ietf.org/html/rfc4918) */
  val unprocessableEntity422 = status422

  /** Upgrade Required 426 [RFC 6585](https://tools.ietf.org/html/rfc7231#section-6.5.15) */
  val status426 = Status(426, "Upgrade Required")

  /** Upgrade Required 426 [RFC 6585](https://tools.ietf.org/html/rfc7231#section-6.5.15) */
  val upgradeRequired426 = status426

  /** Precondition Required 428 [RFC 6585](https://tools.ietf.org/html/rfc6585) */
  val status428 = Status(428, "Precondition Required")

  /** Precondition Required 428 [RFC 6585](https://tools.ietf.org/html/rfc6585) */
  val preconditionRequired428 = status428

  /** Too Many Requests 429 [RFC 6585](https://tools.ietf.org/html/rfc6585) */
  val status429 = Status(429, "Too Many Requests")

  /** Too Many Requests 429 [RFC 6585](https://tools.ietf.org/html/rfc6585) */
  val tooManyRequests429 = status429

  /** Request Header Fields Too Large 431 [RFC 6585](https://tools.ietf.org/html/rfc6585) */
  val status431 = Status(431, "Request Header Fields Too Large")

  /** Request Header Fields Too Large 431 [RFC 6585](https://tools.ietf.org/html/rfc6585) */
  val requestHeaderFieldsTooLarge431 = status431

  /** Internal Server Error 500 */
  val status500 = Status(500, "Internal Server Error")

  /** Internal Server Error 500 */
  val internalServerError500 = status500

  /** Not Implemented 501 */
  val status501 = Status(501, "Not Implemented")

  /** Not Implemented 501 */
  val notImplemented501 = status501

  /** Bad Gateway 502 */
  val status502 = Status(502, "Bad Gateway")

  /** Bad Gateway 502 */
  val badGateway502 = status502

  /** Service Unavailable 503 */
  val status503 = Status(503, "Service Unavailable")

  /** Service Unavailable 503 */
  val serviceUnavailable503 = status503

  /** Gateway Timeout 504 */
  val status504 = Status(504, "Gateway Timeout")

  /** Gateway Timeout 504 */
  val gatewayTimeout504 = status504

  /** HTTP Version Not Supported 505 */
  val status505 = Status(505, "HTTP Version Not Supported")

  /** HTTP Version Not Supported 505 */
  val httpVersionNotSupported505 = status505

  /** Network Authentication Required 511 [RFC 6585](https://tools.ietf.org/html/rfc6585) */
  val status511 = Status(511, "Network Authentication Required")

  /** Network Authentication Required 511 [RFC 6585](https://tools.ietf.org/html/rfc6585) */
  val networkAuthenticationRequired511 = status511
