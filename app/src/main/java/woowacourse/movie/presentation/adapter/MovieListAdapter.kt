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
import woowacourse.movie.presentation.contract.MainContract

class MovieListAdapter(
    private val context: Context,
    private val presenter: MainContract.Presenter,
) : BaseAdapter() {
    private val movieList: List<Movie> = presenter.movieList()

    override fun getCount(): Int = movieList.size

    override fun getItem(index: Int): Movie = movieList[index]

    override fun getItemId(index: Int): Long = index.toLong()

    override fun getView(
        index: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View = convertView ?: createView(parent!!)
        val movie = movieList[index]
        bindData(view, movie)
        return view
    }

    private fun createView(parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
    }

    private fun bindData(
        view: View,
        movie: Movie,
    ) {
        val posterImage = view.findViewById<ImageView>(R.id.posterImage)
        val title = view.findViewById<TextView>(R.id.title)
        val screeningDate = view.findViewById<TextView>(R.id.screeningDate)
        val runningTime = view.findViewById<TextView>(R.id.runningTime)
        val reserveButton = view.findViewById<TextView>(R.id.reserveButton)

        posterImage.setImageResource(movie.posterImageId)
        title.text = movie.title
        screeningDate.text = context.getString(R.string.screening_date_format, movie.screeningDate)
        runningTime.text = context.getString(R.string.running_time_format, movie.runningTime)

        reserveButton.setOnClickListener {
            presenter.onReserveButtonClicked(movie)
        }
    }
}
