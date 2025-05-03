package woowacourse.movie.feature.movieSelect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.view.model.ResourceMapper
import woowacourse.movie.view.model.setCustomImageResource

class MovieAdapter(
    screeningDataList: List<ScreeningData>,
    adsDataList: List<AdvertisementData>,
    private val onClickReserveButton: (
        screening: ScreeningData,
    ) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val movieSelectItems: List<MovieSelectItems> by lazy {
        var adIndex = 0
        screeningDataList.foldIndexed(mutableListOf()) { idx, movieSelectItems, screeningData ->
            movieSelectItems.apply {
                add(MovieSelectItems.MovieItem(screeningData))

                if ((idx + 1) % AD_SHOW_CYCLE == 0 && adsDataList.isNotEmpty()) {
                    add(MovieSelectItems.AdItem(adsDataList[adIndex % adsDataList.size]))
                    adIndex++
                }
            }
        }
    }

    private inner class MovieViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val titleView: TextView = view.findViewById(R.id.tv_item_movie_title)
        private val screeningDateView: TextView =
            view.findViewById(R.id.tv_item_movie_screening_date)
        private val runningTimeView: TextView = view.findViewById(R.id.tv_item_movie_running_time)
        private val posterImageView: ImageView = view.findViewById(R.id.iv_item_movie_poster)
        private val reserveButton: Button = view.findViewById(R.id.btn_item_movie_reserve)

        fun bind(item: MovieSelectItems.MovieItem) {
            val screeningData = item.screeningData
            val context = itemView.context

            titleView.text = screeningData.title
            screeningDateView.text =
                context.getString(
                    R.string.screening_period,
                    screeningData.startDate.year,
                    screeningData.startDate.monthValue,
                    screeningData.startDate.dayOfMonth,
                    screeningData.endDate.year,
                    screeningData.endDate.monthValue,
                    screeningData.endDate.dayOfMonth,
                )
            runningTimeView.text =
                context.getString(R.string.running_time, screeningData.runningTime)

            val poster = ResourceMapper.movieIdToPosterImageResource(screeningData.movieId)
            posterImageView.setCustomImageResource(poster)
            reserveButton.setOnClickListener {
                onClickReserveButton(screeningData)
            }
        }
    }

    private inner class AdBannerViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val bannerImageView: ImageView = view.findViewById(R.id.iv_ad_banner)

        fun bind(item: MovieSelectItems.AdItem) {
            val adData = item.adData
            bannerImageView.setCustomImageResource(adData.imageResource)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_MOVIE -> {
                val view =
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_movie, parent, false)
                MovieViewHolder(view)
            }

            VIEW_TYPE_AD -> {
                val view =
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_ad_banner, parent, false)
                AdBannerViewHolder(view)
            }

            else -> throw IllegalArgumentException("존재하지 않는 뷰 타입입니다")
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (val item = movieSelectItems[position]) {
            is MovieSelectItems.MovieItem -> (holder as MovieViewHolder).bind(item)
            is MovieSelectItems.AdItem -> (holder as AdBannerViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int = movieSelectItems.size

    override fun getItemViewType(position: Int): Int =
        when (movieSelectItems[position]) {
            is MovieSelectItems.MovieItem -> VIEW_TYPE_MOVIE
            is MovieSelectItems.AdItem -> VIEW_TYPE_AD
        }

    companion object {
        private const val VIEW_TYPE_MOVIE = 0
        private const val VIEW_TYPE_AD = 1

        private const val AD_SHOW_CYCLE = 3
    }
}
