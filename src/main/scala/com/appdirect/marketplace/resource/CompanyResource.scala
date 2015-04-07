package com.appdirect.marketplace.resource

/**
 * Created by esfandiaramirrahimi on 2015-10-09.
 */
class CompanyResource extends AccountResource {
  override val name = "Company Resource"
  override val path = base + "/companies"
  override val secure = true
}
