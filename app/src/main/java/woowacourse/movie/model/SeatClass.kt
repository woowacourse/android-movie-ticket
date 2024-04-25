package woowacourse.movie.model

enum class SeatClass(val price: Int) {
    B_CLASS(10_000),
    A_CLASS(12_000),
    S_CLASS(15_000),
    ;

    companion object {
        fun determineSeatGrade(position: Int): SeatClass {
            return when (position) {
                1, 2 -> B_CLASS
                3, 4 -> S_CLASS
                else -> A_CLASS
            }
        }
    }
}
