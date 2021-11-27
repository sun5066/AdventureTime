package github.sun5066.adventuretime.di

import github.sun5066.domain.usecase.AdventureTimeGetListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AdventureTimeGetListUseCase(get()) }
}