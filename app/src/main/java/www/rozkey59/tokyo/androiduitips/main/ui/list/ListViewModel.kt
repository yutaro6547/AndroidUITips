package www.rozkey59.tokyo.androiduitips.main.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState
import www.rozkey59.tokyo.androiduitips.main.infra.GitHubRepository
import www.rozkey59.tokyo.androiduitips.main.ui.other.ListData
import www.rozkey59.tokyo.androiduitips.main.ui.other.UiData

class ListViewModel : ViewModel() {

    private fun repositoryBuilder() = GitHubRepository()
    val uiLive = MutableLiveData<Pair<UiState, UiData?>>()
    private var uiData = UiData(
        list = mutableListOf(),
        isChanged = false
    )

    fun getGitHubRepositoryData(since: Int, shouldChange: Boolean = false) {
        viewModelScope.launch {
            uiLive.postValue(UiState.LOADING to null)
            repositoryBuilder().getRepositories(since).collect { response ->
                try {
                    uiData = uiData.copy(
                        list = response,
                        isChanged = shouldChange
                    )
                    uiLive.postValue(UiState.IDEAL to uiData)
                } catch (e: HttpException) {
                    uiLive.postValue(UiState.ERROR to null)
                }
            }
        }
    }
}