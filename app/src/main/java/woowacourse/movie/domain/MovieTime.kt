package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

class MovieTime(
    val date: LocalDate,
    val time: Time
) : Serializable
