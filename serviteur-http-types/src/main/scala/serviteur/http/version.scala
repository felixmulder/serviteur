package serviteur.http.version

/** HTTP Version */
enum HttpVersion:
  case HttpVersion(major: Int, minor: Int)

/** Contains official HTTP versions */
object HttpVersion:
  /** HTTP 0.9 */
  val v09 = HttpVersion(0, 9)

  /** HTTP 1.0 */
  val v10 = HttpVersion(1, 0)

  /** HTTP 1.1 */
  val v11 = HttpVersion(1, 1)

  /** HTTP 2.0 */
  val v20 = HttpVersion(2, 0)
