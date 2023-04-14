package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Movie

class MovieListAdapter(
    private val context: Context,
    private val movies: List<Movie>
) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val convertView: View
        var viewHolder: MovieItemViewHolder? = null
        if (view == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
            viewHolder = MovieItemViewHolder(
                convertView.findViewById(R.id.movie_poster),
                convertView.findViewById(R.id.movie_title),
                convertView.findViewById(R.id.movie_screening_date),
                convertView.findViewById(R.id.movie_running_time),
                convertView.findViewById(R.id.reserve_now_button)
            )
        } else {
            convertView = view
        }
        val movie = movies[position]
        viewHolder?.let {
            initMovieItemView(viewHolder, movie)
        }
        return convertView
    }

    private fun initMovieItemView(
        viewHolder: MovieItemViewHolder,
        movie: Movie
    ) {
        viewHolder.apply {
            poster.setImageResource(movie.posterResourceId)
            title.text = movie.title
            screeningStartDate.text =
                context.resources.getString(R.string.screening_date_format).format(
                    movie.screeningStartDate.format(DATE_FORMATTER),
                    movie.screeningEndDate.format(DATE_FORMATTER)
                )
            runningTime.text = context.resources.getString(R.string.running_time_format)
                .format(movie.runningTime.value)
            reserveNow.setOnClickListener {
                val intent = Intent(context, ReservationActivity::class.java)
                intent.putExtra(MOVIE, movie)
                startActivity(context, intent, null)
            }
        }
    }

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    companion object {
        const val MOVIE = "MOVIE"
    }
}
