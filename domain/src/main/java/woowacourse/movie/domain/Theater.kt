package woowacourse.movie.domain

import kotlin.properties.Delegates

class Theater(private val seatsWidth: Int, private val seatsHeight: Int) {

    var id: Long? by Delegates.vetoable(null) { _, old, new ->
        old == null && new != null
    }
}
