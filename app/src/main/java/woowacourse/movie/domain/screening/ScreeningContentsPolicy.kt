package woowacourse.movie.domain.screening

interface ScreeningContentsPolicy {
    fun screeningContents(): List<ScreeningContent>
}

class DefaultScreeningContentsPolicy(
    private val screenings: List<Screening>,
    private val advertisements: Advertisements,
) : ScreeningContentsPolicy {
    override fun screeningContents(): List<ScreeningContent> {
        val screeningContents: MutableList<ScreeningContent> = mutableListOf()
        var screeningCount = 0

        for (screening in screenings) {
            screeningContents.add(screening)
            screeningCount++
            if (screeningCount % 3 == 0) {
                screeningContents.add(advertisements.fetchAdvertisement())
            }
        }

        return screeningContents
    }
}
