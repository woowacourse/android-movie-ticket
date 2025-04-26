package woowacourse.movie.view.movies

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdvertisementViewHolder(
    view: View,
    advertisementClickListener: () -> Unit,
) : RecyclerView.ViewHolder(view) {
    init {
        view.findViewById<ConstraintLayout>(R.id.cl_advertisement).setOnClickListener {
            advertisementClickListener.invoke()
        }
    }
}
