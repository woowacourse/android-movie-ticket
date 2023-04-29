package woowacourse.movie.item

import androidx.annotation.LayoutRes
import woowacourse.movie.R

enum class ItemType(@LayoutRes val layoutRes: Int) {
    ADVERTISING(R.layout.item_advertising),
    MOVIE(R.layout.item_movie);

    companion object {
        fun of(ordinal: Int): ItemType = values()[ordinal]
    }
}
