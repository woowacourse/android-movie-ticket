package woowacourse.movie.view.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class MovieAdapter(
    private val items: List<MovieListItem>,
    private val listener: MovieItemClickListener,
) : RecyclerView.Adapter<BaseViewHolder<MovieListItem>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<MovieListItem> =
        when (MovieListItem.ViewType.entries[viewType]) {
            MovieListItem.ViewType.TYPE_MOVIE ->
                MovieViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_movie, parent, false),
                    listener,
                )

            MovieListItem.ViewType.TYPE_ADS ->
                AdsViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_ads, parent, false),
                    listener,
                )
        } as BaseViewHolder<MovieListItem>

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: BaseViewHolder<MovieListItem>,
        position: Int,
    ) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int = items[position].type.ordinal
}
