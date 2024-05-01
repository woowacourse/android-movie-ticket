package woowacourse.movie.feature.main.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.main.ui.ScreeningItem

class AdvertisementViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val ivAdvertisement: ImageView = view.findViewById(R.id.iv_advertisement)

    fun bind(adModel: ScreeningItem.AdModel) {
        ivAdvertisement.setImageResource(adModel.resId)
    }
}
