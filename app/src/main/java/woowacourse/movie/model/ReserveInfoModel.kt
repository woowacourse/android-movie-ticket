package woowacourse.movie.model

import java.time.LocalDateTime

data class ReserveInfoModel(
    val title: String,
    val dateTime: LocalDateTime,
    val count: Int
) : java.io.Serializable
