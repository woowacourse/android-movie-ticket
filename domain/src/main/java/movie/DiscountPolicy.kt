package movie

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

object DiscountPolicy : Serializable {
    fun of(localDate: LocalDate, localTime: LocalTime): (Int) -> Int = when {
        isMovieDay(localDate.dayOfMonth) && isEarlyMorning(localTime.hour) -> { price -> discountMovieDay(price) - EARLY_MORNING_DISCOUNT_AMOUNT }
        isMovieDay(localDate.dayOfMonth) && isEvening(localTime.hour) -> { price -> discountMovieDay(price) - EVENING_DISCOUNT_AMOUNT }
        isMovieDay(localDate.dayOfMonth) -> { price -> discountMovieDay(price) }
        isEarlyMorning(localTime.hour) -> { price -> price - EARLY_MORNING_DISCOUNT_AMOUNT }
        isEvening(localTime.hour) -> { price -> price - EVENING_DISCOUNT_AMOUNT }
        else -> { price -> price }
    }

    private fun discountMovieDay(totalPrice: Int) = (totalPrice * MOVIE_DAY_DISCOUNT_RATE).toInt()
    private fun isMovieDay(day: Int) = day % 10 == 0
    private fun isEarlyMorning(hour: Int) = hour < earlyMorningTime
    private fun isEvening(hour: Int) = hour > eveningTime

    private const val MOVIE_DAY_DISCOUNT_RATE = 0.9
    private const val EARLY_MORNING_DISCOUNT_AMOUNT = 2000
    private const val EVENING_DISCOUNT_AMOUNT = 2000
    private const val earlyMorningTime = 11
    private const val eveningTime = 20
}
