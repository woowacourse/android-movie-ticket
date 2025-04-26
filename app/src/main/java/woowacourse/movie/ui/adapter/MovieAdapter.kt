package woowacourse.movie.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.ui.util.PosterMapper

class MovieAdapter(
    context: Context,
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit,
) : ArrayAdapter<Movie>(context, 0, movies) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val (view, movieViewHolder) = getOrCreateViewHolder(convertView, parent)
        movieViewHolder.bind(movies[position])
        return view
    }

    private fun getOrCreateViewHolder(
        convertView: View?,
        parent: ViewGroup
    ): Pair<View, MovieViewHolder> {
        return if (convertView == null) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
            val viewHolder = MovieViewHolder(view, context, onClick)
            view.tag = viewHolder
            view to viewHolder
        } else {
            convertView to (convertView.tag as MovieViewHolder)
        }
    }

    private class MovieViewHolder(
        view: View,
        private val context: Context,
        private val onClick: (Movie) -> Unit,
    ) {
        private val poster: ImageView = view.findViewById(R.id.imageview_poster)
        private val title: TextView = view.findViewById(R.id.textview_title)
        private val screeningDate: TextView = view.findViewById(R.id.textview_screeningdate)
        private val runningTime: TextView = view.findViewById(R.id.textview_runningtime)
        private val reservationBtn: Button = view.findViewById(R.id.button_book)

        fun bind(movie: Movie) {
            title.text = movie.title
            screeningDate.text = context.getString(
                R.string.date_text,
                movie.startScreeningDate,
                movie.endScreeningDate
            )
            runningTime.text = context.getString(
                R.string.runningTime_text,
                movie.runningTime.toString()
            )
            poster.setImageResource(PosterMapper.convertTitleToResId(movie.title))

            reservationBtn.setOnClickListener {
                onClick(movie)
            }
        }
    }
}
