package woowacourse.movie.model

enum class MovieGrade(val price: Int) {
    B_GRADE(10000),
    S_GRADE(15000),
    A_GRADE(12000), ;

    companion object {
        fun judgeGradeByRow(row: Char): MovieGrade {
            return when (row) {
                in 'A'..'B' -> B_GRADE
                in 'C'..'D' -> S_GRADE
                else -> A_GRADE
            }
        }
    }
}
