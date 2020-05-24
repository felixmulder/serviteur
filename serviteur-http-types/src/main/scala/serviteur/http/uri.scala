package serviteur.http.uri

/** Query item */
opaque type QueryItem = (String, Option[String])

/** Query
 *
 *  General form: `a=b&c=d`, but if the value is None, it becmoes: `a&c=d`
 */
opaque type Query = List[QueryItem]
