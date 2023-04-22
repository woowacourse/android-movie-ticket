package woowacourse.movie.dto

interface DtoConverter<T, U : Dto> {
    fun convertDtoToModel(dto: U): T
    fun convertModelToDto(model: T): U
}
