package woowacourse.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie

class MovieListAdapter(
    private val context: Context,
    private val movies: List<Movie>,
    val movie: (Movie) -> Unit,
) : BaseAdapter() {
    private lateinit var  view: View /* = LayoutInflater.from(context).inflate(R.layout.movie_item, null)*/

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        view = convertView ?: LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        val reservationButton = view.findViewById<Button>(R.id.reservation_button)

        val item = movies[position]
        setUpViews(item)

        reservationButton.setOnClickListener {
            movie(item)
        }

        return view
    }

    private fun setUpViews(item: Movie) {
        initTitle(item.title)
        initScreenDate(item.screenDateToString())
        initRunningTime(item.runningTime)
        initImage(item.img)
    }

    private fun initTitle(title: String) {
        val movieTitle = view.findViewById<TextView>(R.id.title)
        movieTitle.text = title
    }

    private fun initScreenDate(screenDate: String) {
        val movieScreenDate = view.findViewById<TextView>(R.id.screen_date)
        movieScreenDate.text = screenDate
    }

    private fun initRunningTime(runningTime: Int) {
        val movieRunningTime = view.findViewById<TextView>(R.id.running_time)
        movieRunningTime.text = runningTime.toString()
    }

    private fun initImage(imageResource: Int) {
        val image = view.findViewById<ImageView>(R.id.poster_image)
        image.setImageResource(imageResource)
    }
}
