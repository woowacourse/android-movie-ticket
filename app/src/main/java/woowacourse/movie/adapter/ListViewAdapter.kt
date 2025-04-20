package woowacourse.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Date
import woowacourse.movie.domain.Movie
import woowacourse.movie.utils.DateFormatter

class ListViewAdapter(
    private val items: List<Movie>,
    private val onReserveClick: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Movie = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item, parent, false)

        setImageView(view, items[position].image)
        setTitleTextView(view, items[position].title)
        setDateTextView(view, items[position].date, parent?.context)
        setTimeTextView(view, items[position].time, parent?.context)
        setReserveButton(
            view,
            Movie(items[position].image, items[position].title, items[position].date, items[position].time),
        )

        return view
    }

    private fun setImageView(
        view: View,
        movieImage: Int,
    ) {
        val imageView: ImageView = view.findViewById(R.id.movie_image)
        imageView.setImageResource(movieImage)
    }

    private fun setTitleTextView(
        view: View,
        movieTitle: String,
    ) {
        val titleTextView: TextView = view.findViewById(R.id.movie_title)
        titleTextView.text = movieTitle
    }

    private fun setDateTextView(
        view: View,
        movieDate: Date,
        context: Context?,
    ) {
        val dateFormatter = DateFormatter()
        val formattedStartDate = dateFormatter.format(movieDate.startDate)
        val formattedEndDate = dateFormatter.format(movieDate.endDate)
        val dateTextView: TextView = view.findViewById(R.id.movie_date)
        dateTextView.text = context?.getString(R.string.movieDate, formattedStartDate, formattedEndDate)
    }

    private fun setTimeTextView(
        view: View,
        movieRunningTime: Int,
        context: Context?,
    ) {
        val timeTextView: TextView = view.findViewById(R.id.movie_time)
        timeTextView.text = context?.getString(R.string.movieTime, movieRunningTime.toString())
    }

    private fun setReserveButton(
        view: View,
        movie: Movie,
    ) {
        val reserveButton: Button = view.findViewById(R.id.reserve_button)
        reserveButton.setOnClickListener {
            onReserveClick(movie)
        }
    }
}
