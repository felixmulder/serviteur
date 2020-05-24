/** HTTP Headers
 */
package serviteur.http.header

/** The name of the header */
opaque type HeaderName = String

object HeaderName:
  def apply(s: String) = s.toLowerCase

/** The header's value */
opaque type HeaderValue = String

object HeaderValue:
  def apply(s: String) = s

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Accept: HeaderName = "Accept"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val AcceptCharset: HeaderName = "Accept-Charset"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val AcceptEncoding: HeaderName = "Accept-Encoding"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val AcceptLanguage: HeaderName = "Accept-Language"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val AcceptRanges: HeaderName = "Accept-Ranges"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Age: HeaderName = "Age"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Allow: HeaderName = "Allow"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Authorization: HeaderName = "Authorization"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val CacheControl: HeaderName = "Cache-Control"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Connection: HeaderName = "Connection"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec19.html */
val ContentDisposition: HeaderName = "Content-Disposition"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val ContentEncoding: HeaderName = "Content-Encoding"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val ContentLanguage: HeaderName = "Content-Language"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val ContentLength: HeaderName = "Content-Length"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val ContentLocation: HeaderName = "Content-Location"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val ContentMD5: HeaderName = "Content-MD5"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val ContentRange: HeaderName = "Content-Range"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val ContentType: HeaderName = "Content-Type"

/** HTTP Header names according to https://tools.ietf.org/html/rfc6265#section-4 */
val Cookie: HeaderName = "Cookie"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Date: HeaderName = "Date"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val ETag: HeaderName = "ETag"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Expect: HeaderName = "Expect"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Expires: HeaderName = "Expires"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val From: HeaderName = "From"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Host: HeaderName = "Host"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val IfMatch: HeaderName = "If-Match"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val IfModifiedSince: HeaderName = "If-Modified-Since"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val IfNoneMatch: HeaderName = "If-None-Match"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val IfRange: HeaderName = "If-Range"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val IfUnmodifiedSince: HeaderName = "If-Unmodified-Since"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val LastModified: HeaderName = "Last-Modified"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Location: HeaderName = "Location"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec19.html */
val MIMEVersion: HeaderName = "MIME-Version"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val MaxForwards: HeaderName = "Max-Forwards"

/** HTTP Header names according to https://tools.ietf.org/html/rfc6454 */
val Origin: HeaderName = "Origin"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Pragma: HeaderName = "Pragma"

/** HTTP Header names according to https://tools.ietf.org/html/rfc7240 */
val Prefer: HeaderName = "Prefer"

/** HTTP Header names according to https://tools.ietf.org/html/rfc7240 */
val PreferenceApplied: HeaderName = "Preference-Applied"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val ProxyAuthenticate: HeaderName = "Proxy-Authenticate"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val ProxyAuthorization: HeaderName = "Proxy-Authorization"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Range: HeaderName = "Range"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Referer: HeaderName = "Referer"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val RetryAfter: HeaderName = "Retry-After"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Server: HeaderName = "Server"

/** HTTP Header names according to https://tools.ietf.org/html/rfc6265#section-4 */
val SetCookie: HeaderName = "Set-Cookie"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val TE: HeaderName = "TE"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Trailer: HeaderName = "Trailer"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val TransferEncoding: HeaderName = "Transfer-Encoding"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Upgrade: HeaderName = "Upgrade"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val UserAgent: HeaderName = "User-Agent"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Vary: HeaderName = "Vary"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Via: HeaderName = "Via"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val WWWAuthenticate: HeaderName = "WWW-Authenticate"

/** HTTP Header names according to http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html */
val Warning: HeaderName = "Warning"
