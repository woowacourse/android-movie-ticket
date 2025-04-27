package woowacourse.movie.activity.reservation

import android.widget.TextView
import woowacourse.movie.dto.SeatDto

interface ReservationSeatContract {
    interface View {
        fun initSeatTable(seats: List<SeatDto>)

        fun setButtonState(state: Boolean)

        fun setWhenSeatSelected(
            view: TextView,
            seat: SeatDto,
        )

        fun setWhenSeatDisSelected(
            view: TextView,
            seat: SeatDto,
        )
    }

    interface Presenter {
        fun initSeatTable()

        fun setWhenSeatSelected(
            view: TextView,
            seat: SeatDto,
        )

        fun setWhenSeatDisSelected(
            view: TextView,
            seat: SeatDto,
        )

        fun setButtonState(
            selectedMember: Int,
            allMember: Int,
        )
    }
}
