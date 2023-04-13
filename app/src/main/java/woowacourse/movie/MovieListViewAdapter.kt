package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.domain.Movie
import java.time.format.DateTimeFormatter

class MovieListViewAdapter(private val context: Context, private val movies: List<Movie>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Movie {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var itemView = convertView
        val holder: MovieListViewHolder

        if (itemView == null) {
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
            holder = MovieListViewHolder(itemView)
            itemView.tag = holder
        } else {
            holder = itemView.tag as MovieListViewHolder
        }
        val item: Movie = movies[position]

        moviePoster.setImageResource(item.moviePoster)
        movieTitle.text = item.title
        screeningStartDate.text = item.runningDate.startDate.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
        screeningEndDate.text = item.runningDate.endDate.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
        runningTime.text = item.runningTime.toString()

    private fun setMovieData(holder: MovieListViewHolder, item: Movie) {
        holder.moviePoster.setImageResource(item.moviePoster)
        holder.movieTitle.text = item.title
        holder.screeningStartDate.text
        item.runningDate.startDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
        holder.screeningEndDate.text =
            item.runningDate.endDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
        holder.runningTime.text = item.runningTime.toString()
    }

    private fun buttonSetOnclickListener(holder: MovieListViewHolder, item: Movie) {
        holder.bookButton.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movie", item)
            context.startActivity(intent)
        }
    }
}
