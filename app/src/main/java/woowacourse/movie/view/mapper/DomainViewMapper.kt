package woowacourse.movie.view.mapper

import woowacourse.movie.view.model.UiModel

interface DomainViewMapper<T, U : UiModel> {
    fun toDomain(viewModel: U): T
    fun toUi(domainModel: T): U
}
