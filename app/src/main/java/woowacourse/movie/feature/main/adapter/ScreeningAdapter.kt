package woowacourse.movie.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.feature.main.ui.ScreeningItem

class ScreeningAdapter(
    private val screeningItems: List<ScreeningItem>,
    private val itemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    fun interface OnItemClickListener {
        fun onItemClick(screeningId: Long)
    }

    override fun getItemCount(): Int = screeningItems.size

    override fun getItemViewType(position: Int): Int =
        when (screeningItems[position]) {
            is ScreeningItem.AdModel -> AD
            is ScreeningItem.ScreeningModel -> SCREENING
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            SCREENING -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.listview_item, parent, false)
                ScreeningViewHolder(view, itemClickListener)
            }

            AD -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.advertisement_item, parent, false)
                AdvertisementViewHolder(view)
            }

            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.advertisement_item, parent, false)
                AdvertisementViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is ScreeningViewHolder -> holder.bind(screeningItems[position] as ScreeningItem.ScreeningModel)
            is AdvertisementViewHolder -> holder.bind(screeningItems[position] as ScreeningItem.AdModel)
        }
    }

    companion object {
        private const val AD = 0
        private const val SCREENING = 1
    }
}
