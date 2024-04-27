package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.screening.Screening
import java.time.format.DateTimeFormatter

class ScreeningRecyclerViewAdapter(
    private val screeningItems: List<Screening>,
    private val ticketingButtonClickListener: TicketingButtonClickListener,
) : RecyclerView.Adapter<ScreeningRecyclerViewAdapter.ScreeningViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ScreeningViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ScreeningViewHolder(view).also { viewHolder ->
            val ticketingButton: Button = view.findViewById(R.id.btn_ticketing)
            ticketingButton.setOnClickListener {
                ticketingButtonClickListener.onTicketingButtonClick(screeningItems[viewHolder.adapterPosition].screeningId)
            }
        }
    }

    override fun onBindViewHolder(
        holder: ScreeningViewHolder,
        position: Int,
    ) {
        holder.bind(screeningItems[position])
    }

    override fun getItemCount(): Int = screeningItems.size

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
                date.text = context.getString(R.string.text_screening_period, startDate, endDate)
                thumbnail.setImageResource(movie.thumbnailResourceId)
                runningTime.text = context.getString(R.string.text_running_time, movie.runningTime)
            }
        }
    }

    fun interface TicketingButtonClickListener {
        fun onTicketingButtonClick(screeningId: Long)
    }
}
