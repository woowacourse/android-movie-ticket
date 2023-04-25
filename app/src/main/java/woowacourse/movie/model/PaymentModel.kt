package woowacourse.movie.model

private const val ON_SITE_STRING = "현장 결제"

enum class PaymentModel(val string: String) {
    ON_SITE(ON_SITE_STRING);

    companion object {
        private const val FIND_NULL_ERROR = "해당하는 enum class를 찾을 수 없습니다."

        fun of(string: String): PaymentModel {
            return values().firstOrNull { it.string == string }
                ?: throw IllegalArgumentException(FIND_NULL_ERROR)
        }
    }
}
