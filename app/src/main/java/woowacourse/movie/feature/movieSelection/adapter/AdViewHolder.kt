package woowacourse.movie.feature.movieSelection.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image: ImageView = view.findViewById(R.id.ad)

    fun bind() {
        image.setImageResource(R.drawable.ad)
    }
}
