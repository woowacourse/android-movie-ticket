package woowacourse.movie.domain.ticket

interface CancelTimePolicy {
    val cancelableMinutes: Int
}

object DefaultCancelTimePolicy : CancelTimePolicy {
    const val CANCELABLE_MINUTES: Int = 15
    override val cancelableMinutes: Int = CANCELABLE_MINUTES
}

class FakeCancelTimePolicy(
    override val cancelableMinutes: Int,
) : CancelTimePolicy
