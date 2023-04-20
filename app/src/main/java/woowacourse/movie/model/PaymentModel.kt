package woowacourse.movie.model

import android.content.res.Resources
import woowacourse.movie.R

enum class PaymentModel(val string: String) {
    ON_SITE(Resources.getSystem().getString(R.string.payment_on_site));

    companion object {

        private const val FIND_NULL_ERROR = "해당하는 enum class를 찾을 수 없습니다."

        fun of(string: String): PaymentModel {
            return PaymentModel.values().firstOrNull { it.string == string }
                ?: throw IllegalArgumentException(FIND_NULL_ERROR)
        }
    }
}
