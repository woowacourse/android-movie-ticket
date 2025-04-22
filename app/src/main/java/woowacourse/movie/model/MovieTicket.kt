package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieTicket(
    val title: String,
    val timeStamp: String,
    val count: Int,
) : Parcelable {
    fun price(): Int = count * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
