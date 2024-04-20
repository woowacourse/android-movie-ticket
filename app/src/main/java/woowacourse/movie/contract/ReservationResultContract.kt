package woowacourse.movie.contract

import android.content.Intent

interface ReservationResultContract {
    interface View {
        fun setUpView(
            title: String,
            screenDate: String,
            count: Int,
            price: Int,
        )
    }

    interface Presenter {
        fun fetchReservationDetail(intent: Intent)
    }
}
