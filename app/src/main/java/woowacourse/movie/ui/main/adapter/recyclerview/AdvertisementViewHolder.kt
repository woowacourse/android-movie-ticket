package woowacourse.movie.ui.main.adapter.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.model.main.AdvertisementUiModel
import woowacourse.movie.model.main.MainData
import woowacourse.movie.ui.main.adapter.MainViewType

class AdvertisementViewHolder(parent: ViewGroup, clickAd: (Int) -> Unit) :
    MainViewHolder(makeView(parent)) {
    private val poster: ImageView = view.findViewById(R.id.imageAdvertisement)
    override val mainViewType: MainViewType = MainViewType.ADVERTISEMENT

    init {
        view.setOnClickListener { clickAd(adapterPosition) }
    }

    override fun onBind(data: MainData) {
        val advertisement = data as AdvertisementUiModel
        poster.setImageResource(advertisement.image)
    }

    companion object {
        private fun makeView(parent: ViewGroup): View {
            val layoutInflater = LayoutInflater.from(parent.context)
            return layoutInflater.inflate(R.layout.advertisement_list_item, parent, false)
        }
    }
}
