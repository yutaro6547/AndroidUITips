package www.rozkey59.tokyo.androiduitips.main.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import www.rozkey59.tokyo.androiduitips.main.infra.GitHubRepository
import www.rozkey59.tokyo.androiduitips.main.ui.list.ListViewModel
import www.rozkey59.tokyo.androiduitips.main.ui.sticky.StickyListViewModel

@Module
@InstallIn(ApplicationComponent::class)
object ViewModelModule {

    @Provides
    fun provideListViewModel(application: Application, repository: GitHubRepository): ListViewModel {
        return ListViewModel(application, repository)
    }

    @Provides
    fun provideStickyListViewModel(application: Application, repository: GitHubRepository): StickyListViewModel {
        return StickyListViewModel(application, repository)
    }
}