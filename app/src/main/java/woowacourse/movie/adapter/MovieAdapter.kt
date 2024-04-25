package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.format.format
import woowacourse.movie.model.movie.Movie

class MovieAdapter(
    private val screeningListView: MovieListContract.View,
) :
    BaseAdapter(),
        MovieAdapterContract.Model,
        MovieAdapterContract.View {
    private var movies: List<Movie> = listOf()

    override fun setMovies(screenings: List<Movie>) {
        this.movies = screenings
    }

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val listItemView =
            convertView
                ?: LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_list_item, parent, false)
        val viewHolder =
            if (convertView != null) {
                listItemView.tag as ViewHolder
            } else {
                val viewHolder = ViewHolder(listItemView)
                listItemView.tag = viewHolder
                viewHolder
            }
        val movie: Movie = getItem(position)
        viewHolder.bind(movie, position)
        return listItemView
    }

    override fun notifyItemClicked(position: Int) {
        screeningListView.navigateToMovieDetail(position)
    }

    inner class ViewHolder(
        private val title: TextView,
        private val screeningDate: TextView,
        private val runningTime: TextView,
        private val detailButton: Button,
    ) {
        constructor(itemView: View) : this(
            title = itemView.findViewById<TextView>(R.id.movie_title),
            screeningDate = itemView.findViewById<TextView>(R.id.movie_screening_date),
            runningTime = itemView.findViewById<TextView>(R.id.movie_item_running_time),
            detailButton = itemView.findViewById<Button>(R.id.movie_details_button),
        )

        fun bind(
            movie: Movie,
            position: Int,
        ) {
            val movieDetail = movie.movieDetail
            title.text = movieDetail.title.format()
            screeningDate.text = "상영일: ${movie.screeningDate.format()}"
            runningTime.text = "러닝타임: ${movieDetail.runningTime.format()}"
            detailButton.setOnClickListener {
                notifyItemClicked(position)
            }
        }
    }
}
