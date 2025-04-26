package woowacourse.movie.ui.view.booking

interface SeatsSelectionContract {
    interface View {
        fun showBookingConfirmDialog()

        fun navigateToBookingSummary()
    }

    interface Presenter {
        fun onConfirm()
    }
}
