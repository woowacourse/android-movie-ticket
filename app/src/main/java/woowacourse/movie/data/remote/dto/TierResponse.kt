package woowacourse.movie.data.remote.dto

enum class TierResponse(val price: Int) {
    S(15_000),
    A(12_000),
    B(10_000),
}
