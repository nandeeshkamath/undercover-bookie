package dev.nandeeshkamath.undercoverbookie.models.response

import com.jayway.jsonpath.JsonPath

class MovieSynopsisResponse(
    val text: String?,
    val eventName: String?,
    val bookingUrl: String?
) {
    constructor(json: String) : this(
        text = kotlin.runCatching { JsonPath.parse(json).read<String>("$['bannerWidget']['pageCta'][0]['text']") }
            .getOrNull(),
        eventName = kotlin.runCatching { JsonPath.parse(json).read<String>("$['meta']['event']['eventName']") }
            .getOrNull(),
        bookingUrl = kotlin.runCatching { JsonPath.parse(json).read<String>("$['seo']['metaProperties'][7]['value']") }
            .getOrNull()
    )
}
