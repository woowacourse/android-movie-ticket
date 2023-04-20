package woowacourse.movie.view

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.databinding.MovieAdItemBinding

class MovieAdViewHolder(
    binding: MovieAdItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    val banner: ImageView = binding.adImageview
}
