package woowacourse.movie.model

private const val ON_SITE_STRING = "현장 결제"
fun Payment.toUI(): String {
    return when (this) {
        Payment.ON_SITE -> ON_SITE_STRING
    }
}

enum class Payment {
    ON_SITE
}
