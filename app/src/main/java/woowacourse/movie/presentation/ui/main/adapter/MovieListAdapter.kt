package woowacourse.movie.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.presentation.uimodel.MovieUiModel
import woowacourse.movie.presentation.utils.toDrawableIdByName

class MovieListAdapter(
    private val movieList: List<MovieUiModel>,
    private val onMovieReserved: (Int) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movieList.size

    override fun getItem(index: Int): MovieUiModel = movieList[index]

    override fun getItemId(index: Int): Long = index.toLong()

    override fun getView(
        index: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val holder: MovieViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.movie_item, parent, false)
            holder = MovieViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as MovieViewHolder
        }

        holder.bind(movieList[index], onMovieReserved)
        return view
    }

    class MovieViewHolder(view: View) {
        private val posterImage: ImageView = view.findViewById(R.id.posterImage)
        private val title: TextView = view.findViewById(R.id.title)
        private val screeningDate: TextView = view.findViewById(R.id.screeningDate)
        private val runningTime: TextView = view.findViewById(R.id.runningTime)
        private val reserveButton: TextView = view.findViewById(R.id.reserveButton)

        fun bind(
            movie: MovieUiModel,
            onMovieReserved: (Int) -> Unit,
        ) {
            val context = posterImage.context
            val imageResource = movie.posterName.toDrawableIdByName(context)
            imageResource?.let { posterImage.setImageResource(it) }
            title.text = movie.title
            screeningDate.text = context.getString(R.string.screening_date_format, movie.screeningStartDate, movie.screeningEndDate)
            runningTime.text = context.getString(R.string.running_time_format, movie.runningTime)
            reserveButton.setOnClickListener { onMovieReserved(movie.movieId) }
        }
    }
}
