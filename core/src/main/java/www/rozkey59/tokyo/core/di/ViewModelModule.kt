package www.rozkey59.tokyo.core.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import www.rozkey59.tokyo.main.infra.GitHubRepository
import www.rozkey59.tokyo.main.ui.presentation.list.ListViewModel
import www.rozkey59.tokyo.main.ui.presentation.sticky.StickyListViewModel

@Module
@InstallIn(ApplicationComponent::class)
object ViewModelModule {

    @Provides
    fun provideListViewModel(application: Application, repository: www.rozkey59.tokyo.main.infra.GitHubRepository): www.rozkey59.tokyo.main.ui.presentation.list.ListViewModel {
        return www.rozkey59.tokyo.main.ui.presentation.list.ListViewModel(
            application,
            repository
        )
    }

    @Provides
    fun provideStickyListViewModel(application: Application, repository: www.rozkey59.tokyo.main.infra.GitHubRepository): www.rozkey59.tokyo.main.ui.presentation.sticky.StickyListViewModel {
        return www.rozkey59.tokyo.main.ui.presentation.sticky.StickyListViewModel(
            application,
            repository
        )
    }
}