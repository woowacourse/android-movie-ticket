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
import woowacourse.movie.dto.MovieListData
import woowacourse.movie.global.setImage
import woowacourse.movie.global.toFormattedDate

class MovieListAdapter(
    private val onReservationClick: (selectedMovie: MovieListData.MovieDto) -> Unit,
) : ListAdapter<MovieListData, RecyclerView.ViewHolder>(MyDataDiffCallback()) {
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is ViewHolder -> holder.setData(getItem(position) as MovieListData.MovieDto, onReservationClick)
            is AdvertiseViewHolder -> holder.setData(getItem(position) as MovieListData.AdsDto)
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
            is MovieListData.MovieDto -> VIEW_TYPE_MOVIE
            is MovieListData.AdsDto -> VIEW_TYPE_ADS
        }
    }

    override fun getItemCount(): Int {
        return if (super.itemCount <= VIEW_MAX_PRINT) super.itemCount else VIEW_MAX_PRINT
    }

    private class MyDataDiffCallback : DiffUtil.ItemCallback<MovieListData>() {
        override fun areItemsTheSame(
            oldItem: MovieListData,
            newItem: MovieListData,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieListData,
            newItem: MovieListData,
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById<TextView>(R.id.movie_title)
        val runningTimeTextView: TextView = view.findViewById<TextView>(R.id.movie_running)
        val screeningDateTextView: TextView = view.findViewById<TextView>(R.id.movie_date)
        val posterImageView: ImageView = view.findViewById<ImageView>(R.id.movie_poster)
        val buttonView: Button = view.findViewById<Button>(R.id.btn_book)

        fun setData(
            movie: MovieListData.MovieDto,
            onReservationClick: (selectedMovie: MovieListData.MovieDto) -> Unit,
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
        val ads: ImageView = view.findViewById<ImageView>(R.id.ads)

        fun setData(adsDto: MovieListData.AdsDto) {
            ads.setImage(adsDto.uri)
        }
    }

    companion object {
        private const val VIEW_TYPE_MOVIE = 0
        private const val VIEW_TYPE_ADS = 1
        private const val VIEW_MAX_PRINT = 10_000
    }
}
