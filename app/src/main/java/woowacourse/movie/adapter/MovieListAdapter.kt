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
import woowacourse.movie.contract.MainContract
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Movies
import woowacourse.movie.repository.MovieData
import woowacourse.movie.view.ReservationActivity

class MovieListAdapter(
    private val context: Context,
    private val movies: Movies,
) : BaseAdapter(), MainContract.View {
    override fun getCount(): Int {
        return MovieData.movies.list.size
    }

    override fun getItem(position: Int): Movie {
        return movies.list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val movieViewHolder: MovieViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }

        val item = getItem(position)
        movieViewHolder.image.setImageResource(item.img)
        movieViewHolder.movieTitle.text = item.title
        movieViewHolder.movieScreenDate.text = item.screenDateToString()
        movieViewHolder.movieRunningTime.text = item.runningTime.toString()

        movieViewHolder.reservationButton.setOnClickListener {
            startReservationActivity(position)
        }

        return view
    }

    private fun startReservationActivity(position: Int) {
        val intent = Intent(context, ReservationActivity::class.java)
        val item = getItem(position)
        // Needed: Simplify intent data setting
        intent.putExtra("image", item.img)
        intent.putExtra("title", item.title)
        intent.putExtra("screenDate", item.screenDateToString())
        intent.putExtra("runningTime", item.runningTime.toString())
        intent.putExtra("description", item.description)

        context.startActivity(intent)
    }

    class MovieViewHolder(view: View) {
        val movieTitle: TextView = view.findViewById(R.id.title)
        val movieScreenDate: TextView = view.findViewById(R.id.screen_date)
        val movieRunningTime: TextView = view.findViewById(R.id.running_time)
        val image: ImageView = view.findViewById(R.id.image_view)

        val reservationButton: Button = view.findViewById(R.id.reservation_button)
    }
}
