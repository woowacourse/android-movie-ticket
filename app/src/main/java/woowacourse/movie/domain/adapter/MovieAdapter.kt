package woowacourse.movie.domain.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import java.time.format.DateTimeFormatter

class MovieAdapter(
    private val items: List<Movie>,
    private val onButtonListener: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val viewHolder: MovieViewHolder

        if (convertView == null) {
            view =
                LayoutInflater
                    .from(parent?.context)
                    .inflate(R.layout.item, parent, false)
            viewHolder = MovieViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as MovieViewHolder
        }

        val item = items[position]

        viewHolder.movieImage.setImageResource(item.image)
        viewHolder.movieTitle.text = item.title
        setDateTextView(viewHolder, item.date, parent?.context)
        setTimeTextView(viewHolder, item.time, parent?.context)
        setReserveButton(
            viewHolder,
            Movie(item.image, item.title, item.date, item.time),
        )
        return view
    }

    private fun setDateTextView(
        viewHolder: MovieViewHolder,
        movieDate: Date,
        context: Context?,
    ) {
        val formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN)
        val startDateFormatted = movieDate.startDate.format(formatter)
        val endDateFormatted = movieDate.endDate.format(formatter)
        viewHolder.movieDate.text =
            context?.getString(R.string.movieDate, startDateFormatted, endDateFormatted)
    }

    private fun setTimeTextView(
        viewHolder: MovieViewHolder,
        movieRunningTime: Int,
        context: Context?,
    ) {
        viewHolder.movieTime.text =
            context?.getString(R.string.movieTime, movieRunningTime.toString())
    }

    private fun setReserveButton(
        viewHolder: MovieViewHolder,
        movie: Movie,
    ) {
        viewHolder.reserveButton.setOnClickListener {
            onButtonListener(movie)
        }
    }

    companion object {
        private const val DATETIME_PATTERN = "yyyy.M.d"
    }
}
