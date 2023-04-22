package woowacourse.movie.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.model.AdvertisementViewModel

class AdvertisementItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView

    init {
        image = view.findViewById(R.id.advertisement_image)
    }

    fun bind(advertisementViewModel: AdvertisementViewModel, onClickEvent: (AdvertisementViewModel) -> Unit) {
        image.setImageResource(advertisementViewModel.image)
        image.setOnClickListener {
            onClickEvent(advertisementViewModel)
        }
    }
}
