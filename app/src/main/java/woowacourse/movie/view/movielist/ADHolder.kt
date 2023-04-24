package woowacourse.movie.view.movielist

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.viewmodel.MovieListData.ADData

class ADHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ad: ImageView = view.findViewById(R.id.advertisement)
    fun bind(adData: ADData) {
        ad.setBackgroundResource(adData.id)
    }
}
