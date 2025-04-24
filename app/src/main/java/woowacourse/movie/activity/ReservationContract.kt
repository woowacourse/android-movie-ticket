package woowacourse.movie.activity

import woowacourse.movie.dto.MovieDto
import woowacourse.movie.dto.ReservationDto
import java.time.LocalDate

interface ReservationContract {
    interface Presenter {
        fun betweenDates(
            today: LocalDate,
            movieDto: MovieDto,
        ): List<LocalDate>

        fun price(memberCount: Int): Int
    }

    interface View {
        fun navigate(reservationDto: ReservationDto)
    }
}
