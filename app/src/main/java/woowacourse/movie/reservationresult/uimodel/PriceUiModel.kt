package woowacourse.movie.reservationresult.uimodel

data class PriceUiModel(
    val price: String,
) {
    constructor(price: Int) : this(String.format("%,d", price) + "원 (현장 결제)")
}
