package movie

import movie.screening.ScreeningDate

class MovieSchedule(
    private val movie: Movie,
    private val screeningDate: ScreeningDate,
) {
    fun getScreeningDate(): List<String> = screeningDate.getScreeningDate()
}
