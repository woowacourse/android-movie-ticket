package woowacourse.movie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.presentation.movies.MoviesItem
import woowacourse.movie.ui.util.PosterMapper

class MovieAdapter(private val onClick: (Movie) -> Unit) : ListAdapter<MoviesItem, RecyclerView.ViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_movie -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(view, parent.context, onClick)
            }
            R.layout.item_advertisement -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_advertisement, parent, false)
                AdvertisementViewHolder(view)
            }
            else -> throw IllegalArgumentException(TYPE_ERROR)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind((getItem(position) as MoviesItem.MovieItem).movie)
            is AdvertisementViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is MoviesItem.MovieItem -> R.layout.item_movie
            is MoviesItem.AdvertisementItem -> R.layout.item_advertisement
        }
    }

    private class MovieViewHolder(
        view: View,
        private val context: Context,
        private val onClick: (Movie) -> Unit,
    ) : RecyclerView.ViewHolder(view) {
        private val poster: ImageView = view.findViewById(R.id.imageview_poster)
        private val title: TextView = view.findViewById(R.id.textview_title)
        private val screeningDate: TextView = view.findViewById(R.id.textview_screeningdate)
        private val runningTime: TextView = view.findViewById(R.id.textview_runningtime)
        private val reservationBtn: Button = view.findViewById(R.id.button_book)

        fun bind(movie: Movie) {
            title.text = movie.title
            screeningDate.text = context.getString(
                R.string.date_text,
                movie.startScreeningDate,
                movie.endScreeningDate
            )
            runningTime.text = context.getString(
                R.string.runningTime_text,
                movie.runningTime.toString()
            )
            poster.setImageResource(PosterMapper.convertTitleToResId(movie.title))

            reservationBtn.setOnClickListener {
                onClick(movie)
            }
        }
    }

    private class AdvertisementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val adImage: ImageView = view.findViewById(R.id.imageview_ad)

        fun bind() {
            adImage.setImageResource(R.drawable.advertisement)
        }
    }

    companion object {
        private const val TYPE_ERROR = "[ERROR] 알 수 없는 타입입니다."

        private val diffCallback = object : DiffUtil.ItemCallback<MoviesItem>() {
            override fun areContentsTheSame(oldItem: MoviesItem, newItem: MoviesItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: MoviesItem, newItem: MoviesItem): Boolean {
                return when {
                    oldItem is MoviesItem.MovieItem && newItem is MoviesItem.MovieItem -> oldItem.movie == newItem.movie
                    oldItem is MoviesItem.AdvertisementItem && newItem is MoviesItem.AdvertisementItem -> oldItem.id == newItem.id
                    else -> false
                }
            }
        }
    }
}
