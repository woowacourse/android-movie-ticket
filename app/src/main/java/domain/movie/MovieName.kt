package domain.movie

@JvmInline
value class MovieName(val value: String) {
    init {
        require(value.length >= MINIMUM_LENGTH) {
            MOVIE_NAME_LENGTH_ERROR
        }
    }

    companion object {

        private const val MOVIE_NAME_LENGTH_ERROR = "[ERROR] 영화 이름의 길이는 1이상이다."
        private const val MINIMUM_LENGTH = 1
    }
}
