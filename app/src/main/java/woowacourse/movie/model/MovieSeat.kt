package woowacourse.movie.model

import woowacourse.movie.model.MovieGrade.Companion.judgeGradeByRow

class MovieSeat(val row: Char, val column: Int) {
    val grade: MovieGrade = judgeGradeByRow(row)
}
