package woowacourse.movie.utils

object MovieIntentConstants {
    const val EXTRA_MOVIE_ID = "movieId"
    const val EXTRA_MOVIE_RESERVATION_COUNT = "movieReservationCount"
    const val EXTRA_MOVIE_SCREEN_DATE_TIME_ID = "movieScreenDateTimeId"
    const val EXTRA_PARSED_MOVIE_RESERVATION_DATE = "parsedMovieReservationDate"
    const val EXTRA_PARSED_MOVIE_RESERVATION_TIME = "parsedMovieReservationTime"
    const val EXTRA_MOVIE_SEATS_ID_LIST = "movieSeatsIdList"
    const val EXTRA_MOVIE_TOTAL_PRICE = "movieTotalPrice"
    const val EXTRA_MOVIE_SELECTED_SEAT_INDEXES = "movieSelectedSeatIndexes"

    const val NOT_FOUND_MOVIE_ID: Long = -1
    const val NOT_FOUND_MOVIE_RESERVATION_COUNT: Int = -1
    const val NOT_FOUND_MOVIE_SCREEN_DATE_TIME_ID: Long = -1
    const val NOT_FOUND_MOVIE_TOTAL_PRICE: Int = -1
}
