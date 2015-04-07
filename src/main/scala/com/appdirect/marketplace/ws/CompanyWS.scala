package com.appdirect.marketplace.ws

/**
 * Created by esfandiaramirrahimi on 2015-10-07.
 */

case class Im(imType: String, account: String)

case class Ims(im: Im)

case class Address(city: String, country: String, state: String,
                   street1: String, street2: String = null, zip: String)

case class Contact(phoneNumber: String, ims: Ims = null, homePhone: String = null,
                   mobilePhone: String = null) {
  var address: Address = null

  def withAddress(address: Address): Contact = {
    this.address = address
    this
  }
}

case class Entry(key: String, value: String)

case class Attributes(entry: Entry)

case class SalesAgent(_id: String, _href: String)

case class Dealer(tsuiteDealerId: String, tsuiteAppointedDate: String, tsuiteExpiredDate: String,
                  msppDealerId: String, msppAppointedDate: String, msppExpiredDate: String)

case class Company(id: String, name: String, enabled: String = null,
                   size: String = null, attributes: Attributes = null, creationDate: String = null,
                   industry: String = null, salesAgent: SalesAgent = null, website: String = null,
                   emailAddress: String = null, dealer: Dealer = null, customAttributes: Attributes = null
                    ) extends WSEntity {
  var contact: Contact = null

  def withContact(contact: Contact): Company = {
    this.contact = contact
    this
  }
}
