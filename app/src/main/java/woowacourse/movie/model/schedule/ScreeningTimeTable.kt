package woowacourse.movie.model.schedule

interface ScreeningTimeTable {
    fun getScreeningTimes(): List<ScreeningDateTime>
}
