package woowacourse.movie.domain.model

data class Seat(
    private val position: Position,
    private val grade: Grade,
) {
    fun price() = grade.price
}
