package www.rozkey59.tokyo.androiduitips.main.ui.sticky

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState
import www.rozkey59.tokyo.androiduitips.main.infra.GitHubRepository
import www.rozkey59.tokyo.androiduitips.main.ui.other.UiData
import javax.inject.Inject
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState.ERROR
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState.IDEAL

class StickyListViewModel @Inject constructor(
    application: Application,
    private val repository: GitHubRepository
): AndroidViewModel(application) {
    val uiLive = MutableLiveData<Pair<UiState, UiData?>>()
    private var uiData = UiData(
        list = mutableListOf(),
        isChanged = false
    )
    private val disposables = CompositeDisposable()

    fun getGitHubRepositoryData(since: Int) {
        repository.getRepositories(since)
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                uiData = uiData.copy(
                    list = response,
                    isChanged = false
                )
                uiLive.postValue(IDEAL to uiData)
            }, {
                uiLive.postValue(ERROR to null)
            })
            .addTo(disposables)
    }
}