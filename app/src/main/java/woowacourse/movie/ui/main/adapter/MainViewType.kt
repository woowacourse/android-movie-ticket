package woowacourse.movie.ui.main.adapter

import android.view.View
import woowacourse.movie.ui.main.adapter.listview.AdvertisementViewHolder
import woowacourse.movie.ui.main.adapter.listview.MainViewHolder
import woowacourse.movie.ui.main.adapter.listview.MovieViewHolder

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
