package woowacourse.movie.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieAndAd

class MovieAdapter(allData: List<MovieAndAd>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val allData: List<MovieAndAd> = allData.toList()

    var clickListener: ReservationClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            AD_TYPE -> {
                val adView = LayoutInflater.from(parent.context).inflate(R.layout.ad_item_layout, parent, false)
                AdViewHolder(adView)
            }
            else -> {
                val movieView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
                MovieViewHolder(movieView).apply {
                    reservation.setOnClickListener { clickListener?.onClick(allData[adapterPosition]) }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(allData[position] as MovieAndAd.Movie)
            is AdViewHolder -> holder.bind(allData[position] as MovieAndAd.Advertisement)
        }
    }

    override fun getItemCount(): Int {
        return allData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (allData[position]) {
            is MovieAndAd.Movie -> MOVIE_TYPE
            is MovieAndAd.Advertisement -> AD_TYPE
        }
    }

    interface ReservationClickListener {
        fun onClick(item: MovieAndAd)
    }

    companion object {
        private const val MOVIE_TYPE = 0
        private const val AD_TYPE = 1
    }
}
