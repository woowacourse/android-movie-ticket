package woowacourse.movie.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.data.Movie
import woowacourse.movie.databinding.ItemMovieBinding

class MovieListAdapter(
    private val movies: List<Movie>,
    private val onBookClickListener: OnBookClickListener,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val context = parent?.context
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        with(movies[position]) {
            binding.ivPoster.setImageResource(thumbnail)
            binding.tvTitle.text = title
            binding.tvDate.text = context?.getString(
                R.string.movie_release_date,
                startDate.formattedDate,
                endDate.formattedDate
            )
            binding.tvRunningTime.text =
                context?.getString(R.string.movie_running_time, runningTime)
            binding.btnBook.setOnClickListener { onBookClickListener.onClick(this) }
        }
        return binding.root
    }

    interface OnBookClickListener {
        fun onClick(item: Movie)
    }
}
