package woowacourse.movie.view.activities.screeninglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class ScreeningListAdapter(
    private val screeningListViewItems: List<ScreeningListViewItemUIState>,
    private val onReserveButtonClick: (Long) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ScreeningItemViewHolder(
        private val view: View,
        private val onReserveButtonClick: (Long) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val posterView: ImageView = view.findViewById(R.id.movie_poster_iv)
        private val titleView: TextView = view.findViewById(R.id.movie_title_tv)
        private val screeningRange: TextView = view.findViewById(R.id.screening_range_tv)
        private val runningTimeView: TextView = view.findViewById(R.id.running_time_tv)
        private val reserveButton: Button = view.findViewById(R.id.reserve_now_btn)

        fun bind(screeningUIState: ScreeningListViewItemUIState.ScreeningUIState) {
            posterView.setImageResource(screeningUIState.poster)
            titleView.text = screeningUIState.title
            screeningRange.text = view.resources.getString(R.string.screening_range_format)
                .format(
                    DATE_FORMATTER.format(screeningUIState.screeningStartDate),
                    DATE_FORMATTER.format(screeningUIState.screeningEndDate)
                )
            runningTimeView.text = view.resources.getString(R.string.running_time_format)
                .format(screeningUIState.runningTime)
            reserveButton.setOnClickListener { onReserveButtonClick(screeningUIState.screeningId) }
        }

        companion object {
            private val DATE_FORMATTER: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy.MM.dd")
        }
    }

    class AdvertisementItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val advertisementImageView: ImageView = view.findViewById(R.id.advertisement_iv)

        fun bind(advertisementUIState: ScreeningListViewItemUIState.AdvertisementUIState) {
            advertisementImageView.setImageResource(advertisementUIState.advertisementImage)
        }
    }


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
