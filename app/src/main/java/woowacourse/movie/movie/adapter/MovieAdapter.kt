package woowacourse.movie.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.movie.MovieUiModel
import woowacourse.movie.util.Formatter.formatDateDotSeparated

class MovieAdapter(
    private val movieList: List<MovieUiModel>,
    private val onReserveClick: (MovieUiModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        val adCount = movieList.size / AD_FREQUENCY
        return movieList.size + adCount
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % (AD_FREQUENCY + 1) == 0) TYPE_AD else TYPE_MOVIE
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_AD -> {
                val view = inflater.inflate(R.layout.ad_banner_item, parent, false)
                AdViewHolder(view)
            }

            else -> {
                val view = inflater.inflate(R.layout.movie_list_item, parent, false)
                MovieViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> {
                val context = holder.itemView.context
                val realPosition = position - (position / (AD_FREQUENCY + 1))
                val movie = movieList[realPosition]

                val formattedStartDate = formatDateDotSeparated(movie.screeningStartDate)
                val formattedEndDate = formatDateDotSeparated(movie.screeningEndDate)

                val screeningPeriod =
                    context.getString(
                        R.string.screening_date_period,
                        formattedStartDate,
                        formattedEndDate,
                    )
                val runningTimeText = context.getString(R.string.minute_text, movie.runningTime)

                holder.poster.setImageResource(movie.imageSource)
                holder.title.text = movie.title
                holder.screeningDate.text = screeningPeriod
                holder.runningTime.text = runningTimeText

                holder.reserveButton.setOnClickListener {
                    onReserveClick(movie)
                }
            }

            is AdViewHolder -> {
                holder.imgBanner.setImageResource(R.drawable.img_advertisement)
            }
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster: ImageView = view.findViewById(R.id.img_poster)
        val title: TextView = view.findViewById(R.id.tv_movie_title)
        val screeningDate: TextView = view.findViewById(R.id.tv_movie_screening_date)
        val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
        val reserveButton: Button = view.findViewById(R.id.btn_reserve)
    }

    inner class AdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgBanner: ImageView = view.findViewById(R.id.img_banner)
    }

    companion object {
        private const val TYPE_MOVIE = 0
        private const val TYPE_AD = 1
        private const val AD_FREQUENCY = 3
    }
}
