package woowacourse.movie.ui.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R

class AdvertisementHolder(private val view: View) : ViewHolder(view) {
    private val ivAdvertisement: ImageView = view.findViewById(R.id.iv_advertisement)

    fun bind(image: Int, onAdClickListener: () -> Unit) {
        ivAdvertisement.setImageResource(image)
        ivAdvertisement.setOnClickListener {
            onAdClickListener()
        }
    }
}
