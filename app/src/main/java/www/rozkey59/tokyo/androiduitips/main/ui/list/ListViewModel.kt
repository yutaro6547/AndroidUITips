package www.rozkey59.tokyo.androiduitips.main.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState.LOADING
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState.ERROR
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState.IDEAL
import www.rozkey59.tokyo.androiduitips.main.infra.GitHubRepository
import www.rozkey59.tokyo.androiduitips.main.ui.other.UiData
import javax.inject.Inject

class ListViewModel @Inject constructor(
    application: Application,
    private val repository: GitHubRepository
): AndroidViewModel(application) {

    val uiLive = MutableLiveData<Pair<UiState, UiData?>>()
    private var uiData = UiData(
        list = mutableListOf(),
        isChanged = false
    )
    private val disposables = CompositeDisposable()

    fun getGitHubRepositoryData(since: Int, shouldChange: Boolean = false) {
        uiLive.postValue(LOADING to uiData)
        repository.getRepositories(since)
            .subscribeOn(Schedulers.io())
            .subscribe({ response ->
                uiData = uiData.copy(
                    list = response,
                    isChanged = shouldChange
                )
                uiLive.postValue(IDEAL to uiData)
            }, {
                uiLive.postValue(ERROR to null)
            })
            .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}