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
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.model.MovieUiModel
import woowacourse.movie.ui.model.toUiModel

class MovieAdapter(
    context: Context,
    private val movies: List<Movie>,
    private val onClick: (MovieUiModel) -> Unit,
) : ArrayAdapter<Movie>(context, 0, movies) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val (view, movieViewHolder) = getOrCreateViewHolder(convertView, parent)
        movieViewHolder.bind(movies[position].toUiModel())
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
        private val onClick: (MovieUiModel) -> Unit,
    ) {
        private val poster: ImageView = view.findViewById(R.id.imageview_poster)
        private val title: TextView = view.findViewById(R.id.textview_title)
        private val screeningDate: TextView = view.findViewById(R.id.textview_screeningdate)
        private val runningTime: TextView = view.findViewById(R.id.textview_runningtime)
        private val reservationBtn: Button = view.findViewById(R.id.button_book)

        fun bind(movieUiModel: MovieUiModel) {
            title.text = movieUiModel.title
            screeningDate.text = context.getString(
                R.string.date_text,
                movieUiModel.startScreeningDate,
                movieUiModel.endScreeningDate
            )
            runningTime.text = context.getString(
                R.string.runningTime_text,
                movieUiModel.runningTime
            )
            poster.setImageResource(movieUiModel.posterResId)

            reservationBtn.setOnClickListener {
                onClick(movieUiModel)
            }
        }
    }
}
