package com.appdirect.http

import com.appdirect.auth.Auth
import com.ning.http.client.{AsyncCompletionHandler, AsyncHttpClient, Request, Response}
import org.jboss.netty.handler.codec.http.HttpMethod

import scala.concurrent.Promise

/**
 * Created by esfandiaramirrahimi on 2015-10-08.
 */

class AsyncClient {
  val client = new AsyncHttpClient()

  val RequestMap = Map(
    HttpMethod.GET -> { (url: String) => client.prepareGet(url) },
    HttpMethod.POST -> { (url: String) => client.preparePost(url) },
    HttpMethod.DELETE -> { (url: String) => client.prepareDelete(url) },
    HttpMethod.PUT -> { (url: String) => client.preparePut(url) },
    HttpMethod.PATCH -> { (url: String) => client.preparePatch(url) },
    HttpMethod.OPTIONS -> { (url: String) => client.prepareOptions(url) }
  )

  def getBuilder(httpMethod: HttpMethod, path: String) = {
    RequestMap.get(httpMethod).get(path)
      .addHeader("Accept", "application/json")
      .addHeader("Content-Type", "application/json")
  }

  def getRequest(httpMethod: HttpMethod, path: String, body: String = null, secure: Boolean = false, authentication: Auth = null) = {
    val requestBuilder = RequestMap.get(httpMethod).get(path)
      .addHeader("Accept", "application/json")
      .addHeader("Content-Type", "application/json")

    if (body != null) {
      requestBuilder.setBody(body)
    }

    var request = requestBuilder.build()

    if (secure) {
      if (authentication == null)
        throw new RuntimeException("When resource is secure, authentication must be provided")
      request = authentication.apply(request)
    }

    request
  }

  def execute(request: Request) = {
    val result = Promise[Response]()
    client.executeRequest(request, new AsyncCompletionHandler[Response]() {
      override def onCompleted(response: Response) = {
        result.success(response)
        response
      }

      override def onThrowable(t: Throwable) {
        result.failure(t)
      }
    })
    result.future
  }
}

object AsyncClient {
  def apply = new AsyncClient()
}
