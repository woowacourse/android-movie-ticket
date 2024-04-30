package woowacourse.movie.model

import woowacourse.movie.model.MovieGrade.Companion.judgeGradeByRow

data class MovieSeat(val row: Int, val column: Int) {
    val grade: MovieGrade = judgeGradeByRow(row)
}
