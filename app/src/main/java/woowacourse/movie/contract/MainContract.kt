package woowacourse.movie.contract

import android.content.Intent

interface MainContract {
    interface View {
    }

    interface Presenter {
        fun putData(
            intent: Intent,
            position: Int,
        )
    }
}
