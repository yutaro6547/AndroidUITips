package www.rozkey59.tokyo.androiduitips.main.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException
import www.rozkey59.tokyo.androiduitips.core.ui.other.UiState
import www.rozkey59.tokyo.androiduitips.main.infra.GitHubRepository
import www.rozkey59.tokyo.androiduitips.main.ui.other.UiData

class ListViewModel : ViewModel() {

    private fun repositoryBuilder() = GitHubRepository()
    val uiLive = MutableLiveData<Pair<UiState, List<UiData>?>>()

    fun getGitHubRepositoryData(since: Int) {
        viewModelScope.launch {
            uiLive.postValue(UiState.LOADING to null)
            repositoryBuilder().getRepositories(since).collect { response ->
                try {
                    val list = response.map {
                        UiData(
                            id = it.id,
                            name = it.name,
                            userUrl = it.owner.avatarUrl,
                            description = it.description,
                            issueEventsUrl = it.issueEventsUrl
                        )
                    }
                    uiLive.postValue(UiState.IDEAL to list)
                } catch (e: HttpException) {
                    uiLive.postValue(UiState.ERROR to null)
                }
            }
        }
    }
}