package woowacourse.movie.model

import woowacourse.movie.R

enum class MovieGrade(val price: Int, val color: Int) {
    B_GRADE(10000, R.color.b_grade),
    S_GRADE(15000, R.color.s_grade),
    A_GRADE(12000, R.color.a_grade), ;

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
