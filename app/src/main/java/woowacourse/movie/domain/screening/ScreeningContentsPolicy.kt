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
            if (screeningContents.size == CONTENTS_SIZE_MAX) break
            screeningContents.add(screening)
            screeningCount++
            if (screeningCount % 3 == 0) {
                screeningContents.add(advertisements.fetchAdvertisement())
            }
        }

        return screeningContents
    }

    companion object {
        const val CONTENTS_SIZE_MAX = 10_000
    }
}
