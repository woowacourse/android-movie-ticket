package woowacourse.movie.model

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.BookingActivity
import woowacourse.movie.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieAdapter(
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
        val itemView: View
        val viewHolder: MovieViewHolder

        if (convertView == null) {
            itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.movie_list_item, parent, false)
            viewHolder = MovieViewHolder(itemView)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = convertView.tag as MovieViewHolder
        }

        val reserveButton = itemView.findViewById<Button>(R.id.btn_reserve)
        val movie = movieList[position]

        reserveButton.setOnClickListener {
            val context = itemView.context
            val intent =
                Intent(context, BookingActivity::class.java).apply {
                    this.putExtra("movieData", movie)
                }
            context.startActivity(intent)
        }

        viewHolder.poster.setImageResource(movie.imageSource)
        viewHolder.title.text = movie.title
        val screeningStartDate = formatDate(movie.screeningStartDate)
        val screeningEndDate = formatDate(movie.screeningEndDate)
        viewHolder.screeningDate.text =
            itemView.context.getString(
                R.string.screening_date_period,
                screeningStartDate,
                screeningEndDate
            )
        viewHolder.runningTime.text =
            itemView.context.getString(R.string.minute_text, movie.runningTime)

        return itemView
    }

    class MovieViewHolder(view: View) {
        val poster: ImageView = view.findViewById<ImageView>(R.id.img_poster)
        val title: TextView = view.findViewById<TextView>(R.id.tv_movie_title)
        val screeningDate: TextView = view.findViewById<TextView>(R.id.tv_movie_screening_date)
        val runningTime: TextView = view.findViewById<TextView>(R.id.tv_movie_running_time)
    }

    private fun formatDate(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
    }
}
