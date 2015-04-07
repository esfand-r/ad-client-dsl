package com.appdirect.Test

import com.appdirect.ADConnectDsl._
import com.appdirect.auth.Oauth1
import com.appdirect.marketplace.query.{AppType, SortOrder}
import com.appdirect.marketplace.ws.{Address, Company, Contact}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object ClientTest {
  def main(args: Array[String]) = {
    // Client ID and Secret configured on the local instance.
    // Will try to use types more effectively so let's say when you type in get,
    // then IDE gives you suggestions from the type of query than can be applied to that particular resource.

    val connect = new AppDirectConnection connect att using Oauth1(clientId = "", clientSecret = "")

    connect to listing getBy listingQuery(appType = AppType.WEB_APP, order = SortOrder.ALPHABETICAL, searchString = "test") execute synchronously

    connect to listing getBy listingQuery(appType = AppType.WEB_APP, order = SortOrder.ALPHABETICAL) execute synchronously

    connect to listing getBy listingQuery(appType = AppType.WEB_APP, order = SortOrder.ALPHABETICAL) execute synchronously

    connect to bundle get all execute synchronously

    connect to listing get (10 apps) execute asynchronously onComplete {
      case Success(response) => println("Response" + response.getResponseBody)
      case Failure(error) => println("An error has occurred: " + error.getMessage)
    }

    connect to company get "74c01d4a-6944-4d40-afd5-439b612a6f41" execute synchronously

    connect to companyUser(companyId = "1") get all execute synchronously

    val address = Address(city = "Montreal", country = "Canada", state = "QC", street1 = "1400 Notre Dame", zip = "12345")
    val contact = Contact(phoneNumber = "5147956401") withAddress address

    connect to company create (Company(id = "12333dd33c3", name = "ad5") withContact (contact withAddress address)) execute synchronously

    connect to company delete "74c01d4a-6944-4d40-afd5-439b612a6f41" execute synchronously

    connect to company update "74c01d4a-6944-4d40-afd5-439b612a6f41" using (Company(id = "12333dd33c3", name = "ad5") withContact contact) execute synchronously

    val body = connect to company get all execute synchronously getResponseBody

    println(body)
  }
}
