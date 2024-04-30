package woowacourse.movie.ui.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.ScreenAd
import woowacourse.movie.domain.model.ScreenType

class ScreenAdapter(
    private val onItemClick: (id: Int) -> Unit,
) : ListAdapter<ScreenAd, RecyclerView.ViewHolder>(ScreenPreviewUiDiffUtil()) {
    private lateinit var inflater: LayoutInflater

    override fun getItemViewType(position: Int): Int =
        when {
            ((position + 1) % ADVERTISEMENT_INTERVAL == 0) -> ScreenType.ADVERTISEMENT.id
            else -> ScreenType.SCREEN.id
        }

    override fun getItemCount(): Int {
        val actualItemCount = super.getItemCount()
        val additionalAds = actualItemCount / ADVERTISEMENT_INTERVAL
        return actualItemCount + additionalAds
    }

    override fun getItem(position: Int): ScreenAd {
        val adjustedPosition = position - (position / ADVERTISEMENT_INTERVAL)
        return super.getItem(adjustedPosition)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)

        val screenType =
            ScreenType.entries.find { it.id == viewType }
                ?: throw IllegalArgumentException("Invalid viewType. viewType: $viewType")

        return when (screenType) {
            ScreenType.SCREEN -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_screen, parent, false)
                ScreenViewHolder(view, onItemClick)
            }

            ScreenType.ADVERTISEMENT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_advertisement, parent, false)
                AdViewHolder(view, onItemClick)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item = getItem(position)

        if (item is ScreenAd.ScreenPreviewUi && holder is ScreenViewHolder) {
            holder.bind(item)
        } else if (item is ScreenAd.Advertisement && holder is AdViewHolder) {
            holder.bind(item)
        }
    }

    companion object {
        private const val ADVERTISEMENT_INTERVAL = 4
    }
}
