package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.screening.Screening
import java.time.format.DateTimeFormatter

class ScreeningRecyclerViewAdapter(
    private val screeningItems: List<Screening>,
    @DrawableRes
    private val advertisementDrawableId: Int,
    private val ticketingButtonClickListener: TicketingButtonClickListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var ticketingButton: Button

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val view: View
        return when (viewType) {
            TYPE_SCREENING -> {
                view = layoutInflater.inflate(R.layout.item_movie, parent, false)
                ScreeningViewHolder(view).also { viewHolder ->
                    ticketingButton = view.findViewById(R.id.btn_ticketing)
                    initializeTicketingButton(viewHolder)
                }
            }

            else -> {
                view = layoutInflater.inflate(R.layout.item_advertisement, parent, false)
                AdvertisementViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        if (holder is AdvertisementViewHolder) {
            holder.bind(advertisementDrawableId)
        } else {
            (holder as ScreeningViewHolder).bind(screeningItems[position - position / INTERVAL_ADVERTISEMENT])
        }
    }

    override fun getItemCount(): Int {
        return screeningItems.size / INTERVAL_ADVERTISEMENT + screeningItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position % DIVIDER_ADVERTISEMENT == INTERVAL_ADVERTISEMENT -> TYPE_ADVERTISEMENT
            else -> TYPE_SCREENING
        }
    }

    private fun initializeTicketingButton(viewHolder: ScreeningViewHolder) {
        ticketingButton.setOnClickListener {
            ticketingButtonClickListener.onTicketingButtonClick(
                screeningItems[viewHolder.adapterPosition - viewHolder.adapterPosition / INTERVAL_ADVERTISEMENT].screeningId,
            )
        }
    }

    class ScreeningViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val date: TextView = itemView.findViewById(R.id.tv_date)
        private val thumbnail: ImageView = itemView.findViewById(R.id.iv_thumbnail)
        private val runningTime: TextView = itemView.findViewById(R.id.tv_running_time)

        fun bind(screeningItem: Screening) {
            val startDate =
                screeningItem.datePeriod.startDate.format(
                    DateTimeFormatter.ofPattern(itemView.context.getString(R.string.format_datetime)),
                )
            val endDate =
                screeningItem.datePeriod.endDate.format(
                    DateTimeFormatter.ofPattern(itemView.context.getString(R.string.format_datetime)),
                )

            val context = itemView.context
            screeningItem.movie?.let { movie ->
                title.text = movie.title
                date.text = context.getString(R.string.title_date, startDate, endDate)
                runningTime.text = context.getString(R.string.title_running_time, movie.runningTime)
                thumbnail.setImageResource(movie.thumbnailResourceId)
            }
        }
    }

    class AdvertisementViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        private val advertisement = itemView.findViewById<ImageView>(R.id.iv_advertisement)

        fun bind(
            @DrawableRes advertisementDrawableId: Int,
        ) {
            advertisement.setImageResource(advertisementDrawableId)
        }
    }

    interface TicketingButtonClickListener {
        fun onTicketingButtonClick(screeningId: Long)
    }

    companion object {
        private const val INTERVAL_ADVERTISEMENT = 3
        private const val DIVIDER_ADVERTISEMENT = INTERVAL_ADVERTISEMENT + 1
        private const val TYPE_SCREENING = 1000
        private const val TYPE_ADVERTISEMENT = 1001
    }
}
