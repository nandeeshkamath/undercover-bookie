package dev.nandeeshkamath.undercoverbookie.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "ticketing-feign", url = "\${ticketing-api.base-url}")
interface TicketingFeign {
    @GetMapping("api/movies/v1/synopsis/init?isdesktop=true&channel=web")
    fun movieSynopsis(
        @RequestParam("eventcode") eventId: String,
        @RequestHeader("authority") authority: String,
        @RequestHeader("x-region-code") regionCode: String,
        @RequestHeader("x-region-slug") regionSlug: String,
        @RequestHeader("accept") accept: String = " application/json, text/plain, */*",
        @RequestHeader("x-app-code") appCode: String = "WEB"
    ): String?
}
