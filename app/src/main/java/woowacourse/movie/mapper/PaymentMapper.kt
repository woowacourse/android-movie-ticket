package woowacourse.movie.mapper

import com.example.domain.model.Payment
import woowacourse.movie.model.PaymentModel

fun Payment.toPaymentModel(): PaymentModel {
    return when (this) {
        Payment.ON_SITE -> PaymentModel.ON_SITE
    }
}

fun PaymentModel.toPayment(): Payment {
    return when (this) {
        PaymentModel.ON_SITE -> Payment.ON_SITE
    }
}
