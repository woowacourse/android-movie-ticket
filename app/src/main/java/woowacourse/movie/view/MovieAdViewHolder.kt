package woowacourse.movie.view

import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.databinding.MovieAdItemBinding
import woowacourse.movie.view.model.MovieListModel

class MovieAdViewHolder(
    private val binding: MovieAdItemBinding,
    private val onViewClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.adImageview.setOnClickListener {
            onViewClick(adapterPosition)
        }
    }

    fun bind(ad: MovieListModel.MovieAdModel) {
        binding.adImageview.setImageResource(ad.banner)
    }
}
