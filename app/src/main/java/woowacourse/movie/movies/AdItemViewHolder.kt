package woowacourse.movie.movies

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdItemViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.advertisement_imageview)

    fun bind() {
        image.setImageResource(R.drawable.img_ad)
    }
}
