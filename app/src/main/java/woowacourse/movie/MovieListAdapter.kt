package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.io.Serializable

class MovieListAdapter(
    private val context: Context,
    private val movieList: ArrayList<Movie>,
) : BaseAdapter() {
    private lateinit var viewHolder: ViewHolder

    override fun getCount(): Int {
        return movieList.size
    }

    override fun getItem(position: Int): Any {
        return movieList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val movie = movieList[position]
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent)
            makeViewHolder(view)
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        setViewHolder(movie)
        setClickListener(movie)
        return view
    }

    private fun makeViewHolder(view: View) {
        viewHolder =
            ViewHolder(
                view.findViewById(R.id.movie_title),
                view.findViewById(R.id.movie_poster),
                view.findViewById(R.id.movie_screening_date),
                view.findViewById(R.id.movie_running_time),
                view.findViewById(R.id.movie_reservation_button),
            )
        view.tag = viewHolder
    }

    private fun setViewHolder(movie: Movie) {
        viewHolder.title.text = movie.title
        viewHolder.poster.setImageResource(movie.posterResourceId)
        viewHolder.screeningDate.text = movie.screeningDate
        viewHolder.runningTime.text = movie.runningTime.toString()
    }

    private fun setClickListener(movie: Movie) {
        viewHolder.movieReservationButton.setOnClickListener {
            val intent = Intent(context, MovieReservationActivity::class.java)
            intent.putExtra(Movie.KEY_NAME_MOVIE, movie as Serializable)
            context.startActivity(intent)
        }
    }
}
