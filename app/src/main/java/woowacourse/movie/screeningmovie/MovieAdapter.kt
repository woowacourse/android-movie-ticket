package woowacourse.movie.screeningmovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class MovieAdapter(
    private val movies: List<ScreenMovieUiModel>,
    private val onClickReservationButton: (id: Long) -> Unit = {},
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val movieItems = movies.toMutableList()

    class MovieViewHolder(
        itemView: View,
        private val onClickReservationButton: (id: Long) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        private val postImageView: ImageView = itemView.findViewById(R.id.iv_movie_post)
        private val title: TextView = itemView.findViewById(R.id.tv_movie_title)
        private val date: TextView = itemView.findViewById(R.id.tv_movie_running_date)
        private val runningTime: TextView = itemView.findViewById(R.id.tv_movie_running_time)
        private val button: Button = itemView.findViewById(R.id.btn_movie_reservation)

        fun onBind(item: ScreenMovieUiModel) {
            postImageView.setImageResource(item.imageRes)
            title.text = item.title
            date.text = item.screenDate
            runningTime.text = item.runningTime
            button.setOnClickListener {
                onClickReservationButton(item.id)
            }
        }
    }

    class AdvertiseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        return when {
            position == TOP_VIEW -> MOVIE
            position % ADVERTISE_INTERVAL == 0 -> ADVERTISE
            else -> MOVIE
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            MOVIE -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_screening_movie, parent, false)
                MovieViewHolder(view, onClickReservationButton)
            }

            ADVERTISE -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_screening_advertise, parent, false)
                AdvertiseViewHolder(view)
            }

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (getItemViewType(position) == MOVIE) {
            (holder as MovieViewHolder).onBind(movieItems.removeFirst())
        }
    }

    override fun getItemCount(): Int = movies.size + movies.size / 4

    companion object {
        private const val MOVIE = 0
        private const val ADVERTISE = 1
        private const val ADVERTISE_INTERVAL = 4
        private const val TOP_VIEW = 0
    }
}
