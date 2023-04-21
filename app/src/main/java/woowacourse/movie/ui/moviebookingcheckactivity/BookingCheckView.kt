package woowacourse.movie.ui.moviebookingcheckactivity

import android.view.ViewGroup
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.price.TicketCount
import woowacourse.movie.domain.price.TicketPrice
import woowacourse.movie.domain.price.discount.runningpolicy.TimeMovieDayDiscountPolicy
import woowacourse.movie.domain.price.pricecalculate.PricePolicyCalculator
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.model.MovieUIModel

class BookingCheckView(
    private val view: ViewGroup,
    movieData: MovieUIModel,
    bookedScreeningDateTime: woowacourse.movie.domain.datetime.ScreeningDateTime,
    ticketCount: woowacourse.movie.domain.price.TicketCount
) {

    init {
        initMovieInformation(movieData, bookedScreeningDateTime, ticketCount)
    }

    private fun initMovieInformation(
        movieData: MovieUIModel,
        bookedScreeningDateTime: woowacourse.movie.domain.datetime.ScreeningDateTime,
        ticketCount: woowacourse.movie.domain.price.TicketCount
    ) {
        val tvBookingCheckMovieName = view.findViewById<TextView>(R.id.tv_booking_check_movie_name)
        val tvBookingCheckScreeningDay =
            view.findViewById<TextView>(R.id.tv_booking_check_screening_day)
        val tvBookingCheckPersonCount =
            view.findViewById<TextView>(R.id.tv_booking_check_person_count)
        val tvBookingCheckTotalMoney =
            view.findViewById<TextView>(R.id.tv_booking_check_total_money)

        tvBookingCheckMovieName.text = movieData.title
        tvBookingCheckScreeningDay.text =
            bookedScreeningDateTime.time.format(DateTimeFormatters.dateDotTimeColonFormatter)
        tvBookingCheckPersonCount.text =
            view.context.getString(R.string.tv_booking_check_person_count).format(ticketCount.value)
        tvBookingCheckTotalMoney.text =
            view.context.getString(R.string.tv_booking_check_total_money)
                .format(
                    applyDisCount(
                        woowacourse.movie.domain.price.TicketPrice(woowacourse.movie.domain.price.TicketPrice.STANDARD_TICKET_PRICE),
                        ticketCount,
                        bookedScreeningDateTime
                    ).value
                )
    }

    private fun applyDisCount(
        ticketPrice: woowacourse.movie.domain.price.TicketPrice,
        ticketCount: woowacourse.movie.domain.price.TicketCount,
        bookedScreeningDateTime: woowacourse.movie.domain.datetime.ScreeningDateTime
    ): woowacourse.movie.domain.price.TicketPrice =
        woowacourse.movie.domain.price.pricecalculate.PricePolicyCalculator(
            woowacourse.movie.domain.price.discount.runningpolicy.TimeMovieDayDiscountPolicy(
                bookedScreeningDateTime
            ).getDiscountPolicies()
        )
            .totalPriceCalculate(ticketPrice, ticketCount)
}
