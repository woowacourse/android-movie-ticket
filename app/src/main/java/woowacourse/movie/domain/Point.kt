package woowacourse.movie.domain

data class Point(
    val x: Int,
    val y: Int,
) {
    fun price(): Int {
        return when (x) {
            0, 1 -> 10000
            2, 3 -> 12000
            else -> 15000
        }
    }
}
