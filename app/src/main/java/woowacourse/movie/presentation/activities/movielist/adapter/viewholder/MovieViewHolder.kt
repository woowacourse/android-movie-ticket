package woowacourse.movie.presentation.activities.movielist.adapter.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseRecyclerView
import woowacourse.movie.presentation.model.movieitem.ListItem
import woowacourse.movie.presentation.model.movieitem.Movie

class MovieViewHolder(
    view: View,
    onClick: (Int) -> Unit,
) : BaseRecyclerView.BaseViewHolder(view) {
    private val posterImageView: ImageView = view.findViewById(R.id.poster_iv)
    private val titleTextView: TextView = view.findViewById(R.id.title_tv)
    private val dateTextView: TextView = view.findViewById(R.id.date_tv)
    private val runningTimeTextView: TextView = view.findViewById(R.id.running_time_tv)
    private val bookButton: Button = view.findViewById(R.id.book_btn)

    init {
        bookButton.setOnClickListener { onClick(adapterPosition) }
    }

    override fun <T : ListItem> bind(item: T) {
        if (item !is Movie) return

        with(item as Movie) {
            posterImageView.setImageResource(thumbnail)
            titleTextView.text = title
            dateTextView.text = dateTextView.context.getString(
                R.string.movie_release_date,
                startDate.formattedDate,
                endDate.formattedDate
            )
            runningTimeTextView.text =
                runningTimeTextView.context.getString(R.string.movie_running_time, runningTime)
        }
    }
}
