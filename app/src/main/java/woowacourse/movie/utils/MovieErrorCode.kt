package woowacourse.movie.utils

enum class MovieErrorCode(val code: Int, val msg: String) {
    INVALID_MOVIE_ID(-1, "올바르지 않은 MOVIE ID 입니다"),
    INVALID_SCREEN_DATE_TIME_ID(-2, "올바르지 않은 SCREEN DATE TIME ID 입니다"),
    INVALID_SEAT_ID(-3, "올바르지 않은 SEAT ID 입니다"),
}
