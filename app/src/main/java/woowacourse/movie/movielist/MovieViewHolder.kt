package woowacourse.movie.movielist

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.utils.DateFormatter

class MovieViewHolder(view: View, private val onItemClick: ClickListener) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.item_movie_image)
        private val titleTextView: TextView = view.findViewById(R.id.item_movie_title)
        private val dateTextView: TextView = view.findViewById(R.id.item_movie_date)
        private val timeTextView: TextView = view.findViewById(R.id.item_movie_time)
        private val reserveButton: Button = view.findViewById(R.id.item_reserve_button)
        private val dateFormatter = DateFormatter()

        fun setItem(item: Movie) {
            val formattedStartDate = dateFormatter.format(item.date.startDate)
            val formattedEndDate = dateFormatter.format(item.date.endDate)

            imageView.setImageResource(item.image)
            titleTextView.text = item.title
            dateTextView.text = itemView.context.getString(R.string.movieDate, formattedStartDate, formattedEndDate)
            timeTextView.text = itemView.context.getString(R.string.movieTime, item.time.toString())
            reserveButton.setOnClickListener { onItemClick.onReserveClick(item) }
        }
    }