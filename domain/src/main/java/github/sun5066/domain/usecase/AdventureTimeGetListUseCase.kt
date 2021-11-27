package github.sun5066.domain.usecase

import github.sun5066.remote.AdventureTimeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AdventureTimeGetListUseCase(private val adventureTimeService: AdventureTimeService) {

    operator fun invoke() = flow {
        emit(adventureTimeService.getCharacterInfoList())
    }.flowOn(Dispatchers.IO)
}