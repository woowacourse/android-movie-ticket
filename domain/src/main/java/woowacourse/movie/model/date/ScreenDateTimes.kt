package woowacourse.movie.model.date

data class ScreenDateTimes(val dateTimes: List<ScreenDateTime>) {
    val size = dateTimes.size

    init {
        require(dateTimes.isNotEmpty()) { "상영 일정이 존재하지 않습니다." }
    }

    constructor(vararg screenDateTimes: ScreenDateTime) : this(screenDateTimes.toList())

    operator fun get(index: Int): ScreenDateTime = dateTimes[index]

    fun first(): ScreenDateTime = dateTimes.first()

    fun last(): ScreenDateTime = dateTimes.last()
}
