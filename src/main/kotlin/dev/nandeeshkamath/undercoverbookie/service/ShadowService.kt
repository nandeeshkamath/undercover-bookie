package dev.nandeeshkamath.undercoverbookie.service

import dev.nandeeshkamath.undercoverbookie.feign.TelegramFeign
import dev.nandeeshkamath.undercoverbookie.feign.TicketingFeign
import dev.nandeeshkamath.undercoverbookie.models.response.MovieSynopsisResponse
import dev.nandeeshkamath.undercoverbookie.utils.host
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class ShadowService(
    @Value("\${ticketing-api.base-url}") private val ticketingApiUrl: String,
    @Value("\${telegram-api.channel-id}") private val channelId: String,
    @Value("\${telegram-api.debug-channel-id}") private val debugChannelId: String,
    private val ticketingFeign: TicketingFeign,
    private val telegramFeign: TelegramFeign
) {
    @Async
    fun spy(eventId: String, regionCode: String, regionSlug: String, keyword: String) {
        val response = ticketingFeign.movieSynopsis(
            eventId, host(ticketingApiUrl), regionCode, regionSlug
        )?.let { MovieSynopsisResponse(it) }

        if (response == null) {
            telegramFeign.sendMessageToChannel(
                debugChannelId,
                "Something went wrong while fetching movie synopsis"
            )
            return
        }
        if (response.text?.contains(keyword, ignoreCase = true) == true) {
            val message = """
                ${response.eventName} is now ready to be booked at $regionSlug.
                ${response.bookingUrl}
            """.trimIndent()
            telegramFeign.sendMessageToChannel(
                channelId,
                message
            )
        }
    }
}
