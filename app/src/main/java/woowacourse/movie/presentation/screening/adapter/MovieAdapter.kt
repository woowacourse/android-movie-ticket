package woowacourse.movie.presentation.screening.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.common.ui.ItemDiffCallback
import woowacourse.movie.presentation.screening.ScreeningMovieUiModel

class MovieAdapter(
    private val onClickReservationButton: (id: Long) -> Unit,
    private val onClickAd: () -> Unit,
    private val isAdPosition: (Int) -> Boolean,
) :
    ListAdapter<ScreeningMovieUiModel, RecyclerView.ViewHolder>(movieComparator) {
    private lateinit var inflater: LayoutInflater

    override fun getItemViewType(position: Int): Int {
        return when {
            isAdPosition(position) -> ScreenItemType.AD.id
            else -> ScreenItemType.MOVIE.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)
        val viewType =
            ScreenItemType.entries.find { it.id == viewType }
                ?: error("InValid 한 viewType 입니다. viewType: $viewType")
        return when (viewType) {
            ScreenItemType.MOVIE -> {
                val view = inflater.inflate(R.layout.item_screening_movie, parent, false)
                MovieViewHolder(view) { onClickReservationButton(getItem(it).id) }
            }

            ScreenItemType.AD -> {
                val view = inflater.inflate(R.layout.item_ad, parent, false)
                AdViewHolder(view, onClickAd)
            }
        }
    }

    override fun onBindViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val data = currentList[position]
        when (viewHolder) {
            is MovieViewHolder -> viewHolder.bind(data)
            is AdViewHolder -> Unit
        }
    }

    companion object {
        private val movieComparator =
            ItemDiffCallback<ScreeningMovieUiModel>(
                onItemsTheSame = { old, new -> old.id == new.id },
                onContentsTheSame = { old, new -> old == new },
            )
    }
}
