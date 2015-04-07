package com.appdirect.http

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
 * Created by esfandiaramirrahimi on 2015-10-08.
 */
trait Mapper {
  def writeObjectToString(obj: Object): String
}

object MapperType extends Enumeration {
  val JACKSON = Value
}

object ObjectMapper {
  private val objectMappers = Map(
    MapperType.JACKSON -> new JacksonObjectMapper
  )

  def apply(mapperType: MapperType.Value): Mapper = {
    objectMappers.get(mapperType).get
  }

  private class JacksonObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper with Mapper {
    val objectMapper = new com.fasterxml.jackson.databind.ObjectMapper

    override def writeObjectToString(obj: Object): String = {
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
      objectMapper.registerModule(DefaultScalaModule)
      objectMapper.writeValueAsString(obj)
    }
  }

}
