package woowacourse.movie.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie

class MovieListAdapter(
    private val context: Context,
    private val movieList: List<Movie>,
    private val onReserveButtonClickListener: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movieList.size

    override fun getItem(index: Int): Movie = movieList[index]

    override fun getItemId(index: Int): Long = index.toLong()

    override fun getView(
        index: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val movieViewHolder: MovieViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }

        val movie = movieList[index]
        with(movieViewHolder) {
            posterImage.setImageResource(movie.posterSrc)
            title.text = movie.title
            screeningDate.text = context.getString(R.string.screening_date_format, movie.screeningDate)
            runningTime.text = context.getString(R.string.running_time_format, movie.runningTime)
            reserveButton.setOnClickListener {
                onReserveButtonClickListener(movie)
            }
        }

        return view
    }

    class MovieViewHolder(view: View) {
        val posterImage: ImageView = view.findViewById(R.id.posterImage)
        val title: TextView = view.findViewById(R.id.title)
        val screeningDate: TextView = view.findViewById(R.id.screeningDate)
        val runningTime: TextView = view.findViewById(R.id.runningTime)
        val reserveButton: TextView = view.findViewById(R.id.reserveButton)
    }
}
