package woowacourse.movie.data

enum class IdError(val message: String) {
    NO_MOVIE("%d : id에 해당 하는 영화가 없습니다."),
    NO_RESERVATION("%d : id에 해당 하는 예약 내역이 없습니다."),
}
