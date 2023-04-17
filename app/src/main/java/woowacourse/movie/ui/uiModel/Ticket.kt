package woowacourse.movie.ui.uiModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.discountpolicy.DateTimeTimeDiscountAdapter
import java.time.LocalDateTime

@Parcelize
data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
) : Parcelable {

    fun getPaymentAmount() =
        DateTimeTimeDiscountAdapter(bookedDateTime).discount(TICKET_PRICE) * count

    companion object {
        private const val TICKET_PRICE = 13_000
    }
}
