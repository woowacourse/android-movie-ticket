package woowacourse.movie.fixture

import woowacourse.movie.domain.rules.PriceRule
import woowacourse.movie.dto.MovieListData
import woowacourse.movie.dto.PriceRuleDto
import woowacourse.movie.dto.ReservationDto
import woowacourse.movie.dto.ReservationSeatDto
import woowacourse.movie.dto.SeatDto
import woowacourse.movie.global.ServiceLocator
import java.time.LocalDateTime

object ActivityFixture {
    val reservationDto =
        ReservationDto(
            MovieListData.MovieDto.fromMovie(ServiceLocator.movies[0]),
            2,
            LocalDateTime.of(2025, 4, 3, 11, 0, 0),
        )
    val seatsDto =
        listOf(
            SeatDto(
                "A1",
                PriceRuleDto.fromPriceRule(
                    PriceRule.RANK_B,
                ),
                "A",
                1,
            ),
            SeatDto(
                "A2",
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
