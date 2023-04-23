package woowacourse.movie.ui.movielist.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieListModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieItemViewHolder(
    private val view: View,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val poster: ImageView = view.findViewById(R.id.item_poster)
    private val title: TextView = view.findViewById(R.id.item_title)
    private val date: TextView = view.findViewById(R.id.item_date)
    private val runningTime: TextView = view.findViewById(R.id.item_running_time)
    private val button: Button = view.findViewById(R.id.item_booking_button)

    init {
        view.setOnClickListener { onItemClick(adapterPosition) }
        button.setOnClickListener { onItemClick(adapterPosition) }
    }

    fun bind(movie: MovieListModel.MovieModel) {
        poster.setImageResource(movie.poster)
        title.text = movie.title
        date.text = view.context.getString(R.string.screening_date, movie.startDate.format(), movie.endDate.format())
        runningTime.text = view.context.getString(R.string.running_time, movie.runningTime)
    }

    private fun LocalDate.format(): String = format(
        DateTimeFormatter.ofPattern(view.context.getString(R.string.date_format))
    )
}
