package com.appdirect.marketplace.resource

import com.appdirect.marketplace.query.{APIQuery, AppType, SortOrder}

/**
 * Created by esfandiaramirrahimi on 2015-10-09.
 */
class ListingResource extends MarketPlaceResource {
  override val name = "Marketplace Listings"
  override val path = base + "/listing"
}

case class ListingQuery(appTypeOption: Option[AppType.Value], orderOption: Option[SortOrder.Value],
                        searchStringOption: Option[String], totalOption: Option[Int]) extends APIQuery {

  val MAX_COUNT = 100

  override val queryString = "?" +
    totalOption.map(count => s"count=$count").getOrElse(s"count=$MAX_COUNT") +
    orderOption.map(order => s"&order=$order").getOrElse("") +
    appTypeOption.map(appType => s"&type=$appType").getOrElse("") +
    searchStringOption.map(searchString => s"&q=$searchString").getOrElse("")
}
