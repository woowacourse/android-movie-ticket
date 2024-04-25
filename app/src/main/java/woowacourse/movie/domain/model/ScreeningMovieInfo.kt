package woowacourse.movie.domain.model

class ScreeningMovieInfo(
    val title: String,
    screeningInfo: ScreeningInfo,
) {
    val dateTime = ScreeningDateTime(screeningInfo)

    fun changeDate(
        year: Int,
        month: Int,
        day: Int,
    ) {
        dateTime.changeScreeningDate(year, month, day)
    }

    fun changeTime(
        hour: Int,
        minute: Int,
    ) {
        dateTime.changeScreeningTime(hour, minute)
    }
}
