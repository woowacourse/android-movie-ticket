package woowacourse.movie.contract

interface ReservationContract {
    interface Presenter {
        fun presentTitle()
    }

    interface View {
        fun setTitle(title: String)
    }
}
