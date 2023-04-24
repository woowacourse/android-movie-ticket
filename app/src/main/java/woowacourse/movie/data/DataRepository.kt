package woowacourse.movie.data

import woowacourse.movie.model.MovieListItem
import kotlin.reflect.KClass

interface DataRepository {

    fun <T : MovieListItem> getData(dataType: KClass<T>): List<MovieListItem>
}
