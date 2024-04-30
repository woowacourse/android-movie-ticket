package woowacourse.movie.presentation.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Ads
import woowacourse.movie.presentation.uimodel.MovieUiModel
import woowacourse.movie.presentation.utils.toDrawableIdByName

class MovieListAdapter(
    private val movies: List<MovieUiModel>,
    private val onMovieReserved: (Int) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    private val items: MutableList<Any> = ArrayList<Any>().apply {
        movies.forEachIndexed { index, movie ->
            add(movie)
            if ((index + ADS_POSITION_OFFSET) % ADS_INTERVAL == 0) add(Ads())
        }
    }
    
    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MovieUiModel -> VIEW_TYPE_MOVIE
            is Ads -> VIEW_TYPE_ADS
            else -> throw IllegalArgumentException(ERROR_INVALID_POSITION)
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_MOVIE -> MovieViewHolder(
                inflater.inflate(R.layout.movie_item, parent, false)
            )
            VIEW_TYPE_ADS -> AdsViewHolder(
                inflater.inflate(R.layout.ads_item, parent, false)
            )
            else -> throw IllegalArgumentException(ERROR_INVALID_POSITION)
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(items[position] as MovieUiModel, onMovieReserved)
            is AdsViewHolder -> holder.bind(items[position] as Ads)
        }
    }
    
    override fun getItemCount(): Int = items.size
    
    companion object {
        private const val VIEW_TYPE_MOVIE = 1
        private const val VIEW_TYPE_ADS = 2
        private const val ADS_INTERVAL = 3
        private const val ADS_POSITION_OFFSET = 1
        private const val ERROR_INVALID_POSITION =  "view type을 알 수 없는 위치입니다."
    }
    
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val posterImage: ImageView = view.findViewById(R.id.posterImage)
        private val title: TextView = view.findViewById(R.id.title)
        private val screeningDate: TextView = view.findViewById(R.id.screeningDate)
        private val runningTime: TextView = view.findViewById(R.id.runningTime)
        private val reserveButton: TextView = view.findViewById(R.id.reserveButton)
        
        fun bind(
            movie: MovieUiModel,
            onMovieReserved: (Int) -> Unit,
        ) {
            Log.d("MovieListAdapter2", "bind: ${movie.movieId}")
            val context = posterImage.context
            val imageResource = movie.posterName.toDrawableIdByName(context)
            imageResource?.let { posterImage.setImageResource(it) }
            title.text = movie.title
            screeningDate.text = context.getString(R.string.screening_date_format, movie.screeningStartDate, movie.screeningEndDate)
            runningTime.text = context.getString(R.string.running_time_format, movie.runningTime)
            reserveButton.setOnClickListener { onMovieReserved(movie.movieId) }
        }
    }
    
    class AdsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val adsImage: ImageView = view.findViewById(R.id.ads_image)
        
        fun bind(ads: Ads) {
            Log.d("MovieListAdapter2", "AdsViewHolder: ")
            val context = adsImage.context
            val imageResource = ads.posterName.toDrawableIdByName(context)
            imageResource?.let { adsImage.setImageResource(it) }
        }
    }
}
