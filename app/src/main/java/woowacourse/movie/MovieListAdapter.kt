package woowacourse.movie

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
        if (convertView != null) {
            binding = binding.root.tag as ItemMovieListBinding
        } else {
            binding = ItemMovieListBinding.inflate(LayoutInflater.from(parent?.context))
            binding.root.tag = binding
        }
        val movie = movies[position]
        val viewHolder = MovieListViewHolder(binding)

        viewHolder.bind(movie)

        return binding.root
    }
}
