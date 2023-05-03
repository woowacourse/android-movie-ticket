package woowacourse.movie.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.ContextCompat.startActivity
import woowacourse.movie.R
import woowacourse.movie.dto.MovieDto

class MovieListAdapter(
    private val context: Context,
    private val movies: List<MovieOrAdvertise>
) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val convertView =
            view ?: LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        val movie = movies[position].movie
        if (movie == null){
            initAdvertiseView(convertView)
        } else {
            initMovieItemView(convertView, movie)
        }

        return convertView
    }

    private fun initAdvertiseView(convertView: View) {
        convertView.background = getDrawable(context, R.drawable.ad_image)
        convertView.findViewById<Button>(R.id.reserve_now_button).visibility = View.GONE
    }

    private fun initMovieItemView(
        convertView: View,
        movie: MovieDto
    ) {
        convertView.findViewById<ImageView>(R.id.movie_poster)
            ?.setImageResource(PosterRepository.getPosterResourceId(movie.id))
        convertView.findViewById<TextView>(R.id.movie_title)?.text = movie.title
        convertView.findViewById<TextView>(R.id.movie_screening_date)?.text =
            if (movie.screenings.isEmpty())
                context.resources.getString(R.string.screening_range_is_empty)
            else
                context.resources.getString(R.string.screening_range_format).format(
                    movie.screenings.first().screeningDateTime.format(DATE_FORMATTER),
                    movie.screenings.last().screeningDateTime.format(DATE_FORMATTER)
                )
        convertView.findViewById<TextView>(R.id.movie_running_time)?.text =
            context.resources.getString(R.string.running_time_format)
                .format(movie.runningTime)
        convertView.findViewById<Button>(R.id.reserve_now_button).setOnClickListener {
            startReservationActivity(movie)
        }
    }

    private fun startReservationActivity(movie: MovieDto) {
        val intent = Intent(context, ReservationActivity::class.java)
        intent.putExtra(MOVIE_ID, movie.id)
        startActivity(context, intent, null)
    }

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }
}
