package dev.nandeeshkamath.undercoverbookie.models

import com.youtube.tracker.models.ResultInfo
import dev.nandeeshkamath.undercoverbookie.constant.ResultInfoConstants

data class ResponseWrapper<T>(
    val resultInfo: ResultInfo,
    val data: T?
) {
    companion object {
        fun <T> success(data: T? = null) = ResponseWrapper(ResultInfoConstants.SUCCESS, data)
    }
}
