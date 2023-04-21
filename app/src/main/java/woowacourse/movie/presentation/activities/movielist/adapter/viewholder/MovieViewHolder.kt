package woowacourse.movie.presentation.activities.movielist.adapter.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.model.movieitem.Movie

class MovieViewHolder(
    view: View,
    onClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(view) {
    private val posterImageView: ImageView = view.findViewById(R.id.poster_iv)
    private val titleTextView: TextView = view.findViewById(R.id.title_tv)
    private val dateTextView: TextView = view.findViewById(R.id.date_tv)
    private val runningTimeTextView: TextView = view.findViewById(R.id.running_time_tv)
    private val bookButton: Button = view.findViewById(R.id.book_btn)

    init {
        bookButton.setOnClickListener { onClick(adapterPosition) }
    }

    fun bind(item: Movie) {
        val context = posterImageView.context

        with(item) {
            posterImageView.setImageResource(thumbnail)
            titleTextView.text = title
            dateTextView.text = context.getString(
                R.string.movie_release_date,
                startDate.formattedDate,
                endDate.formattedDate
            )
            runningTimeTextView.text =
                context.getString(R.string.movie_running_time, runningTime)
        }
    }
}
