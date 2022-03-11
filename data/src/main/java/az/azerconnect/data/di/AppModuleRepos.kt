package az.azerconnect.data.di

import az.azerconnect.data.repository.AuthRepo
import org.koin.dsl.module

val appModuleRepos = module {
    //Auth
    factory { AuthRepo(get()) }
}