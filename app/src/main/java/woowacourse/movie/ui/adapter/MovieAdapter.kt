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
import woowacourse.movie.ui.mapper.PosterMapper

class MovieAdapter(
    context: Context,
    private val movies: List<Movie>,
    private val onReservationClickListener: (Int) -> Unit,
) : ArrayAdapter<Movie>(context, 0, movies) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view =
            convertView ?: LayoutInflater
                .from(context)
                .inflate(R.layout.item_movie, parent, false)

        val movie = movies[position]

        val reservationBtn = view.findViewById<Button>(R.id.reservation)
        reservationBtn.setOnClickListener {
            onReservationClickListener(movie.id)
        }

        val imagePoster = view.findViewById<ImageView>(R.id.poster)
        val title = view.findViewById<TextView>(R.id.title)
        val screeningDate = view.findViewById<TextView>(R.id.screeningDate)
        val runningTime = view.findViewById<TextView>(R.id.runningTime)

        title.text = movie.title
        screeningDate.text =
            context.getString(R.string.date_text, movie.startScreeningDate, movie.endScreeningDate)
        runningTime.text =
            context.getString(R.string.runningTime_text, movie.runningTime.toString())

        val posterRes = PosterMapper.mapMovieIdToDrawableRes(movie.id)
        imagePoster.setImage(posterRes)

        return view
    }

    companion object {
        fun ImageView.setImage(res: Int?) {
            if (res != null) {
                this.setImageResource(res)
                this.visibility = View.VISIBLE
            } else {
                this.visibility = View.INVISIBLE
            }
        }
    }
}
