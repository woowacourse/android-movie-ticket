package woowacourse.movie.presentation.screening

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R

class ScreeningMovieAdapter(
    context: Context,
    private val movies: List<ScreeningMovieUiModel>,
    private val onClickReservationButton: (id: Long) -> Unit = {},
) :
    BaseAdapter() {
    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): ScreeningMovieUiModel = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view = convertView ?: inflater.inflate(R.layout.item_movie, null)

        val movie = getItem(position)
        val postImageView = view.findViewById<ImageView>(R.id.iv_movie_post)
        val title = view.findViewById<TextView>(R.id.tv_movie_title)
        val date = view.findViewById<TextView>(R.id.tv_movie_running_date)
        val runningTime = view.findViewById<TextView>(R.id.tv_movie_running_time)

        postImageView.setImageResource(movie.imageRes)
        title.text = movie.title
        date.text = movie.screenDate
        runningTime.text = movie.runningTime

        view.findViewById<Button>(R.id.btn_movie_reservation).setOnClickListener {
            onClickReservationButton(movie.id)
        }

        return view
    }
}
