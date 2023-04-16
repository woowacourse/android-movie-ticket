package woowacourse.movie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.activity.MovieDetailActivity
import woowacourse.movie.model.Movie
import woowacourse.movie.util.Formatter

class MovieListAdapter(private val context: Context, private val movies: List<Movie>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder
        // parent에는 ListView
        // convertView에는 LinearLayout, 즉 item view 들어있음
        if (convertView == null) {
            // convertView 가 null 이면 holder 객체를 생성하고, 생성한 holder 객체에 inflating한 뷰의 참조값 저장
            view = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, null)
            viewHolder = getViewHolder(view)
            // view의 태그에 holder 객체를 저장
            view.tag = viewHolder
        } else {
            // convertView가 null이 아니면 뷰를 생성할 때 태그에 저장했던 holder 객체가 존재
            // 이 holder 객체는 자신을 inflating 한 참조값 (즉 다시 infalte 할 필요가 없다.)
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        setViewHolder(viewHolder, movies[position])
        return view
    }

    private fun getViewHolder(view: View): ViewHolder {
        val image: ImageView = view.findViewById(R.id.img_movie)
        val title: TextView = view.findViewById(R.id.text_title)
        val playingDate: TextView = view.findViewById(R.id.text_playing_date)
        val runningTime: TextView = view.findViewById(R.id.text_running_time)
        val ticketingButton: Button = view.findViewById(R.id.btn_ticketing)

        return ViewHolder(image, title, playingDate, runningTime, ticketingButton)
    }

    private fun setViewHolder(holder: ViewHolder, movie: Movie) {
        holder.image.setImageResource(movie.image)
        holder.title.text = movie.title
        holder.playingDate.text = context.getString(
            R.string.playing_time, Formatter.dateFormat(movie.playingTimes.startDate),
            Formatter.dateFormat(movie.playingTimes.endDate)
        )
        holder.runningTime.text = context.getString(R.string.running_time, movie.runningTime)
        holder.ticketingButton.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_KEY, movie)
            context.startActivity(intent)
        }
    }

    private class ViewHolder(
        val image: ImageView,
        val title: TextView,
        val playingDate: TextView,
        val runningTime: TextView,
        val ticketingButton: Button
    )

    companion object {
        private const val MOVIE_KEY = "movie"
    }
}
