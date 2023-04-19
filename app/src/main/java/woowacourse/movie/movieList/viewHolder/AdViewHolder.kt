package woowacourse.movie.movieList.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import model.AdModel
import woowacourse.movie.R

class AdViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val imageView: ImageView = view.findViewById(R.id.ad_banner)
    fun bind(
        adModel: AdModel,
        onAdClick: (AdModel) -> Unit,
    ) {
        imageView.setImageResource(adModel.image)
        imageView.setOnClickListener { onAdClick(adModel) }
    }
}
