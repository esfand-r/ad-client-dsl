package com.appdirect.marketplace.query

/**
 * Created by esfandiaramirrahimi on 2015-10-05.
 */
trait APIQuery {
  val queryString: String = ""
}

trait ConcreteQuery extends APIQuery

object AppType extends Enumeration {
  val WEB_APP, WEB_APP_MANUAL_SETUP, BUNDLE = Value
}

object SortOrder extends Enumeration {
  val NEWEST_FIRST, ALPHABETICAL = Value
}
