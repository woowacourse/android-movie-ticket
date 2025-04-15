package woowacourse.movie.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import woowacourse.movie.BookingCompleteActivity
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
        val itemView =
            convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.movie_list_item, parent, false)

        val poster = itemView.findViewById<ImageView>(R.id.img_poster)
        val title = itemView.findViewById<TextView>(R.id.tv_movie_title)
        val screeningDate = itemView.findViewById<TextView>(R.id.tv_movie_screening_date)
        val runningTime = itemView.findViewById<TextView>(R.id.tv_movie_running_time)

        val reserveButton = itemView.findViewById<Button>(R.id.btn_reserve)
        val movie = movieList[position]

        reserveButton.setOnClickListener {
            val context = itemView.context
            val intent =
                Intent(context, BookingCompleteActivity::class.java).apply {
                    this.putExtra("movieData", movie)
                }
            context.startActivity(intent)
        }

        val posterImage = AppCompatResources.getDrawable(context, R.drawable.harry_potter)
        poster.setImageDrawable(posterImage)
        title.text = movie.title
        screeningDate.text = formatDate(movie.screeningDate)
        runningTime.text = context.getString(R.string.minute_text, movie.runningTime)

        return itemView
    }

    private fun formatDate(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
    }
}
