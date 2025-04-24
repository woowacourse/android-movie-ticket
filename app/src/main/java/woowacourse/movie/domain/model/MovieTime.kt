package woowacourse.movie.domain.model

import java.time.LocalTime

@JvmInline
value class MovieTime(
    val value: LocalTime = LocalTime.now(),
) {
    constructor(hour: Int, minute: Int) : this(LocalTime.of(hour, minute))

    companion object {
        fun from(
            hour: Int,
            minute: Int,
        ): MovieTime = MovieTime(LocalTime.of(hour, minute))

        fun getMovieTimes(dateType: DateType): List<MovieTime> =
            when (dateType) {
                DateType.WEEKDAY -> weekdaysMovieTimes
                DateType.WEEKEND -> weekendsMovieTimes
            }

        private val weekdaysMovieTimes: List<MovieTime> =
            listOf(
                MovieTime(10, 0),
                MovieTime(12, 0),
                MovieTime(14, 0),
                MovieTime(16, 0),
                MovieTime(18, 0),
                MovieTime(20, 0),
                MovieTime(22, 0),
                MovieTime(0, 0),
            )

        private val weekendsMovieTimes: List<MovieTime> =
            listOf(
                MovieTime(9, 0),
                MovieTime(11, 0),
                MovieTime(13, 0),
                MovieTime(15, 0),
                MovieTime(17, 0),
                MovieTime(19, 0),
                MovieTime(21, 0),
                MovieTime(23, 0),
            )
    }
}
