package woowacourse.movie.ui.movielistactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.ui.DateTimeFormatters.hyphenDateFormatter

class MovieDataAdapter(
    private val context: Context,
    private val movies: List<MovieDataState>,
    private val onButtonClickListener: (item: MovieDataState) -> Unit
) : RecyclerView.Adapter<MovieDataAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPoster: ImageView = view.findViewById(R.id.iv_poster)
        val tvMovieName: TextView = view.findViewById(R.id.tv_movie_name)
        val tvScreeningDay: TextView = view.findViewById(R.id.tv_screening_day)
        val tvRunningTime: TextView = view.findViewById(R.id.tv_running_time)
        val btnBooking: Button = view.findViewById(R.id.btn_booking)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.movie_list_item, viewGroup, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, position: Int) {
        val item = movies[position]
        movieViewHolder.ivPoster.setImageResource(item.posterImage)
        movieViewHolder.tvMovieName.text = item.title
        movieViewHolder.tvScreeningDay.text = context.getString(R.string.screening_date_format)
            .format(
                item.screeningDay.start.format(hyphenDateFormatter),
                item.screeningDay.end.format(hyphenDateFormatter)
            )
        movieViewHolder.tvRunningTime.text = context.getString(R.string.running_time_format)
            .format(item.runningTime)
        movieViewHolder.btnBooking.setOnClickListener {
            onButtonClickListener(item)
        }
    }

    override fun getItemCount() = movies.size
}
