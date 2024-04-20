package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.movieInfo.MovieDate
import woowacourse.movie.model.movieInfo.RunningTime
import woowacourse.movie.model.movieInfo.Title
import woowacourse.movie.model.theater.Theater

class MovieAdapter : BaseAdapter() {
    private var theaters: List<Theater> = listOf()

    var onClick: ((Int) -> Unit)? = null

    fun setTheaters(theaters: List<Theater>) {
        this.theaters = theaters
    }

    override fun getCount(): Int = theaters.size

    override fun getItem(position: Int): Theater = theaters[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val listItemView = convertView
            ?: LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)

        val theater: Theater = getItem(position)
        val movie = theater.movie

        listItemView.findViewById<TextView>(R.id.movie_title).text = movie.title.parse()
        listItemView.findViewById<TextView>(R.id.movie_release_date).text =
            "상영일: ${movie.releaseDate.parse()}"
        listItemView.findViewById<TextView>(R.id.movie_duration).text =
            "러닝타임: ${movie.runningTime.parse()}"
        val detailsButton = listItemView.findViewById<Button>(R.id.movie_details_button)

        detailsButton.setOnClickListener {
            onClick?.let { it(position) }
        }
        return listItemView
    }

    private fun Title.parse() = content
    private fun MovieDate.parse() = "${date.year}.${date.monthValue}.${date.dayOfMonth}"
    private fun RunningTime.parse() = time.toString() + "분"
}
