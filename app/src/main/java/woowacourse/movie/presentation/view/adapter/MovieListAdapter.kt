package woowacourse.movie.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.databinding.ItemMovieBinding
import woowacourse.movie.presentation.model.Movie

class MovieListAdapter(
    private val movies: List<Movie>,
    private val onBookBtnClick: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View? = convertView
        val viewHolder: ViewHolder

        if (view == null) {
            val binding =
                ItemMovieBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
            view = binding.root
            binding.btnBook.setOnClickListener { onBookBtnClick(getItem(position)) }
            viewHolder = ViewHolder(binding)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.bind(movies[position])
        return view
    }

    private class ViewHolder(private val binding: ItemMovieBinding) {
        private val ivPoster = binding.ivPoster
        private val tvTitle = binding.tvTitle
        private val tvDate = binding.tvDate
        private val tvRunningTime = binding.tvRunningTime

        fun bind(item: Movie) {
            val context = binding.root.context
            with(item) {
                ivPoster.setImageResource(thumbnail)
                tvTitle.text = title
                tvDate.text = context.getString(
                    R.string.movie_release_date,
                    startDate.formattedDate,
                    endDate.formattedDate
                )
                tvRunningTime.text =
                    context.getString(R.string.movie_running_time, runningTime)
            }
        }
    }

    interface OnBookClickListener {
        fun onClick(item: Movie)
    }
}
