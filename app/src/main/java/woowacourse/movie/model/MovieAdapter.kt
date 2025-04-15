package woowacourse.movie.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import woowacourse.movie.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieAdapter(
    val context: Context,
    val movieList: List<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val itemView = LayoutInflater.from(context).inflate(R.layout.movie_list_item, null)

        val poster = itemView.findViewById<ImageView>(R.id.img_poster)
        val title = itemView.findViewById<TextView>(R.id.tv_movie_title)
        val screeningDate = itemView.findViewById<TextView>(R.id.tv_movie_screening_date)
        val runningTime = itemView.findViewById<TextView>(R.id.tv_movie_running_time)

        val movie = movieList[position]
        poster.setImageDrawable(movie.imageSource.toDrawable())
        title.text = movie.title
        screeningDate.text = formatDate(movie.screeningDate)
        runningTime.text = "${movie.runningTime}분"

        return itemView
    }

    private fun formatDate(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
    }
}
