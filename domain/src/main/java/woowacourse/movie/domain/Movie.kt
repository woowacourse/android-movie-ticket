package woowacourse.movie.domain

class Movie(
    val id: Long,
    val title: String,
    val runningTime: Minute,
    val summary: String
) {
    private val _screeningInfos: MutableSet<ScreeningInfoOfMovie> = mutableSetOf()
    val screeningInfos: Set<ScreeningInfoOfMovie>
        get() = _screeningInfos.toSet()

    fun addScreening(screeningInfo: ScreeningInfoOfMovie) {
        _screeningInfos.add(screeningInfo)
    }
}
