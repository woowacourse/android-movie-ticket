package woowacourse.movie.ui.view.booking

interface BookingSummaryContract {
    interface View {
        fun showBookingSummary()
    }

    interface Presenter {
        fun loadBookingSummary()
    }
}
