package woowacourse.movie.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.utils.DateFormatter


class MovieListAdapter(
    private val onItemClick: ClickListener,
    private val items: List<ItemType>
) : RecyclerView.Adapter<ViewHolder>() {

    inner class MovieViewHolder(view: View) : ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.item_movie_image)
        private val titleTextView: TextView = view.findViewById(R.id.item_movie_title)
        private val dateTextView: TextView = view.findViewById(R.id.item_movie_date)
        private val timeTextView: TextView = view.findViewById(R.id.item_movie_time)
        private val reserveButton: Button = view.findViewById(R.id.item_reserve_button)
        private val dateFormatter = DateFormatter()

        init {
            reserveButton.setOnClickListener {
                onReservationButtonClicked(bindingAdapterPosition)
            }
        }

        private val onReservationButtonClicked = { position: Int ->
            val item = items[position]
            if (item is ItemType.MovieType) {
                val movie = item.movie
                onItemClick.onReserveClick(movie)
            }
        }

        fun setItem(item: Movie) {
            val formattedStartDate = dateFormatter.format(item.date.startDate)
            val formattedEndDate = dateFormatter.format(item.date.endDate)

            imageView.setImageResource(item.image)
            titleTextView.text = item.title
            dateTextView.text = itemView.context.getString(R.string.movieDate, formattedStartDate, formattedEndDate)
            timeTextView.text = itemView.context.getString(R.string.movieTime, item.time.toString())
        }
    }

    inner class ADViewHolder(itemView: View) : ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == TYPE_MOVIE) {
            val movieView = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
            return MovieViewHolder(movieView)
        } else {
            val adView = LayoutInflater.from(parent.context)
                .inflate(R.layout.ad_item, parent, false)
            return ADViewHolder(adView)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is MovieViewHolder) {
            val item = items[position]
            if (item is ItemType.MovieType) {
                val movie = item.movie
                holder.setItem(movie)
            }

        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return  if (position % 4 == TYPE_EMPTY) TYPE_EMPTY else TYPE_MOVIE
    }

    companion object {
        private const val TYPE_MOVIE = 1
        private const val TYPE_EMPTY = 3
    }
}
