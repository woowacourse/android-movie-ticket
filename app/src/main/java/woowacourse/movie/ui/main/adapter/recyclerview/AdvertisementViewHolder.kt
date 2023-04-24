package woowacourse.movie.ui.main.adapter.recyclerview

import android.content.Intent
import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.model.main.AdvertisementUiModel
import woowacourse.movie.model.main.MainData
import woowacourse.movie.ui.main.adapter.MainViewType

class AdvertisementViewHolder(view: View) : MainViewHolder(view) {
    private val poster: ImageView = view.findViewById(R.id.imageAdvertisement)

    override val mainViewType: MainViewType = MainViewType.ADVERTISEMENT
    private lateinit var advertisement: AdvertisementUiModel

    override fun onBind(data: MainData) {
        advertisement = data as AdvertisementUiModel
        poster.setImageResource(advertisement.image)
    }

    fun setAdvertisementClick(clickAd: (Intent) -> Unit) {
        view.setOnClickListener {
            clickAd(advertisement.getIntent())
        }
    }
}
