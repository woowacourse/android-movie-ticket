package woowacourse.movie.ui.main.adapter

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.ui.model.AdvertisementUiModel

class AdvertisementViewHolder(view: View) :
    BaseViewHolder<AdvertisementUiModel>(view) {
    private val advertisementImageView = view.findViewById<ImageView>(R.id.iv_advertisement)

    override fun bind(item: AdvertisementUiModel) {
        advertisementImageView.setImageResource(item.content)
    }
}
