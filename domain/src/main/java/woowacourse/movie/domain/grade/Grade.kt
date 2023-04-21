package woowacourse.movie.domain.grade

enum class Grade(val price: Int, val rowIndexRange: IntRange) {
    S(15000, 2..3),
    B(10000, 0..1),
    A(12000, 4..4)
}
