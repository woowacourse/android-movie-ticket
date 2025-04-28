package woowacourse.movie.presentation.view.movies.adapter

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdViewHolder(
    parentView: View,
) : RecyclerView.ViewHolder(parentView) {
    private val ivAd = parentView.findViewById<ImageView>(R.id.iv_ad)

    fun bind(
        @DrawableRes resId: Int,
    ) {
        ivAd.setImageResource(resId)
    }
}
