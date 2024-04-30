package woowacourse.movie.domain.model

import woowacourse.movie.ui.MoviePreviewUI

sealed interface ScreenAd {
    val id: Int

    fun equalsWith(other: ScreenAd?): Boolean

    data class ScreenPreviewUi(
        override val id: Int,
        val moviePreviewUI: MoviePreviewUI,
        val dateRange: DateRange,
    ) : ScreenAd {
        override fun equalsWith(other: ScreenAd?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ScreenPreviewUi

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
