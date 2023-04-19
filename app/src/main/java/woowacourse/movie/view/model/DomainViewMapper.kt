package woowacourse.movie.view.model

interface DomainViewMapper<T, U : ViewModel> {
    fun toDomain(viewModel: U): T
    fun toView(domainModel: T): U
}
