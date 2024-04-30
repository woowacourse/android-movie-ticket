import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.MovieDisplayData

@Suppress("DEPRECATION")
class MovieAdapter(
    private val context: Context,
    private val items: MutableList<MovieDisplayData>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % 4 == 0) ITEM_VIEW_TYPE_AD else ITEM_VIEW_TYPE_MOVIE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return when (viewType) {
            ITEM_VIEW_TYPE_MOVIE -> {
                val view = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
                MovieViewHolder(view, onClick)
            }

            ITEM_VIEW_TYPE_AD -> {
                val view = layoutInflater.inflate(R.layout.ad_layout, parent, false)
                AdViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(items[position] as MovieDisplayData)
            is AdViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int {
        val originalCount = items.size
        return originalCount + originalCount / 3
    }

    fun updateItems(displayData: List<MovieDisplayData>) {
        items.clear()
        items.addAll(displayData)
    }

    companion object {
        private const val ITEM_VIEW_TYPE_MOVIE = 0
        private const val ITEM_VIEW_TYPE_AD = 1
    }

    class MovieViewHolder(view: View, private val onClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.movie_title)
        private val releaseDateTextView: TextView = view.findViewById(R.id.movie_release_date)
        private val durationTextView: TextView = view.findViewById(R.id.movie_duration)
        private val detailButton: Button = view.findViewById(R.id.movie_details_button)

        fun bind(movie: MovieDisplayData) {
            titleTextView.text = movie.title
            releaseDateTextView.text = movie.releaseDate
            durationTextView.text = movie.runningTime
            detailButton.setOnClickListener {
                onClick(adapterPosition)
            }
        }
    }


    class AdViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {}
    }
}
