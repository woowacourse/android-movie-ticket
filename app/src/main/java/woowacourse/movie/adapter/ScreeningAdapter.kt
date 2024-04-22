package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.screening.ScreeningDate
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.screening.Screening

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
        val listItemView = convertView
            ?: LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)

        val screening: Screening = getItem(position)
        val movie = screening.movie

        listItemView.findViewById<TextView>(R.id.movie_title).text = movie.title.format()
        listItemView.findViewById<TextView>(R.id.movie_release_date).text =
            "상영일: ${screening.date.format()}"
        listItemView.findViewById<TextView>(R.id.movie_duration).text =
            "러닝타임: ${movie.runningTime.format()}"
        val detailsButton = listItemView.findViewById<Button>(R.id.movie_details_button)

        detailsButton.setOnClickListener {
            onClick?.let { it(position) }
        }
        return listItemView
    }

    private fun Title.format() = content
    private fun ScreeningDate.format() = "${date.year}.${date.monthValue}.${date.dayOfMonth}"
    private fun RunningTime.format() = time.toString() + "분"
}
