package com.appdirect

import java.util.concurrent.TimeUnit

import com.appdirect.auth.Auth
import com.appdirect.http._
import com.appdirect.marketplace._
import com.appdirect.marketplace.query.{APIQuery, AppType, ConcreteQuery, SortOrder}
import com.appdirect.marketplace.resource._
import com.appdirect.marketplace.ws.WSEntity
import org.jboss.netty.handler.codec.http.HttpMethod

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class AsyncExecution

class SyncExecution

object ADConnectDsl {
  val API_ABSOLUTE_MAX_RETURN_COUNT = 100

  class AppDirectConnection {
    private val client: AsyncClient = new AsyncClient

    private val mapper = ObjectMapper(MapperType.JACKSON)

    private var path: String = null
    private var body: String = null
    private var marketplace: Marketplace = null
    private var resource: Resource = null
    private var httpMethod: HttpMethod = null
    private var authentication: Auth = null

    def connect(marketplace: Marketplace) = {
      this.marketplace = marketplace
      this
    }

    def to(resource: Resource) = {
      this.resource = resource
      this
    }

    def using(authentication: Auth): AppDirectConnection = {
      this.authentication = authentication
      this
    }

    def getBy(query: APIQuery) = {
      this.httpMethod = HttpMethod.GET
      this.path = this.marketplace.base + this.resource.path + query.queryString
      this
    }

    def get(query: APIQuery) = {
      this.httpMethod = HttpMethod.GET
      this.path = this.marketplace.base + this.resource.path + query.queryString
      this
    }

    def get = {
      this.httpMethod = HttpMethod.GET
      this.path = this.marketplace.base + this.resource.path
      this
    }

    def get(id: String) = {
      this.httpMethod = HttpMethod.GET
      this.path = this.marketplace.base + this.resource.path + "/" + id
      this
    }

    def create(entity: WSEntity) = {
      this.httpMethod = HttpMethod.POST
      this.path = this.marketplace.base + this.resource.path + "/"
      this
    }

    def update(id: String) = {
      this.httpMethod = HttpMethod.PUT
      this.path = this.marketplace.base + this.resource.path + "/" + id
      this
    }

    def using(entity: WSEntity) = {
      this.body = mapper.writeObjectToString(entity)
      this
    }

    def delete(id: String) = {
      this.httpMethod = HttpMethod.DELETE
      this.path = this.marketplace.base + this.resource.path + "/" + id
      this
    }

    def execute(asyncExecution: AsyncExecution) = {
      val request = client.getRequest(httpMethod = this.httpMethod, path = this.path, body = this.body,
        secure = this.resource.secure, authentication = this.authentication)
      client.execute(request = request)
    }

    def execute(syncExecution: SyncExecution) = {
      val request = client.getRequest(httpMethod = this.httpMethod, path = this.path, body = this.body,
        secure = this.resource.secure, authentication = this.authentication)
      Await.result(client.execute(request), Duration(10, TimeUnit.SECONDS))
    }
  }

  def asynchronously = new AsyncExecution

  def synchronously = new SyncExecution

  def rackspace = RackSpace

  def att = Att

  def bundle = new BundleResource

  def listing = new ListingResource

  def company = new CompanyResource

  def companyUser(companyId: String) = new CompanyUserResource(companyId)

  def all: ConcreteQuery = new ConcreteQuery {}

  class PimpedInt(quantity: Int) {
    def apps: APIQuery = {
      listingQuery(null, null, null, quantity)
    }
  }

  implicit def pimpedInt(quantity: Int): PimpedInt = new PimpedInt(quantity)

  def listingQuery(appType: AppType.Value = null, order: SortOrder.Value = null,
                   searchString: String = null, total: Int = API_ABSOLUTE_MAX_RETURN_COUNT) =
    ListingQuery(Option(appType), Option(order), Option(searchString), Option(total))
}
