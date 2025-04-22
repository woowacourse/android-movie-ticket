package woowacourse.movie.model.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieAdapter(
    val resource: Resources,
    val movieList: List<Movie>,
    private val onReserveClick: (Movie) -> Unit,
) : BaseAdapter() {
    private val movieUiList = movieList.map { it.toUiModel(resource) }

    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val itemView: View
        val viewHolder: MovieViewHolder

        if (convertView == null) {
            itemView =
                LayoutInflater.from(parent?.context)
                    .inflate(R.layout.movie_list_item, parent, false)
            viewHolder = MovieViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = convertView.tag as MovieViewHolder
        }

        val movie = movieList[position]
        val movieUiData = movieUiList[position]

        viewHolder.reserveButton.setOnClickListener {
            onReserveClick(movie)
        }

        viewHolder.poster.setImageResource(movieUiData.imageSource)
        viewHolder.title.text = movieUiData.title
        viewHolder.screeningDate.text = movieUiData.screeningPeriod
        viewHolder.runningTime.text = movieUiData.runningTimeText

        return itemView
    }

    private class MovieViewHolder(view: View) {
        val poster: ImageView = view.findViewById<ImageView>(R.id.img_poster)
        val title: TextView = view.findViewById<TextView>(R.id.tv_movie_title)
        val screeningDate: TextView = view.findViewById<TextView>(R.id.tv_movie_screening_date)
        val runningTime: TextView = view.findViewById<TextView>(R.id.tv_movie_running_time)
        val reserveButton: Button = view.findViewById<Button>(R.id.btn_reserve)
    }

    private fun formatDate(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
    }
}
