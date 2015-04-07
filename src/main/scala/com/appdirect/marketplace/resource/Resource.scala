package com.appdirect.marketplace.resource

/**
 * Created by esfandiaramirrahimi on 2015-10-05.
 */
trait Resource {
  val name = ""
  val base = ""
  val path = ""
  val secure = false
}

trait MarketPlaceResource extends Resource {
  override val name = "Marketplace"
  override val base = "marketplace/v1"
}

trait AccountResource extends Resource {
  override val name = "Account"
  override val base = "account/v1"
}
