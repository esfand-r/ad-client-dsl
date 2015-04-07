package com.appdirect.auth

import com.ning.http.client.oauth.{ConsumerKey, OAuthSignatureCalculator, RequestToken}
import com.ning.http.client.{Request, RequestBuilder}

/**
 * Created by esfandiaramirrahimi on 2015-10-06.
 */
trait Auth {
  def apply(request: Request): Request
}

case class Oauth1(clientId: String, clientSecret: String) extends Auth {
  override def apply(request: Request): Request = {
    val requestBuilder: RequestBuilder = new RequestBuilder(request)

    val consumerKey: ConsumerKey = new ConsumerKey(clientId, clientSecret)
    val token: RequestToken = new RequestToken("", "")
    val calc: OAuthSignatureCalculator = new OAuthSignatureCalculator(consumerKey, token)
    calc.calculateAndAddSignature(request, requestBuilder)

    requestBuilder.build
  }
}
