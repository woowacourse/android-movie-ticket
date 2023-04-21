package woowacourse.movie.activity.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.activity.InjectedModelListener
import woowacourse.movie.model.MovieModel
import java.time.format.DateTimeFormatter

class MovieListAdapter(private val movies: List<MovieModel>, private val clickListener: InjectedModelListener<MovieModel>) : BaseAdapter() {
    private val viewHolders: MutableMap<View, ViewHolder> = mutableMapOf()
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
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, null)
        if (viewHolders[view] == null) viewHolders[view] = getViewHolder(view)
        val movie = getItem(position) as MovieModel
        viewHolders[view]?.set(movie, parent?.context) {
            clickListener.onClick(movie)
        }
        return view
    }

    private fun getViewHolder(view: View): ViewHolder = ViewHolder(
        view.findViewById(R.id.img_movie),
        view.findViewById(R.id.text_title),
        view.findViewById(R.id.text_playing_date),
        view.findViewById(R.id.text_running_time),
        view.findViewById(R.id.btn_reserve)
    )
    private class ViewHolder(
        val image: ImageView,
        val title: TextView,
        val playingDate: TextView,
        val runningTime: TextView,
        val reserveButton: Button
    ) {
        fun set(movie: MovieModel, context: Context?, clickListener: OnClickListener) {
            image.setImageResource(movie.image)
            title.text = movie.title
            playingDate.text = context?.getString(
                R.string.playing_date_range,
                DateTimeFormatter.ofPattern(context.getString(R.string.date_format)).format(movie.startDate),
                DateTimeFormatter.ofPattern(context.getString(R.string.date_format)).format(movie.endDate)
            )
            runningTime.text = context?.getString(R.string.running_time, movie.runningTime)
            reserveButton.setOnClickListener(clickListener)
        }
    }
}
