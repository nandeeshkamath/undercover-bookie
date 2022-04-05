package dev.nandeeshkamath.undercoverbookie.controller

import dev.nandeeshkamath.undercoverbookie.models.ResponseWrapper
import dev.nandeeshkamath.undercoverbookie.service.ShadowService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.NotBlank

@RestController
class ShadowController(
    private val shadowService: ShadowService
) {

    @GetMapping("/spy")
    fun spy(
        @NotBlank eventId: String,
        @NotBlank regionCode: String,
        @NotBlank regionSlug: String,
        @NotBlank keyword: String
    ): ResponseWrapper<Nothing> {
        shadowService.spy(eventId, regionCode, regionSlug, keyword)
        return ResponseWrapper.success()
    }
}
