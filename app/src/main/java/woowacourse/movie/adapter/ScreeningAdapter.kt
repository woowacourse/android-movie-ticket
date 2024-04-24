package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.screening.Screening
import woowacourse.movie.model.screening.ScreeningDate

class ScreeningAdapter : BaseAdapter() {
    private var screenings: List<Screening> = listOf()

    var onClick: ((Int) -> Unit)? = null

    fun setScreening(screenings: List<Screening>) {
        this.screenings = screenings
    }

    override fun getCount(): Int = screenings.size

    override fun getItem(position: Int): Screening = screenings[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val listItemView =
            convertView
                ?: LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        val viewHolder =
            if (convertView != null) {
                listItemView.tag as ViewHolder
            } else {
                val viewHolder = ViewHolder(listItemView)
                listItemView.tag = viewHolder
                viewHolder
            }
        val screening: Screening = getItem(position)
        viewHolder.bind(screening, position)
        return listItemView
    }

    inner class ViewHolder(
        private val title: TextView,
        private val screeningDate: TextView,
        private val runningTime: TextView,
        private val detailButton: Button,
    ) {
        constructor(itemView: View) : this(
            title = itemView.findViewById<TextView>(R.id.movie_title),
            screeningDate = itemView.findViewById<TextView>(R.id.movie_screening_date),
            runningTime = itemView.findViewById<TextView>(R.id.movie_item_running_time),
            detailButton = itemView.findViewById<Button>(R.id.movie_details_button),
        )

        fun bind(screening: Screening, position: Int) {
            val movie = screening.movie
            title.text = movie.title.format()
            screeningDate.text = "상영일: ${screening.date.format()}"
            runningTime.text = "러닝타임: ${movie.runningTime.format()}"
            detailButton.setOnClickListener {
                onClick?.let { it(position) }
            }
        }

        private fun Title.format() = content

        private fun ScreeningDate.format() = "${date.year}.${date.monthValue}.${date.dayOfMonth}"

        private fun RunningTime.format() = time.toString() + "분"
    }
}
