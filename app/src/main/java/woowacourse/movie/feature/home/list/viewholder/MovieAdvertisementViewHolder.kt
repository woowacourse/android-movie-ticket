package woowacourse.movie.feature.home.list.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.home.ui.MovieAdvertisementUiModel

class MovieAdvertisementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val advertisementImage: ImageView by lazy { itemView.findViewById(R.id.advertisement_image) }

    fun bind(movieAdvertisementUiModel: MovieAdvertisementUiModel) {
        advertisementImage.setImageResource(movieAdvertisementUiModel.imageId)
    }
}
