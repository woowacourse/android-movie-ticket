package woowacourse.movie.presentation.uimodel

import android.os.Parcel
import android.os.Parcelable
import woowacourse.movie.domain.model.reservation.MovieTicket
import woowacourse.movie.presentation.view.SeatSelectionActivity.Companion.SEAT_COL_START_VALUE
import woowacourse.movie.presentation.view.SeatSelectionActivity.Companion.SEAT_POSITION_TEXT_FORMAT
import woowacourse.movie.presentation.view.SeatSelectionActivity.Companion.SEAT_ROW_START_VALUE
import java.time.format.DateTimeFormatter

data class MovieTicketUiModel(
    val ticketId: Long,
    val title: String,
    val screeningDate: String,
    val startTime: String,
    val endTime: String,
    val runningTime: Int,
    val reservationCount: Int,
    val totalPrice: Int,
    val selectedSeats: List<String>,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createStringArrayList() ?: listOf(),
    )

    override fun writeToParcel(
        parcel: Parcel,
        flags: Int,
    ) {
        parcel.writeLong(ticketId)
        parcel.writeString(title)
        parcel.writeString(screeningDate)
        parcel.writeString(startTime)
        parcel.writeString(endTime)
        parcel.writeInt(runningTime)
        parcel.writeInt(reservationCount)
        parcel.writeInt(totalPrice)
        parcel.writeStringList(selectedSeats)
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor(movieTicket: MovieTicket) : this(
        movieTicket.ticketId,
        movieTicket.screeningMovieInfo.title,
        movieTicket.screeningMovieInfo.dateTime.screeningDate.date.format(DEFAULT_DATE_FORMAT),
        movieTicket.screeningMovieInfo.dateTime.screeningDate.screeningTime.startTime.format(
            DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT),
        ),
        movieTicket.screeningMovieInfo.dateTime.screeningDate.screeningTime.getEndTime().format(
            DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT),
        ),
        movieTicket.screeningMovieInfo.dateTime.screeningInfo.runningTime,
        movieTicket.reservationInfo.reservationCount,
        movieTicket.reservationInfo.selectedSeats.totalPrice(),
        movieTicket.reservationInfo.selectedSeats.seats.map { seat ->
            String.format(
                SEAT_POSITION_TEXT_FORMAT,
                SEAT_ROW_START_VALUE + seat.row,
                SEAT_COL_START_VALUE + seat.col,
            )
        },
    )

    companion object CREATOR : Parcelable.Creator<MovieTicketUiModel> {
        val DEFAULT_DATE_FORMAT: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE
        const val DEFAULT_TIME_FORMAT = "HH:mm"

        override fun createFromParcel(parcel: Parcel): MovieTicketUiModel {
            return MovieTicketUiModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieTicketUiModel?> {
            return arrayOfNulls(size)
        }
    }
}
