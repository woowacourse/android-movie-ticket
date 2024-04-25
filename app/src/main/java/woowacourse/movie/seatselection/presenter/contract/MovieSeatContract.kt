package woowacourse.movie.seatselection.presenter.contract

interface MovieSeatContract {
    interface View {
        fun displayDialog()

        fun navigateToResultView()
    }

    interface Presenter {
        //
    }
}
