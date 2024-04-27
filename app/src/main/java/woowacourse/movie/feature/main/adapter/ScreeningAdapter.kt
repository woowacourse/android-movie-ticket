package woowacourse.movie.feature.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.main.ui.ScreeningModel

class ScreeningAdapter(
    private val screenings: List<ScreeningModel>,
    private val advertisements: List<Int>,
    private val itemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    fun interface OnItemClickListener {
        fun onItemClick(screeningId: Long)
    }

    override fun getItemCount(): Int = screenings.size + advertisements.size

    override fun getItemViewType(position: Int) =
        if (position == 3) {
            AD
        } else {
            SCREENING
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return if (viewType == SCREENING) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.listview_item, parent, false)
            ScreeningViewHolder(view, itemClickListener)
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.advertisement_item, parent, false)
            AdvertisementViewHolder(view)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is ScreeningViewHolder -> {
                val positionExcludingAds = if (position > 3) position - 1 else position
                Log.d("상영 테스트", "$positionExcludingAds")
                holder.bind(screenings[positionExcludingAds])
            }

            is AdvertisementViewHolder -> {
                val positionExcludingScreen = 0
                Log.d("광고 테스트", "$positionExcludingScreen")
                holder.bind(advertisements[positionExcludingScreen])
            }
        }
    }

    companion object {
        private const val AD = 0
        private const val SCREENING = 1
    }
}
