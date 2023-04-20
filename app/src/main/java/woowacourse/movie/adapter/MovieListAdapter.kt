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
import woowacourse.movie.model.MovieModel

class MovieListAdapter(private val movieModels: List<MovieModel>) :
    BaseAdapter() {
    private val viewHolder: MutableMap<View, ViewHolder> = mutableMapOf()
    override fun getCount(): Int {
        return movieModels.size
    }

    override fun getItem(position: Int): Any {
        return movieModels[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            convertView ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.movie_item, parent, false)
        val currentViewHolder = viewHolder.getOrPut(view) { getViewHolder(view) }
        setViewHolder(
            view.context,
            currentViewHolder,
            movieModels[position]
        )
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

    private fun setViewHolder(context: Context, holder: ViewHolder, movieModel: MovieModel) {
        holder.image.setImageResource(movieModel.image)
        holder.title.text = movieModel.title
        holder.playingDate.text = context.getString(
            R.string.playing_time, movieModel.startDate,
            movieModel.endDate
        )
        holder.runningTime.text = context.getString(R.string.running_time, movieModel.runningTime)
        holder.ticketingButton.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE_KEY, movieModel)
            }
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
