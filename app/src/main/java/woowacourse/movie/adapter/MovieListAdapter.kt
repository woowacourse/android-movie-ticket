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
import woowacourse.movie.presenter.MainPresenter
import woowacourse.movie.view.ReservationActivity

class MovieListAdapter(
    private val context: Context,
    private val presenter: MainPresenter,
) : BaseAdapter(), MainContract.View {
    private val view: View = LayoutInflater.from(context).inflate(R.layout.movie_item, null)

    override fun getCount(): Int {
        return presenter.movieList().size
    }

    override fun getItem(position: Int): Any {
        return presenter.item(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val reservationButton = view.findViewById<Button>(R.id.reservation_button)

        titleTextView(position)
        screenDateTextView(position)
        runningTimeTextView(position)
        titleTextView(position)
        imageView(position)

        reservationButton.setOnClickListener {
            startReservationActivity(position)
        }

        return view
    }

    private fun startReservationActivity(position: Int) {
        val intent = Intent(context, ReservationActivity::class.java)
        presenter.putData(intent, position)
        context.startActivity(intent)
    }

    override fun titleTextView(position: Int) {
        val movieTitle = view.findViewById<TextView>(R.id.title)
        movieTitle.text = presenter.item(position).title
    }

    override fun screenDateTextView(position: Int) {
        val movieScreenDate = view.findViewById<TextView>(R.id.screen_date)
        movieScreenDate.text = presenter.item(position).screenDateToString()
    }

    override fun runningTimeTextView(position: Int) {
        val movieRunningTime = view.findViewById<TextView>(R.id.running_time)
        movieRunningTime.text = presenter.item(position).runningTime.toString()
    }

    override fun imageView(position: Int) {
        val image = view.findViewById<ImageView>(R.id.image_view)
        image.setImageResource(presenter.item(position).img)
    }
}
