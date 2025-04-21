package woowacourse.movie

import woowacourse.movie.domain.Time

object StringFormatter {
    fun formatTime(time: Time): String = "%02d:%02d".format(time.value.hour, time.value.minute)
}
