package woowacourse.movie.presentation.screen.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.utils.toDrawableIdByName

class AdViewHolder(
    view: View,
    private val context: Context,
) : RecyclerView.ViewHolder(view) {
    private val imageView: ImageView = view.findViewById(R.id.ad_image)

    fun bind(ad: String) {
        val imageResource = ad.toDrawableIdByName(context)
        imageResource?.let { imageView.setImageResource(it) }
    }
}
