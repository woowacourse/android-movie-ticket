package woowacourse.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.presentation.contract.MainContract
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MovieListAdapter(
    private var movieList: List<MovieUiModel>,
    private val listener: MainContract.ViewActions,
) : BaseAdapter() {
    fun updateMovieList(newMovieList: List<MovieUiModel>) {
        movieList = newMovieList
    }

    override fun getCount(): Int = movieList.size

    override fun getItem(index: Int): MovieUiModel = movieList[index]

    override fun getItemId(index: Int): Long = index.toLong()

    override fun getView(
        index: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view: View
        val movieViewHolder: MovieViewHolder
        if (convertView == null) {
            view = createView(parent!!)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }
        val movie = movieList[index]
        bindData(movieViewHolder, movie, parent!!)

        return view
    }

    private fun createView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
    }

    private fun bindData(
        movieViewHolder: MovieViewHolder,
        movie: MovieUiModel,
        parent: ViewGroup,
    ) {
        movieViewHolder.posterImage.setImageResource(movie.posterImageId)
        movieViewHolder.title.text = movie.title
        movieViewHolder.screeningDate.text =
            parent.context.getString(
                R.string.screening_date_format,
                movie.screeningStartDate,
                movie.screeningEndDate,
            )
        movieViewHolder.runningTime.text =
            parent.context.getString(R.string.running_time_format, movie.runningTime)

        movieViewHolder.reserveButton.setOnClickListener {
            listener.reserveMovie(movie.movieId)
        }
    }
}
