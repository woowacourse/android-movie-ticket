package woowacourse.movie.activity

import woowacourse.movie.dto.MovieDto
import java.time.LocalDate

interface ReservationContract {
    interface Presenter {
        fun betweenDates(
            today: LocalDate,
            movieDto: MovieDto,
        ): List<LocalDate>

        fun price(memberCount: Int): Int

        fun addMember()

        fun removeMember()
    }

    interface View {
        fun updateMemberCount(result: Result<Int>)
    }
}
