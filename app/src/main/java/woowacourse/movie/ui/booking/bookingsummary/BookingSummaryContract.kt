package woowacourse.movie.ui.booking.bookingsummary

interface BookingSummaryContract {
    interface View {
        fun showBookingSummary()
    }

    interface Presenter {
        fun loadBookingSummary()
    }
}
