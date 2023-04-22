package woowacourse.movie.activity.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.activity.InjectedModelListener
import woowacourse.movie.model.MovieModel
import java.time.format.DateTimeFormatter

class MovieListAdapter(
    private val movies: List<MovieModel>,
    private val clickListener: InjectedModelListener<MovieModel>,
) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.set(movies[position]) {
            clickListener.onClick(movies[position])
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = movies.size

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.img_movie)
        val title: TextView = view.findViewById(R.id.text_title)
        val playingDate: TextView = view.findViewById(R.id.text_playing_date)
        val runningTime: TextView = view.findViewById(R.id.text_running_time)
        val reserveButton: Button = view.findViewById(R.id.btn_reserve)

        fun set(movie: MovieModel, clickListener: OnClickListener) {
            val context = title.context
            image.setImageResource(movie.image)
            title.text = movie.title
            playingDate.text = context.getString(
                R.string.playing_date_range,
                DateTimeFormatter.ofPattern(context.getString(R.string.date_format))
                    .format(movie.startDate),
                DateTimeFormatter.ofPattern(context.getString(R.string.date_format))
                    .format(movie.endDate),
            )
            runningTime.text = context.getString(R.string.running_time, movie.runningTime)
            reserveButton.setOnClickListener(clickListener)
        }
    }
}
