package br.com.alura.forum.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.boot.jackson.JsonComponent
import org.springframework.data.domain.Page

@JsonComponent
class PageJsonSerializer: JsonSerializer<Page<*>>() {

    override fun serialize(page: Page<*>?, jsonGenerator: JsonGenerator?, p2: SerializerProvider?) {
        if (jsonGenerator != null) {
            jsonGenerator.writeStartObject()
            jsonGenerator.writeObjectField("content", page!!.content)
            jsonGenerator.writeNumberField("size", page.size)
            jsonGenerator.writeNumberField("totalElements", page.totalElements)
            jsonGenerator.writeNumberField("totalPages", page.totalPages)
            jsonGenerator.writeNumberField("numberPage", page.number)
            jsonGenerator.writeEndObject()
        }
    }
}