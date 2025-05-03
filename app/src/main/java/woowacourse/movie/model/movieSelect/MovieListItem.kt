package woowacourse.movie.model.movieSelect

import woowacourse.movie.model.movieSelect.screening.Screening

class MovieListItem(
    private val screenings: List<Screening> = Screening.getDefaultScreenings(),
    private val advertisements: List<Advertisement> = Advertisement.getDefaultAds(),
) {
    fun createItems(): List<MovieItemCategory> {
        var adIndex = 0
        return screenings.foldIndexed(mutableListOf()) { idx, movieSelectItems, screening ->
            movieSelectItems.apply {
                add(MovieItemCategory.Movie(screening))
                if ((idx + 1) % AD_SHOW_CYCLE == 0 && advertisements.isNotEmpty()) {
                    add(MovieItemCategory.Ad(advertisements[adIndex % advertisements.size]))
                    adIndex++
                }
            }
        }
    }

    companion object {
        private const val AD_SHOW_CYCLE = 3
    }
}
