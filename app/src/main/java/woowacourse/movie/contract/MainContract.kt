package woowacourse.movie.contract

import android.content.Intent

interface MainContract {
    interface View {
        fun titleTextView(position: Int)

        fun screenDateTextView(position: Int)

        fun runningTimeTextView(position: Int)

        fun imageView(position: Int)
    }

    interface Presenter {
        fun putData(
            intent: Intent,
            position: Int,
        )
    }
}
