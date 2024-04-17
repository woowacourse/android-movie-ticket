package woowacourse.movie.ui.detail

import woowacourse.movie.ui.screen.repository.ScreenRepository

class DetailViewModel(private val repository: ScreenRepository) {
    fun findById(id: Int): DetailEventState {
        repository.findById(id = id).onSuccess { screen ->
            return DetailEventState.Success.ScreenLoading(screen)
        }.onFailure { e ->
            when (e) {
                is NoSuchElementException -> {
                    return DetailEventState.Failure.GoToBack("해당하는 상영 정보가 없습니다.")
                }
            }
        }

        return DetailEventState.Failure.UnexpectedFinish("예상치 못한 에러가 발생했습니다")
    }
}
