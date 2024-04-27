package woowacourse.movie.feature.main.ui

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdvertisementViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val ivAdvertisement: ImageView = view.findViewById(R.id.iv_advertisement)

    fun bind(imgRes: Int) {
        ivAdvertisement.setImageResource(imgRes)
    }
}
