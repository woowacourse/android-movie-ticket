package woowacourse.movie.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.contract.MovieListContract
import woowacourse.movie.format.format
import woowacourse.movie.model.movie.Movie

class MovieAdapter(
    private val movies: List<Movie>,
    private val screeningListView: MovieListContract.View,
) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>(),
        MovieAdapterContract.Model,
        MovieAdapterContract.View {

    override fun getItemCount(): Int = movies.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_item, parent, false)
        return MovieHolder(
            title = view.findViewById<TextView>(R.id.movie_title),
            screeningDate = view.findViewById<TextView>(R.id.movie_screening_date),
            runningTime = view.findViewById<TextView>(R.id.movie_item_running_time),
            detailButton = view.findViewById<Button>(R.id.movie_details_button),
            itemView = view,
        )
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie: Movie = movies[position]
        val movieDetail = movie.movieDetail
        holder.title.text = movieDetail.title.format()
        holder.screeningDate.text = "상영일: ${movie.screeningDate.format()}"
        holder.runningTime.text = "러닝타임: ${movieDetail.runningTime.format()}"
    }

    override fun notifyItemClicked(position: Int) {
        screeningListView.navigateToMovieDetail(position)
    }

    inner class MovieHolder(
        val title: TextView,
        val screeningDate: TextView,
        val runningTime: TextView,
        val detailButton: Button,
        itemView: View,
    ): RecyclerView.ViewHolder(itemView) {
        init{
            detailButton.setOnClickListener {
                notifyItemClicked(adapterPosition)
            }
        }
    }
}
