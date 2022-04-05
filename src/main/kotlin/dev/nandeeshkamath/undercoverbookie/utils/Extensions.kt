package dev.nandeeshkamath.undercoverbookie.utils

import java.net.URI

fun host(url: String): String = URI.create(url).host ?: ""
