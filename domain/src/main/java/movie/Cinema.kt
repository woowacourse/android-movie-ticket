package movie

class Cinema(
    private val screenings: List<Screening>,
) {
    val size = screenings.size

    operator fun get(index: Int): Screening = screenings[index]
}
