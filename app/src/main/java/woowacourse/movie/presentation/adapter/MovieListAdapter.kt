package woowacourse.movie.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
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
        val view: View
        val movieViewHolder: MovieViewHolder
        if(convertView == null) {
            view = createView(parent!!)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }
        val movie = movieList[index]
        bindData(movieViewHolder, movie)

        return view
    }

    private fun createView(parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
    }

    private fun bindData(
        movieViewHolder: MovieViewHolder,
        movie: Movie,
    ) {
        movieViewHolder.posterImage.setImageResource(movie.posterImageId)
        movieViewHolder.title.text = movie.title
        movieViewHolder.screeningDate.text = context.getString(R.string.screening_date_format, movie.screeningDate)
        movieViewHolder.runningTime.text = context.getString(R.string.running_time_format, movie.runningTime)

        movieViewHolder.reserveButton.setOnClickListener {
            presenter.onReserveButtonClicked(movie)
        }
    }
}
