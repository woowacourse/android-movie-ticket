package woowacourse.movie.view.movielist

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.data.ADData

class ADHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val ad = view.findViewById<ConstraintLayout>(R.id.advertisement)
    fun bind(adData: ADData, onClickListener: (ADData) -> Unit) {
        ad.setBackgroundResource(adData.id)
        ad.setOnClickListener { onClickListener(adData) }
    }
}
