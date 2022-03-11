package az.azerconnect.data.di

import az.azerconnect.data.api.client.BakcellApiClient
import org.koin.dsl.module

val appModuleApis = module {
    single { BakcellApiClient.provideApi() }
}