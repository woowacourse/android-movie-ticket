package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.discountpolicy.DateTimeTimeDiscountAdapter
import java.time.LocalDateTime

@Parcelize
data class Reservation(
    val movie: String,
    val bookedDateTime: LocalDateTime,
    val count: Int,
    val total: Int,
) : Parcelable {

    fun getPaymentAmount() =
        DateTimeTimeDiscountAdapter(bookedDateTime).discount(total) * count

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
