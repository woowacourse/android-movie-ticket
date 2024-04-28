package woowacourse.movie.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.domain.movie.Ads
import woowacourse.movie.domain.movie.Screen
import woowacourse.movie.repository.ScreenListRepository
import woowacourse.movie.ui.home.viewholder.AdsViewHolder
import woowacourse.movie.ui.home.viewholder.ScreenViewHolder

class ScreenRecyclerAdapter(
    private val screenListRepository: ScreenListRepository,
    private val onReservationButtonClick: (Long) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return when (screenListRepository.list[position]) {
            is Ads -> VIEW_TYPE_AD
            is Screen -> VIEW_TYPE_MOVIE
            else -> throw IllegalArgumentException("Unknown type")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        if (viewType == VIEW_TYPE_AD) {
            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.ads_item, parent, false)
            return AdsViewHolder(view)
        }
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ScreenViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is ScreenViewHolder -> {
                val screen = screenListRepository.list[position] as Screen
                holder.bind(screen, onReservationButtonClick)
            }
            is AdsViewHolder -> {
                val ads = screenListRepository.list[position] as Ads
                holder.bind(ads)
            }
        }
    }

    override fun getItemId(position: Int): Long = screenListRepository.list[position].id

    override fun getItemCount(): Int {
        return screenListRepository.list.size
    }

    companion object {
        const val VIEW_TYPE_AD = 0
        const val VIEW_TYPE_MOVIE = 1
    }
}