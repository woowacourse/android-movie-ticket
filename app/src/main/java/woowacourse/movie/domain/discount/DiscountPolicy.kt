package woowacourse.movie.domain.discount

object DiscountPolicy {

    // early night discount
    const val earlyNightAmount: Int = 2_000
    const val earlyTime: Int = 11
    const val nightTime: Int = 20

    // movie day discount
    const val movieDayPercentage: Double = 0.9
    val movieDays: List<Int> = listOf(10, 20, 30)
}
