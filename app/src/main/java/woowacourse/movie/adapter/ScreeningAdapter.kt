package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.screening.Screening

class ScreeningAdapter(
    private val screenings: List<Screening>,
    private val onTicketingButtonClick: (Long) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = screenings.size

    override fun getItem(position: Int): Any = screenings[position]

    override fun getItemId(position: Int): Long = screenings[position].screeningId

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val context = parent.context
        val movieViewHolder: MovieViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }

        movieViewHolder.bind(screenings[position])
        return view
    }

    inner class MovieViewHolder(private val itemView: View) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val date: TextView = itemView.findViewById(R.id.tv_date)
        private val thumbnail: ImageView = itemView.findViewById(R.id.iv_thumbnail)
        private val runningTime: TextView = itemView.findViewById(R.id.tv_running_time)
        private val ticketingButton: Button = itemView.findViewById(R.id.btn_ticketing)

        fun bind(screening: Screening) {
            val movie = screening.movie
            movie?.let {
                title.text = movie.title
                date.text =
                    itemView.context.getString(
                        R.string.title_date,
                        screening.datePeriod.startDate.toString(),
                        screening.datePeriod.endDate.toString(),
                    )
                runningTime.text =
                    itemView.context.getString(R.string.title_running_time, movie.runningTime)
                ticketingButton.setOnClickListener { onTicketingButtonClick(screening.screeningId) }
                thumbnail.setImageResource(movie.thumbnailResourceId)
            }
        }
    }
}
