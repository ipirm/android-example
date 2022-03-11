package az.azerconnect.bakcell.di

import az.azerconnect.bakcell.ui.launch.auth.login.LoginVM
import az.azerconnect.bakcell.ui.launch.splash.SplashVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    //Splash
    viewModel { SplashVM() }

    //Login
    viewModel { LoginVM(get()) }

}