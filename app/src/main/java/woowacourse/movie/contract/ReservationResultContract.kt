package woowacourse.movie.contract

import android.content.Intent

interface ReservationResultContract {
    interface View {
        fun fetchData(intent: Intent)
    }

    interface Presenter
}
