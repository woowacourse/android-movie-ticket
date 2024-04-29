package woowacourse.movie.ui.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.DateRange
import woowacourse.movie.domain.model.DrawableImage
import woowacourse.movie.ui.MoviePreviewUI

class ScreenAdapter(
    private val onItemClick: (id: Int) -> Unit,
) : ListAdapter<ScreenAd, RecyclerView.ViewHolder>(ScreenPreviewUiDiffUtil()) {
    private lateinit var inflater: LayoutInflater

    override fun getItemViewType(position: Int): Int = when {
        ((position + 1) % ADVERTISEMENT_INTERVAL == 0) -> ScreenType.ADVERTISEMENT.id
        else -> ScreenType.SCREEN.id
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)

        val screenType = ScreenType.entries.find { it.id == viewType }
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

    override fun getItemCount(): Int {
        val actualItemCount = super.getItemCount()
        val additionalAds = actualItemCount / ADVERTISEMENT_INTERVAL
        return actualItemCount + additionalAds
    }

    override fun getItem(position: Int): ScreenAd {
        val adjustedPosition = position - (position / ADVERTISEMENT_INTERVAL)
        return super.getItem(adjustedPosition)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        if (item is ScreenAd.ScreenPreviewUi2 && holder is ScreenViewHolder) {
            holder.bind(item)
        } else if (item is ScreenAd.Advertisement && holder is AdViewHolder) {
            holder.bind(item)
        }
    }


    companion object {
        private const val ADVERTISEMENT_INTERVAL = 4
    }
}

enum class ScreenType(val id: Int) {
    SCREEN(0),
    ADVERTISEMENT(1),
}

sealed interface ScreenAd {
    val id: Int

    fun equalsWith(other: ScreenAd?): Boolean

    data class ScreenPreviewUi2(
        override val id: Int,
        val moviePreviewUI: MoviePreviewUI,
        val dateRange: DateRange,
    ) : ScreenAd {
        override fun equalsWith(other: ScreenAd?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ScreenPreviewUi2

            if (id != other.id) return false
            if (moviePreviewUI != other.moviePreviewUI) return false
            if (dateRange != other.dateRange) return false

            return true
        }
    }

    data class Advertisement(
        override val id: Int = 0,
        val advertisement: DrawableImage,
    ) : ScreenAd {
        override fun equalsWith(other: ScreenAd?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Advertisement

            if (id != other.id) return false
            if (advertisement != other.advertisement) return false

            return true
        }
    }
}
