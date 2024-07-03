package utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object Serializer {
    val json = jacksonObjectMapper()
}