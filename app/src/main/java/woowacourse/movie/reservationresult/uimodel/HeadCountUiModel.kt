package woowacourse.movie.reservationresult.uimodel

data class HeadCountUiModel(val count: String) {
    constructor(count: Int) : this("일반 " + count.toString() + "명")
}
