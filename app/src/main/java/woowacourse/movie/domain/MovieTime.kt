package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalTime

@Parcelize
class MovieTime private constructor(val hour: Int, val min: Int = DEFAULT_MIN) :
    Discountable, Comparable<MovieTime>, Parcelable {

    private fun isDiscountTime(): Boolean =
        hour < AM_DISCOUNT_CLOSE_TIME || hour >= PM_DISCOUNT_OPEN_TIME

    override fun compareTo(other: MovieTime): Int =
        (hour * HOUR_TO_MIN + min) - (other.hour * HOUR_TO_MIN + other.min)

    override fun discount(money: Int): Int =
        if (isDiscountTime()) money - DISCOUNT_PRICE else money

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieTime

        if (hour != other.hour) return false
        if (min != other.min) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hour
        result = 31 * result + min
        return result
    }

    companion object {
        private const val AM_DISCOUNT_CLOSE_TIME = 11
        private const val PM_DISCOUNT_OPEN_TIME = 20

        private const val WEEKEND_MIN_TIME = 9
        private const val WEEKEND_MAX_TIME = 24
        private const val WEEKEND_MOVIE_TIME_INTERVAL = 2

        private const val WEEKDAY_MIN_TIME = 10
        private const val WEEKDAY_MAX_TIME = 24
        private const val WEEKDAY_MOVIE_TIME_INTERVAL = 2

        private const val DEFAULT_MIN = 0
        private const val HOUR_TO_MIN = 60

        private const val DISCOUNT_PRICE = 2_000

        private val weekendTimes: List<MovieTime> =
            (WEEKEND_MIN_TIME until WEEKEND_MAX_TIME step WEEKEND_MOVIE_TIME_INTERVAL)
                .map { MovieTime(it) }

        private val weekdayTimes: List<MovieTime> =
            (WEEKDAY_MIN_TIME until WEEKDAY_MAX_TIME step WEEKDAY_MOVIE_TIME_INTERVAL)
                .map { MovieTime(it) }

        @JvmOverloads
        fun runningTimes(
            isWeekday: Boolean,
            isToday: Boolean,
            currentTime: LocalTime = LocalTime.now(),
        ): List<MovieTime> {
            val runningTimes = if (isWeekday) weekdayTimes else weekendTimes
            val hour = if (isToday) currentTime.hour else 0
            val min = if (isToday) currentTime.minute else 0

            return runningTimes.filter { it > MovieTime(hour, min) }
        }
    }
}
