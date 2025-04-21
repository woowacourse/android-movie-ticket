package woowacourse.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JvmInline
value class TicketCount(
    val value: Int,
) : Parcelable {
    init {
        require(value >= 0) { "티켓 수는 음수가 될 수 없습니다." }
    }

    fun increase(): TicketCount = TicketCount(value + 1)

    fun decrease(): TicketCount = if (value > 0) TicketCount(value - 1) else this
}
