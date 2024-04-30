package woowacourse.movie.model

enum class MovieGrade(val price: Int) {
    B_GRADE(10000),
    S_GRADE(15000),
    A_GRADE(12000), ;

    companion object {
        fun judgeGradeByRow(row: Int): MovieGrade {
            return when (row) {
                in 0..1 -> B_GRADE
                in 2..3 -> S_GRADE
                else -> A_GRADE
            }
        }
    }
}
