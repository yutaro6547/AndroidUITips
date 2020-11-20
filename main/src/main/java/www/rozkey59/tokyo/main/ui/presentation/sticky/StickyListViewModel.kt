package www.rozkey59.tokyo.main.ui.presentation.sticky

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState
import www.rozkey59.tokyo.androiduitips.main.infra.GitHubRepository
import javax.inject.Inject

class StickyListViewModel @Inject constructor(
    application: Application,
    private val repository: GitHubRepository
): AndroidViewModel(application) {
    val uiLive = MutableLiveData<Pair<UiState, www.rozkey59.tokyo.main.ui.presentation.other.UiData?>>()
    private var uiData = www.rozkey59.tokyo.main.ui.presentation.other.UiData(
        list = mutableListOf(),
        isChanged = false
    )

    fun getGitHubRepositoryData(since: Int) {
        viewModelScope.launch {
            uiLive.postValue(UiState.LOADING to null)
            repository.getRepositories(since).collect { response ->
                try {
                    uiData = uiData.copy(
                        list = response,
                        isChanged = false
                    )
                    uiLive.postValue(UiState.IDEAL to uiData)
                } catch (e: HttpException) {
                    uiLive.postValue(UiState.ERROR to null)
                }
            }
        }
    }
}