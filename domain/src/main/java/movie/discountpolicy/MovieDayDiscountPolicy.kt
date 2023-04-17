package movie.discountpolicy

import java.time.LocalDate

class MovieDayDiscountPolicy(
    private val date: LocalDate,
) : DiscountPolicy {

    override fun getDiscountPrice(price: Int): Int {
        return when {
            isMovieDay() -> (price * MOVIE_DAY_DISCOUNT_RATE).toInt()
            else -> 0
        }
    }

    private fun isMovieDay(): Boolean {
        return (date.dayOfMonth % 10) == 0
    }

    companion object {
        private const val MOVIE_DAY_DISCOUNT_RATE = 0.1
    }
}
