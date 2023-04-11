package woowacourse.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MovieAdapter(
    private val mLayoutInflater: LayoutInflater,
    private val movie: List<Movie>,
) : BaseAdapter() {
    override fun getCount(): Int {
        return movie.size
    }

    override fun getItem(p0: Int): Movie {
        return movie[p0]
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

        movie[p0].imgDrawable?.let { image.setImageDrawable(movie[p0].imgDrawable) }
        title.text = movie[p0].title
        date.text = movie[p0].date.toString()
        time.text = movie[p0].runningTime.toString()

        return view
    }
}
