package woowacourse.movie.view.viewmodel

sealed interface MovieListData {
    data class ADData(val id: Int) : MovieListData
}
