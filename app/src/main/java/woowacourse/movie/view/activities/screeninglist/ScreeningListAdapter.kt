package woowacourse.movie.view.activities.screeninglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class ScreeningListAdapter(
    private val screeningListViewItems: List<ScreeningListViewItemUIState>,
    private val onReserveButtonClick: (Long) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.screening_item -> ScreeningItemViewHolder(view, onReserveButtonClick)
            R.layout.advertisement_item -> AdvertisementItemViewHolder(view)
            else -> throw IllegalArgumentException("존재하지 않는 뷰 타입이 들어옴")
        }
    }

    override fun getItemCount(): Int = screeningListViewItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = screeningListViewItems[position]

        when (holder) {
            is ScreeningItemViewHolder ->
                holder.bind(item as ScreeningListViewItemUIState.ScreeningUIState)
            is AdvertisementItemViewHolder ->
                holder.bind(item as ScreeningListViewItemUIState.AdvertisementUIState)
        }
    }

    override fun getItemViewType(position: Int): Int = when (screeningListViewItems[position]) {
        is ScreeningListViewItemUIState.ScreeningUIState -> R.layout.screening_item
        is ScreeningListViewItemUIState.AdvertisementUIState -> R.layout.advertisement_item
    }
}
