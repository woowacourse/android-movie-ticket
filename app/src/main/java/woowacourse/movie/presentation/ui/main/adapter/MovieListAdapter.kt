package woowacourse.movie.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie

class MovieListAdapter(
    private val movieList: List<Movie>,
    private val onReserveButtonClickListener: (Movie) -> Unit,
) : BaseAdapter() {
    override fun getCount(): Int = movieList.size
    
    override fun getItem(index: Int): Movie = movieList[index]
    
    override fun getItemId(index: Int): Long = index.toLong()
    
    override fun getView(
        index: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater.from(parent?.context)
                .inflate(R.layout.movie_item, parent, false)
                .also {
                    it.setTag(R.id.movie_view_holder, MovieViewHolder(it))
                }
        
        val movieViewHolder =
            (view.getTag(R.id.movie_view_holder) as? MovieViewHolder)
                ?: MovieViewHolder(view).also {
                    view.setTag(R.id.movie_view_holder, it)
                }
        movieViewHolder.bind(movieList[index], onReserveButtonClickListener)
        
        return view
    }
    
    class MovieViewHolder(view: View) {
        private val posterImage: ImageView = view.findViewById(R.id.posterImage)
        private val title: TextView = view.findViewById(R.id.title)
        private val screeningDate: TextView = view.findViewById(R.id.screeningDate)
        private val runningTime: TextView = view.findViewById(R.id.runningTime)
        private val reserveButton: TextView = view.findViewById(R.id.reserveButton)
        
        fun bind(
            movie: Movie,
            onReserveButtonClickListener: (Movie) -> Unit,
        ) {
            posterImage.setImageResource(movie.posterSrc)
            title.text = movie.title
            screeningDate.text =
                screeningDate.context.getString(R.string.screening_date_format, movie.screeningDate)
            runningTime.text =
                runningTime.context.getString(R.string.running_time_format, movie.runningTime)
            reserveButton.setOnClickListener {
                onReserveButtonClickListener(movie)
            }
        }
    }
}
