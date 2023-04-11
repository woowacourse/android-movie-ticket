package woowacourse.movie.domain

import java.io.Serializable
import java.util.*

data class RunningDate(val startDate: Date, val endDate: Date) :
    Serializable
