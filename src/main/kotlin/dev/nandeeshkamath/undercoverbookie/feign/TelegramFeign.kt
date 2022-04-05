package dev.nandeeshkamath.undercoverbookie.feign

import com.fasterxml.jackson.databind.ObjectMapper
import dev.nandeeshkamath.undercoverbookie.constant.ResultInfoConstants
import dev.nandeeshkamath.undercoverbookie.exception.ClientException
import dev.nandeeshkamath.undercoverbookie.exception.InvalidException
import dev.nandeeshkamath.undercoverbookie.exception.ServerException
import dev.nandeeshkamath.undercoverbookie.models.response.TelegramErrorResponse
import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    value = "telegram-feign",
    url = "\${telegram-api.base-url}",
    configuration = [TelegramFeignErrorDecoder::class]
)
interface TelegramFeign {
    @GetMapping("/sendMessage")
    fun sendMessageToChannel(@RequestParam("chat_id") chatId: String, @RequestParam text: String): ResponseEntity<*>?
}

@Component
class TelegramFeignErrorDecoder(private val mapper: ObjectMapper) : ErrorDecoder {
    override fun decode(methodKey: String?, response: Response?): Exception {
        if (response == null) {
            throw InvalidException("No response from telegram API")
        }
        when (response.status()) {
            in clientError ->
                throw ClientException(
                    response.status(),
                    ResultInfoConstants.BAD_REQUEST,
                    mapper.readValue(response.body()?.asInputStream(), TelegramErrorResponse::class.java).description
                )
            in serverError ->
                throw ServerException(
                    response.status(),
                    ResultInfoConstants.SERVER_ERROR,
                    mapper.writeValueAsString(response.body()?.asInputStream()) ?: null
                )
            else ->
                throw InvalidException("Invalid error response status")
        }
    }

    companion object {
        private val clientError = 400..499
        private val serverError = 500..599
    }
}
