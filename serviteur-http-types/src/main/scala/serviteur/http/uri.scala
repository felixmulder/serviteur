package serviteur.http.uri

/** Query item */
opaque type QueryItem = (String, Option[String])

/** QueryParameters
 *
 *  General form: `a=b&c=d`, but if the value is None, it becmoes: `a&c=d`
 */
opaque type QueryParameters = List[QueryItem]

/** Path */
opaque type Path = List[String]

/** Host */
opaque type Host = String
