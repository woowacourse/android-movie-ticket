package woowacourse.movie.presentation.movie_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.uimodel.movie.MovieBrief

class MovieBriefAdapter(
    private val adapterClickListener: AdapterClickListenter,
) : RecyclerView.Adapter<ViewHolder>() {
    private val movieBriefs: MutableList<MovieBrief> = mutableListOf()

    override fun getItemCount(): Int = movieBriefs.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return if (viewType == TYPE_MOVIE) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.movie_list_item, parent, false)
            MovieHolder(
                title = view.findViewById<TextView>(R.id.movie_title),
                screeningDate = view.findViewById<TextView>(R.id.movie_screening_date),
                runningTime = view.findViewById<TextView>(R.id.movie_item_running_time),
                detailButton = view.findViewById<Button>(R.id.movie_details_button),
                itemView = view,
            )
        } else {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.advertisement_list_item, parent, false)
            object : ViewHolder(view) {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 3 == 2) TYPE_ADVERTISEMENT else TYPE_MOVIE
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val viewType = getItemViewType(position)
        if (viewType == TYPE_MOVIE) {
            val movieBrief: MovieBrief = movieBriefs[position]
            (holder as MovieHolder).bind(movieBrief)
        }
    }

    inner class MovieHolder(
        val title: TextView,
        val screeningDate: TextView,
        val runningTime: TextView,
        val detailButton: Button,
        itemView: View,
    ) : ViewHolder(itemView) {
        init {
            detailButton.setOnClickListener {
                adapterClickListener.onClick(bindingAdapterPosition)
            }
        }

        fun bind(movieBrief: MovieBrief) {
            title.text = movieBrief.title
            screeningDate.text = movieBrief.screeningDate
            runningTime.text = movieBrief.runningTime
        }
    }

    fun addList(movieBriefs: List<MovieBrief>) {
        val positionStart = this.movieBriefs.size
        this.movieBriefs.addAll(movieBriefs)
        notifyItemRangeChanged(positionStart, movieBriefs.size)
    }

    companion object {
        private const val TYPE_MOVIE = 0
        private const val TYPE_ADVERTISEMENT = 1
    }
}
