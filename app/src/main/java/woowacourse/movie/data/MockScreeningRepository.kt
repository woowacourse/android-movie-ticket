package woowacourse.movie.data

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.screening.Screening
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SeatRow
import woowacourse.movie.feature.main.ui.ScreeningItem
import woowacourse.movie.feature.main.ui.toUiModel
import java.time.LocalDate

object MockScreeningRepository : ScreeningRepository {
    private val defaultSeats: List<Seat> =
        listOf(
            Seat(SeatRow.A, 1),
            Seat(SeatRow.A, 2),
            Seat(SeatRow.A, 3),
            Seat(SeatRow.A, 4),
            Seat(SeatRow.B, 1),
            Seat(SeatRow.B, 2),
            Seat(SeatRow.B, 3),
            Seat(SeatRow.B, 4),
            Seat(SeatRow.C, 1),
            Seat(SeatRow.C, 2),
            Seat(SeatRow.C, 3),
            Seat(SeatRow.C, 4),
            Seat(SeatRow.D, 1),
            Seat(SeatRow.D, 2),
            Seat(SeatRow.D, 3),
            Seat(SeatRow.D, 4),
            Seat(SeatRow.E, 1),
            Seat(SeatRow.E, 2),
            Seat(SeatRow.E, 3),
            Seat(SeatRow.E, 4),
        )

    private val screening1 =
        Screening(
            0,
            Movie(
                R.drawable.sorcerer_ston_poster,
                "해리 포터와 마법사의 돌",
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, " +
                    "영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다." +
                    "크리스 콜럼버스가 감독을 맡았다.",
                152,
            ),
            LocalDate.of(2024, 3, 1),
            LocalDate.of(2024, 4, 30),
        )
    private val screening2 =
        Screening(
            1,
            Movie(
                R.drawable.secret_room_poster,
                "해리 포터와 비밀의 방",
                "《해리 포터와 비밀의 방》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다.",
                162,
            ),
            LocalDate.of(2024, 4, 1),
            LocalDate.of(2024, 4, 28),
        )

    private val screening3 =
        Screening(
            2,
            Movie(
                R.drawable.azkaban_prisoner_poster,
                "해리 포터와 아즈카반의 죄수",
                "《해리 포터와 아즈카반의 죄수》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, " +
                    "영국과 미국 합작, 판타지 영화이다.",
                141,
            ),
            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 31),
        )

    private val screening4 =
        Screening(
            3,
            Movie(
                R.drawable.goblet_of_fire_poster,
                "해리 포터와 불의 잔",
                "《해리 포터와 불의 잔》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다.",
                157,
            ),
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 6, 30),
        )

    private val ad1 =
        ScreeningItem.AdModel(
            id = 0,
            R.drawable.woowa_ad,
        )

    private val screeningItems: List<ScreeningItem> =
        listOf(
            screening1.toUiModel(),
            screening2.toUiModel(),
            screening3.toUiModel(),
            ad1,
            screening4.toUiModel(),
        )

    private val screenings: List<Screening> =
        listOf(
            screening1,
            screening2,
            screening3,
            screening4,
        )

    fun getSeats(screeningId: Long): List<Seat> = defaultSeats

    override fun findAll(): List<ScreeningItem> = screeningItems

    override fun find(id: Long): Screening? = screenings.find { it.id == id }
}
