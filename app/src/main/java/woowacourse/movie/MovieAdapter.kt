package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.model.ScreenMovieUiModel

class MovieAdapter(
    context: Context,
    private val movies: List<ScreenMovieUiModel>,
    private val onClickReservationButton: (id: Long) -> Unit = {},
) :
    BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): ScreenMovieUiModel = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val holder: MovieViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
            holder = MovieViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as MovieViewHolder
        }

        val movie = getItem(position)

        holder.postImageView.setImageResource(movie.imageRes)
        holder.title.text = movie.title
        holder.date.text = movie.screenDate
        holder.runningTime.text = movie.runningTime

        view.findViewById<Button>(R.id.btn_movie_reservation).setOnClickListener {
            onClickReservationButton(movie.id)
        }

        return view
    }

    class MovieViewHolder(view: View) {
        val postImageView: ImageView = view.findViewById(R.id.iv_movie_post)
        val title: TextView = view.findViewById(R.id.tv_movie_title)
        val date: TextView = view.findViewById(R.id.tv_movie_running_date)
        val runningTime: TextView = view.findViewById(R.id.tv_movie_running_time)
    }
}
