package woowacourse.movie.ui.home.adapter

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
import woowacourse.movie.domain.movie.Screen
import woowacourse.movie.repository.MovieListRepository
import woowacourse.movie.repository.ScreenListRepository
import woowacourse.movie.ui.reservation.ReservationActivity
import woowacourse.movie.ui.home.HomeContract

class MovieListAdapter(
    private val context: Context,
    private val screenListRepository: ScreenListRepository,
    private val movieListRepository: MovieListRepository,
) : BaseAdapter(), HomeContract.View {
    override fun getCount(): Int {
        return screenListRepository.list.size
    }

    override fun getItem(position: Int): Screen {
        return screenListRepository.list[position]
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
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

        val item = movieListRepository.findOrNull(getItem(position).movieId)
        if (item != null) {
            movieViewHolder.image.setImageResource(item.imgResId)
            movieViewHolder.movieTitle.text = item.title
            movieViewHolder.movieScreenDate.text = item.screenPeriodToString()
            movieViewHolder.movieRunningTime.text = item.runningTime.toString()

            movieViewHolder.reservationButton.setOnClickListener {
                startReservationActivity(position)
            }
        }
        return view
    }

    private fun startReservationActivity(position: Int) {
        val intent = Intent(context, ReservationActivity::class.java)
        intent.putExtra("screenId", getItemId(position))

        context.startActivity(intent)
    }

    class MovieViewHolder(view: View) {
        val movieTitle: TextView = view.findViewById(R.id.item_title_textview)
        val movieScreenDate: TextView = view.findViewById(R.id.item_screen_date_textview)
        val movieRunningTime: TextView = view.findViewById(R.id.item_running_time_textview)
        val image: ImageView = view.findViewById(R.id.item_imageview)
        val reservationButton: Button = view.findViewById(R.id.item_reservation_button)
    }
}
