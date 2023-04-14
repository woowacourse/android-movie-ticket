package woowacourse.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.databinding.ItemMovieListBinding

class MovieListAdapter(private val movies: List<Movie>) : BaseAdapter() {
    private lateinit var binding: ItemMovieListBinding
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_movie_list, parent, false)

        binding = ItemMovieListBinding.inflate(LayoutInflater.from(view.context))

        val movie = movies[position]

        setMovieData(movie)

        binding.btBookNow.setOnClickListener {
            val intent = Intent(parent?.context, MovieDetailActivity::class.java)
            intent.putExtra("movieData", movie)
            parent?.context?.startActivity(intent)
        }

        return binding.root
    }

    private fun setMovieData(movie: Movie) {
        binding.ivMoviePoster.setImageResource(movie.poster)
        binding.tvMovieTitle.text = movie.title
        binding.tvMovieReleaseDate.text = movie.releaseDate
        binding.tvMovieRunningTime.text = movie.runningTime
    }
}
