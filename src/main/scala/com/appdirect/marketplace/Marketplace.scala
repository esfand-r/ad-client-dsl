package com.appdirect.marketplace

/**
 * Created by esfandiaramirrahimi on 2015-10-05.
 */

object AuthenticationMethod extends Enumeration {
  val OAUTH1, BASIC = Value
}

// Main trait allowing other marketplaces to extend and override the base url.
sealed trait Marketplace {
  val base = "https://www.appdirect.com"
  val authenticationMethod: AuthenticationMethod.Value = AuthenticationMethod.OAUTH1
}

// RackSpace connection.
case object RackSpace extends Marketplace {
  override val base = "http://local.rackspace.com:8080/api/"
}

case object Att extends Marketplace {
  override val base = "https://testatt2.appdirect.com/api/"
}
