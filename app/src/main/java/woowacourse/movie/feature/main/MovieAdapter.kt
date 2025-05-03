package woowacourse.movie.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.movie.Advertisement
import woowacourse.movie.model.movie.MainItem
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ImageResource
import woowacourse.movie.view.model.ResourceMapper
import woowacourse.movie.view.model.setImageResource

class MovieAdapter(
    screenings: List<Screening>,
    ads: List<Advertisement>,
    private val onClickReserveButton: (
        screening: Screening,
        poster: ImageResource,
    ) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mainItems: List<MainItem> by lazy {
        var adIndex = 0
        screenings.foldIndexed(mutableListOf<MainItem>()) { idx, mainItems, screening ->
            mainItems.apply {
                add(MainItem.MovieItem(screening))

                if ((idx + 1) % AD_SHOW_CYCLE == 0 && ads.isNotEmpty()) {
                    add(MainItem.AdItem(ads[adIndex % ads.size]))
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

        fun bind(item: MainItem.MovieItem) {
            val screening = item.screening
            val context = itemView.context

            titleView.text = screening.title
            screeningDateView.text =
                context.getString(
                    R.string.screening_period,
                    screening.period.start.year,
                    screening.period.start.monthValue,
                    screening.period.start.dayOfMonth,
                    screening.period.endInclusive.year,
                    screening.period.endInclusive.monthValue,
                    screening.period.endInclusive.dayOfMonth,
                )
            runningTimeView.text = context.getString(R.string.running_time, screening.runningTime)

            val poster = ResourceMapper.movieIdToPosterImageResource(screening.movieId)
            posterImageView.setImageResource(poster)
            reserveButton.setOnClickListener {
                onClickReserveButton(screening, poster)
            }
        }
    }

    private inner class AdBannerViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val bannerImageView: ImageView = view.findViewById(R.id.iv_ad_banner)

        fun bind(item: MainItem.AdItem) {
            bannerImageView.setImageResource(ResourceMapper.adIdToBannerImageResource(item.ad.adId))
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
        when (val item = mainItems[position]) {
            is MainItem.MovieItem -> (holder as MovieViewHolder).bind(item)
            is MainItem.AdItem -> (holder as AdBannerViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int = mainItems.size

    override fun getItemViewType(position: Int): Int =
        when (mainItems[position]) {
            is MainItem.MovieItem -> VIEW_TYPE_MOVIE
            is MainItem.AdItem -> VIEW_TYPE_AD
        }

    companion object {
        private const val VIEW_TYPE_MOVIE = 0
        private const val VIEW_TYPE_AD = 1

        private const val AD_SHOW_CYCLE = 3
    }
}
