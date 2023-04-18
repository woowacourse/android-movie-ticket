package woowacourse.movie.view

import woowacourse.movie.R
import woowacourse.movie.service.MovieService
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

object MovieMockDateInitiator {

    private val images: MutableMap<Long, Int> = mutableMapOf()

    fun initMovieMockData() {
        val movieId = MovieService.save(
            title = "해리 포터와 마법사의 돌",
            runningTime = 152,
            summary = " 《해리 포터와 마법사의 돌》 은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다."
        )
        val screeningStartDate = LocalDate.of(2024, 3, 1)
        val screeningEndDate = LocalDate.of(2024, 3, 31)
        val screeningDateTimes = createScreeningDateTimes(screeningStartDate, screeningEndDate)

        screeningDateTimes.forEach { MovieService.addScreening(movieId, it) }
    }

    fun getImageResourceIdOf(movieId: Long): Int {
        return images[movieId] ?: R.drawable.harry_porter_poster
    }

    private fun createScreeningDateTimes(
        screeningStartDate: LocalDate,
        screeningEndDate: LocalDate
    ): List<LocalDateTime> {
        val screeningDateTimes: MutableList<LocalDateTime> = mutableListOf()
        var date = screeningStartDate
        while (date <= screeningEndDate) {
            val startTime = if (date.isWeekend()) LocalTime.of(9, 0) else LocalTime.of(10, 0)
            startTime.getAllTimesUntilMidNight(2)
                .forEach { screeningDateTimes.add(LocalDateTime.of(date, it)) }
            date = date.plusDays(1)
        }
        return screeningDateTimes
    }

    private fun LocalTime.getAllTimesUntilMidNight(intervalHour: Long): List<LocalTime> {
        val times: MutableList<LocalTime> = mutableListOf()
        var time = this
        while (time >= this) {
            times.add(time)
            time = time.plusHours(intervalHour)
        }
        return times
    }
}

private fun LocalDate.isWeekend(): Boolean =
    this.dayOfWeek == DayOfWeek.SATURDAY || this.dayOfWeek == DayOfWeek.SUNDAY
