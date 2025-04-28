package woowacourse.movie.adapter

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
import woowacourse.movie.dto.MovieListDataDto
import woowacourse.movie.global.setImage
import woowacourse.movie.global.toFormattedDate

class MovieListAdapter(
    private val onReservationClick: (selectedMovie: MovieListDataDto.MovieDto) -> Unit,
) : ListAdapter<MovieListDataDto, RecyclerView.ViewHolder>(MyDataDiffCallback()) {
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is ViewHolder -> holder.setData(getItem(position) as MovieListDataDto.MovieDto, onReservationClick)
            is AdvertiseViewHolder -> holder.setData(getItem(position) as MovieListDataDto.AdsDto)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val binding =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false)
        return if (viewType == VIEW_TYPE_ADS) {
            val adsBinding =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item_ads, parent, false)
            AdvertiseViewHolder(adsBinding)
        } else {
            ViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MovieListDataDto.MovieDto -> VIEW_TYPE_MOVIE
            is MovieListDataDto.AdsDto -> VIEW_TYPE_ADS
        }
    }

    override fun getItemCount(): Int {
        return if (super.itemCount <= VIEW_MAX_PRINT) super.itemCount else VIEW_MAX_PRINT
    }

    private class MyDataDiffCallback : DiffUtil.ItemCallback<MovieListDataDto>() {
        override fun areItemsTheSame(
            oldItem: MovieListDataDto,
            newItem: MovieListDataDto,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieListDataDto,
            newItem: MovieListDataDto,
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById<TextView>(R.id.movie_title)
        private val runningTimeTextView: TextView = view.findViewById<TextView>(R.id.movie_running)
        private val screeningDateTextView: TextView = view.findViewById<TextView>(R.id.movie_date)
        private val posterImageView: ImageView = view.findViewById<ImageView>(R.id.movie_poster)
        private val buttonView: Button = view.findViewById<Button>(R.id.btn_book)

        fun setData(
            movie: MovieListDataDto.MovieDto,
            onReservationClick: (selectedMovie: MovieListDataDto.MovieDto) -> Unit,
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
                    movie.startDateTime.toFormattedDate(),
                    movie.endDateTime.toFormattedDate(),
                )
            posterImageView.setImage(movie.drawable)
            buttonView.setOnClickListener {
                onReservationClick(movie)
            }
        }
    }

    class AdvertiseViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val ads: ImageView = view.findViewById<ImageView>(R.id.ads)

        fun setData(adsDto: MovieListDataDto.AdsDto) {
            ads.setImage(adsDto.uri)
        }
    }

    companion object {
        private const val VIEW_TYPE_MOVIE = 0
        private const val VIEW_TYPE_ADS = 1
        private const val VIEW_MAX_PRINT = 10_000
    }
}
