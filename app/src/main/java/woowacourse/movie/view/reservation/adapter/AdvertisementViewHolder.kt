package woowacourse.movie.view.reservation.adapter

import android.view.View
import android.widget.ImageView
import woowacourse.movie.R
import woowacourse.movie.domain.reservation.Advertisement
import woowacourse.movie.domain.reservation.ScreeningContent
import woowacourse.movie.view.reservation.AdvertisementImage.advertisementResourceId

class AdvertisementViewHolder(
    view: View,
) : ScreeningContentViewHolder(view) {
    private val advertisementView: ImageView =
        view.findViewById(R.id.iv_item_advertisement)
            ?: error(ERROR_MESSAGE_NO_VIEW_ID.format("iv_item_advertisement"))

    override fun bind(item: ScreeningContent) {
        val advertisement: Advertisement = item as? Advertisement ?: error("")
        advertisementView.setImageResource(advertisement.advertisementResourceId())
        advertisementView.contentDescription = advertisement.description
    }

    companion object {
        private const val ERROR_MESSAGE_NO_VIEW_ID = "Can't find view id of %s"
    }
}
