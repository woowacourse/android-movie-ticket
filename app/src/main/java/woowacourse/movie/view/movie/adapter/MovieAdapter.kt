package woowacourse.movie.view.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.view.movie.model.AdUiModel
import woowacourse.movie.view.movie.model.MovieUiModel

class MovieAdapter(
    private val items: List<MovieListItem>,
    private val onClickButton: (MovieUiModel) -> Unit,
    private val onClickAd: (AdUiModel) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder =
        when (MovieListItem.ViewType.entries[viewType]) {
            MovieListItem.ViewType.TYPE_MOVIE ->
                MovieViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_movie, parent, false),
                    onClickButton,
                )

            MovieListItem.ViewType.TYPE_ADS ->
                AdsViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_ads, parent, false),
                    onClickAd,
                )
        }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (val item = items[position]) {
            is MovieListItem.MovieItem -> (holder as MovieViewHolder).bind(item.movie)
            is MovieListItem.AdItem -> (holder as AdsViewHolder).bind(item.ad)
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].type.ordinal
}
