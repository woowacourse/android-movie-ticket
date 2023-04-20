package woowacourse.movie.view

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.databinding.MovieAdItemBinding
import woowacourse.movie.view.model.MovieListModel

class MovieAdViewHolder(
    binding: MovieAdItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val banner: ImageView = binding.adImageview

    fun bind(ad: MovieListModel.MovieAdModel) {
        banner.setImageResource(ad.banner)
    }
}
