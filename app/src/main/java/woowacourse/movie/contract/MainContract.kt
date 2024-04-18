package woowacourse.movie.contract

import android.content.Intent

interface MainContract {
    interface View {
        fun setUpViews(position: Int)
    }

    interface Presenter {
        fun putData(
            intent: Intent,
            position: Int,
        )
    }
}
