package woowacourse.movie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import domain.Movie
import woowacourse.movie.R

class MoviesAdapter(
    private val context: Context,
    private val movies: List<Movie>
) : BaseAdapter() {

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = 0

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_movie, null)
        val movieImageView: ImageView = view.findViewById(R.id.movie_image_view)
        val screeningDateTextView: TextView = view.findViewById(R.id.movie_screening_date_text_view)
        val runningTimeTextView: TextView = view.findViewById(R.id.movie_running_time_text_view)

        // todo: 코드 줄이기
        val movie: Movie = movies[position]

        movie.posterImage?.let { movieImageView.setImageResource(it) }
        screeningDateTextView.text = SCREENING_TIME.format(movie.screeningDate.year, movie.screeningDate.monthValue, movie.screeningDate.dayOfMonth)
        runningTimeTextView.text = RUNNING_TIME.format(movie.runningTime)

        return view
    }

    companion object {
        private const val SCREENING_TIME = "상영일: %d.%d.%d"
        private const val RUNNING_TIME = "러닝타임: %d분"
    }
}
