package woowacourse.movie.selectseat.uimodel

data class PriceUiModel(
    val price: String,
) {
    constructor(price: Int) : this(String.format("%,d", price) + "원")
}
