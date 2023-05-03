package woowacourse.movie.view.activities.screeninglist

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdvertisementItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val advertisementImageView: ImageView = view.findViewById(R.id.advertisement_iv)

    fun bind(advertisementUIState: ScreeningListViewItemUIState.AdvertisementUIState) {
        advertisementImageView.setImageResource(advertisementUIState.advertisementImage)
    }
}
