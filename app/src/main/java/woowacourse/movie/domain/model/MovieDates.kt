package woowacourse.movie.domain.model

@JvmInline
value class MovieDates(
    val value: List<MovieDate>,
) {
    constructor(startDate: MovieDate, endDate: MovieDate) : this(
        value =
            generateSequence(startDate) { date ->
                if (date.value.isBefore(endDate.value)) {
                    MovieDate(date.value.plusDays(1))
                } else {
                    null
                }
            }.toList(),
    )
}
