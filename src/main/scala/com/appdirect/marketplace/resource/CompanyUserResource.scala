package com.appdirect.marketplace.resource

/**
 * Created by esfandiaramirrahimi on 2015-10-09.
 */
class CompanyUserResource(companyId: String) extends CompanyResource {
  override val name = "Company Bundles"
  override val path = base + "/companies/" + companyId + "/users"
}
