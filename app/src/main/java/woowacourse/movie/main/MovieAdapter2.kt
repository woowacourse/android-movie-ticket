package woowacourse.movie.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.data.MovieAndAd
import java.time.format.DateTimeFormatter

class MovieAdapter2(allData: List<MovieAndAd>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _allData: List<MovieAndAd> = allData.toList()
    val allData: List<MovieAndAd>
        get() = _allData.toList()

    var clickListener: ReservationClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val movieView: View?
        val adView: View?

        return when (viewType) {
            1 -> {
                adView = LayoutInflater.from(parent.context).inflate(R.layout.ad_item_layout, parent, false)
                AdViewHolder(adView).apply {
                    itemView.setOnClickListener {
                    }
                }
            }
            else -> {
                movieView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
                MovieViewHolder(movieView).apply {
                    reservation.setOnClickListener {
                        val curPos: Int = adapterPosition
                        clickListener?.onClick(curPos)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(_allData[position] as MovieAndAd.Movie)
            is AdViewHolder -> holder.bind(_allData[position] as MovieAndAd.Advertisement)
        }
    }

    override fun getItemCount(): Int {
        return _allData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (_allData[position]) {
            is MovieAndAd.Movie -> 0
            is MovieAndAd.Advertisement -> 1
        }
    }

    interface ReservationClickListener {
        fun onClick(position: Int)
    }

    companion object {
        val DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.d")
    }
}
