package br.com.chernoobyl.testegithub.di

import br.com.chernoobyl.testegithub.data.repositories.GithubRepositoryImpl
import br.com.chernoobyl.testegithub.data.services.GithubService
import br.com.chernoobyl.testegithub.data.usecases.GetAllGithubUsers
import br.com.chernoobyl.testegithub.data.usecases.GetGithubRepositoriesByLogin
import br.com.chernoobyl.testegithub.data.usecases.GetGithubUserByLogin
import br.com.chernoobyl.testegithub.domain.usecases.GetUserByLogin
import br.com.chernoobyl.testegithub.domain.repositories.UserRepository
import br.com.chernoobyl.testegithub.domain.usecases.GetAllUsers
import br.com.chernoobyl.testegithub.domain.usecases.GetRepositoriesByLogin
import br.com.chernoobyl.testegithub.presentation.viewmodels.UserDetailViewModel
import br.com.chernoobyl.testegithub.presentation.viewmodels.UserListViewModel
import com.bumptech.glide.Glide
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

private val retrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<GithubService> { get<Retrofit>().create(GithubService::class.java) }
}

private val glideModule = module {
    single { Glide.with(androidContext()) }
}

private val repositoriesModule = module {
    factory<UserRepository> { GithubRepositoryImpl(get()) }
}

private val usecasesModule = module {
    factory<GetAllUsers> { GetAllGithubUsers(get()) }
    factory<GetUserByLogin> { GetGithubUserByLogin(get()) }
    factory<GetRepositoriesByLogin> { GetGithubRepositoriesByLogin(get()) }
}

private val viewModelsModule = module {
    viewModel { UserListViewModel(get()) }
    viewModel { UserDetailViewModel(get(), get()) }
}

val appModules = listOf(
    retrofitModule,
    glideModule,
    repositoriesModule,
    usecasesModule,
    viewModelsModule
)