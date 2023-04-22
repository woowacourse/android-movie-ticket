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
        return when (viewType) {
            AD_VIEWTYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.ad_item, parent, false)
                ViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_item, parent, false)
                ViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.set(movies[position]) {
            clickListener.onClick(movies[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position != 0 && position % 3 == 0) {
            return AD_VIEWTYPE
        }
        return NORMAL_VIEWTYPE
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = movies.size

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.img_movie)
        private val title: TextView = view.findViewById(R.id.text_title)
        private val playingDate: TextView = view.findViewById(R.id.text_playing_date)
        private val runningTime: TextView = view.findViewById(R.id.text_running_time)
        private val reserveButton: Button = view.findViewById(R.id.btn_reserve)

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

    companion object {
        private const val AD_VIEWTYPE = 1
        private const val NORMAL_VIEWTYPE = 0
    }
}
