package woowacourse.movie.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.list.model.Movie
import java.time.format.DateTimeFormatter

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.movie_title)
        val poster: ImageView = itemView.findViewById(R.id.movie_poster)
        val screeningDate: TextView = itemView.findViewById(R.id.movie_screening_date)
        val runningTime: TextView = itemView.findViewById(R.id.movie_running_time)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    private lateinit var itemClickListener: OnItemClickListener

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int,
    ) {
        val formattedScreeningDate =
            movies[position].firstScreeningDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

        holder.title.text = movies[position].title
        holder.poster.setImageResource(movies[position].posterResourceId)
        holder.screeningDate.text = formattedScreeningDate
        holder.runningTime.text = movies[position].runningTime.toString()

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
