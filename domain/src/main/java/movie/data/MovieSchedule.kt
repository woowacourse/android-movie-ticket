package movie.data

import movie.screening.ScreeningDate

class MovieSchedule(
    private val screeningDate: ScreeningDate,
) {
    fun getScreeningDate(): List<String> = screeningDate.getScreeningDate()
}
