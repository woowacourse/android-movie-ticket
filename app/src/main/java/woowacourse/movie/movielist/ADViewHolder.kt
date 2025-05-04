package woowacourse.movie.movielist

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class ADViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val adImageView: ImageView = view.findViewById(R.id.ad_item)

        fun bind(@DrawableRes image: Int) {
            adImageView.setImageResource(image)
        }
    }