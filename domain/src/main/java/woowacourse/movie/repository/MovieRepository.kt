package woowacourse.movie.repository

import woowacourse.movie.domain.Minute
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningInfoOfMovie
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Period

object MovieRepository {

    private val movies: MutableMap<Long, Movie> = mutableMapOf()
    private const val INIT_ID = 1L
    var nextId: Long = INIT_ID
        private set

    init {
        addSampleData()
    }

    private fun addSampleData() {
        val movieId = nextId++
        val movie = Movie(
            movieId,
            "해리 포터와 마법사의 돌",
            Minute(152),
            "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다."
        )
        createScreeningInfos().forEach { movie.addScreening(it) }
        movies[movieId] = movie
    }

    private fun createScreeningInfos(): List<ScreeningInfoOfMovie> {
        val movieHouse = MovieHouseRepository.findById(1)
        val screeningStartDate = LocalDate.of(2024, 3, 1)
        val screeningEndDate = LocalDate.of(2024, 3, 31)
        return createScreeningDateTimes(
            screeningStartDate,
            screeningEndDate
        ).map { ScreeningInfoOfMovie(it, movieHouse!!) }
    }

    private fun createScreeningDateTimes(
        startDate: LocalDate,
        endDate: LocalDate
    ): List<LocalDateTime> {
        val days = Period.between(startDate, endDate).days

        return (0..days).flatMap { day ->
            val date = startDate.plusDays(day.toLong())
            val startTime = if (date.isWeekend()) LocalTime.of(9, 0) else LocalTime.of(10, 0)
            val times = startTime.getAllTimesUntilMidNight(2)
            times.map { LocalDateTime.of(date, it) }
        }
    }

    fun findById(id: Long): Movie? {
        return movies[id]
    }

    fun findAll(): List<Movie> {
        return movies.values.toList()
    }
}

private fun LocalDate.isWeekend(): Boolean =
    this.dayOfWeek == DayOfWeek.SATURDAY || this.dayOfWeek == DayOfWeek.SUNDAY

private fun LocalTime.getAllTimesUntilMidNight(intervalHour: Long): List<LocalTime> {
    val times: MutableList<LocalTime> = mutableListOf()
    var time = this
    while (time >= this) {
        times.add(time)
        time = time.plusHours(intervalHour)
    }
    return times
}
