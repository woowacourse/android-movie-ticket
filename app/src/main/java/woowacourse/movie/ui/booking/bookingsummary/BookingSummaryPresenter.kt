package woowacourse.movie.ui.booking.bookingsummary

class BookingSummaryPresenter(
    private val view: BookingSummaryContract.View,
) : BookingSummaryContract.Presenter {
    override fun loadBookingSummary() {
        view.showBookingSummary()
    }
}
