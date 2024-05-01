package woowacourse.movie.moviereservation.uimodel

class ScreeningDateTimesUiModel(private val dateTimes: List<ScreeningDateTimeUiModel>) {
    fun screeningTimeOfDate(position: Int): List<String> = dateTimes[position].times.toList()

    fun dates(): List<String> = dateTimes.map { it.date }

    fun defaultTimes(): List<String> {
        return dateTimes.first().times.toList()
    }
}
