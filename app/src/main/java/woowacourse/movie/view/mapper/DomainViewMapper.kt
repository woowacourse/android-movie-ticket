package woowacourse.movie.view.mapper

import woowacourse.movie.view.model.ViewModel

interface DomainViewMapper<T, U : ViewModel> {
    fun toDomain(viewModel: U): T
    fun toView(domainModel: T): U
}
