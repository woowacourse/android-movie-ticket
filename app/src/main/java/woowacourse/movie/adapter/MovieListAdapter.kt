package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie

class MovieListAdapter(
    private val movies: List<Movie>,
    private val onReservationClick: (selectedMovie: Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val currentItem = getItem(position) as? Movie ?: return View(parent.context)
        val view =
            convertView ?: LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        view.tag as? ViewHolder ?: ViewHolder(view)
            .also { view.tag = it }
            .apply { setData(currentItem, onReservationClick) }

        return view
    }

    private class ViewHolder(view: View) {
        val titleTextView: TextView = view.findViewById<TextView>(R.id.movie_title)
        val runningTimeTextView: TextView = view.findViewById<TextView>(R.id.movie_running)
        val screeningDateTextView: TextView = view.findViewById<TextView>(R.id.movie_date)
        val posterImageView: ImageView = view.findViewById<ImageView>(R.id.movie_poster)
        val buttonView: Button = view.findViewById<Button>(R.id.btn_book)

        fun setData(
            movie: Movie,
            onReservationClick: (selectedMovie: Movie) -> Unit,
        ) {
            titleTextView.text = movie.title
            runningTimeTextView.text =
                runningTimeTextView.context.getString(
                    R.string.movie_running_time,
                    movie.runningTime.inWholeMinutes,
                )
            screeningDateTextView.text =
                screeningDateTextView.context.getString(
                    R.string.movie_screening_date,
                    movie.startDateTime.toFormattedString(),
                    movie.endDateTime.toFormattedString(),
                )
            posterImageView.setImageResource(R.drawable.movie_poster)
            buttonView.setOnClickListener {
                onReservationClick(movie)
            }
        }
    }
}
