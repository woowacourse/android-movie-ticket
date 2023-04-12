package woowacourse.movie.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import woowacourse.movie.Movie
import woowacourse.movie.R

class MovieAdapter(
    private val mLayoutInflater: LayoutInflater,
    movie: List<Movie>,
) : BaseAdapter() {

    private val _movie: List<Movie> = movie.toList()
    val movie: List<Movie>
        get() = _movie.toList()

    var clickListener: ReservationClickListener? = null

    override fun getCount(): Int {
        return _movie.size
    }

    override fun getItem(p0: Int): Movie {
        return _movie[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = mLayoutInflater.inflate(R.layout.movie_item_layout, null)

        val image = view.findViewById<ImageView>(R.id.image)
        val title = view.findViewById<TextView>(R.id.title)
        val date = view.findViewById<TextView>(R.id.date)
        val time = view.findViewById<TextView>(R.id.time)
        val reservation = view.findViewById<Button>(R.id.reservation)

        val imgDrawable =
            ResourcesCompat.getDrawable(image.resources, _movie[p0].imgResourceId, null)

        image.setImageDrawable(imgDrawable)
        title.text = _movie[p0].title
        date.text = _movie[p0].startDate.toString()
        time.text = _movie[p0].runningTime.toString()

        reservation.setOnClickListener {
            clickListener?.onClick(p0)
        }
        return view
    }

    interface ReservationClickListener {
        fun onClick(position: Int)
    }
}
