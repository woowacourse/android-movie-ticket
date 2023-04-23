package woowacourse.movie.model.main

import android.view.View
import woowacourse.movie.ui.main.adapter.AdvertisementViewHolder
import woowacourse.movie.ui.main.adapter.MainViewHolder
import woowacourse.movie.ui.main.adapter.MovieViewHolder

enum class MainViewType {
    CONTENT {
        override fun makeViewHolder(view: View): MainViewHolder = MovieViewHolder(view)
    },
    ADVERTISEMENT {
        override fun makeViewHolder(view: View): MainViewHolder = AdvertisementViewHolder(view)
    }, ;

    abstract fun makeViewHolder(view: View): MainViewHolder

    companion object {
        fun getMainViewType(ordinal: Int): MainViewType {
            return values()[ordinal]
        }
    }
}
