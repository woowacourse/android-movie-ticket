package woowacourse.movie.presentation.screen.movie.adapter

import androidx.annotation.DrawableRes
import woowacourse.movie.model.Movie

sealed class ScreenView {
    data class AdView(@DrawableRes val res: Int) : ScreenView() {
        companion object {
            const val id = 0
        }
    }

    data class MovieView(val item: Movie) : ScreenView() {
        companion object {
            const val id = 1
        }
    }
}
