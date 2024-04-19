package woowacourse.movie.model.screening

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Quantity
import java.io.Serializable

class Screening(
    val movie: Movie,
    val schedule: Schedule,
    val quantity: Quantity,
) : Serializable
