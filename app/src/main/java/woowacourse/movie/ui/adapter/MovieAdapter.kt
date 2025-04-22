package woowacourse.movie.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.ui.view.BookingActivity
import woowacourse.movie.ui.constant.IntentKeys

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
        val view: View
        val viewHolder: MovieViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
            viewHolder = MovieViewHolder(view, context, onClick)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as MovieViewHolder
        }

        val movie = movies[position]
        viewHolder.bind(movie)

        return view
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
            poster.setImageResource(movie.posterRes)

            reservationBtn.setOnClickListener {
                onClick(movie)
            }
        }
    }
}
