package woowacourse.movie.fixture

import woowacourse.movie.domain.rules.PriceRule
import woowacourse.movie.dto.MovieListDataDto
import woowacourse.movie.dto.PriceRuleDto
import woowacourse.movie.dto.ReservationDto
import woowacourse.movie.dto.ReservationSeatDto
import woowacourse.movie.dto.SeatDto
import woowacourse.movie.global.ServiceLocator
import java.time.LocalDateTime

object ActivityFixture {
    val reservationDto =
        ReservationDto(
            MovieListDataDto.MovieDto.fromMovie(ServiceLocator.movies[0]),
            2,
            LocalDateTime.of(2025, 4, 3, 11, 0, 0),
        )
    val seatsDto =
        listOf(
            SeatDto(
                PriceRuleDto.fromPriceRule(
                    PriceRule.RANK_B,
                ),
                "A",
                1,
            ),
            SeatDto(
                PriceRuleDto.fromPriceRule(
                    PriceRule.RANK_B,
                ),
                "A",
                2,
            ),
        )

    val reservationSeatDto =
        ReservationSeatDto(
            reservationDto,
            24_000,
            seatsDto,
        )
}
