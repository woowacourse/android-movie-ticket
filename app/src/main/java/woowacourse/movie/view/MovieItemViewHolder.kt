package woowacourse.movie.view

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.databinding.MovieItemBinding

class MovieItemViewHolder(
    val binding: MovieItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    val poster: ImageView = binding.moviePoster
    val title: TextView = binding.movieTitle
    val screeningStartDate: TextView = binding.movieScreeningDate
    val runningTime: TextView = binding.movieRunningTime
    val reserveNow: Button = binding.reserveNowButton
}
