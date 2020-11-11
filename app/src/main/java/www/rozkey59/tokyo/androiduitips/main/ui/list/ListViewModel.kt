package www.rozkey59.tokyo.androiduitips.main.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import www.rozkey59.tokyo.androiduitips.main.infra.GitHubRepository
import www.rozkey59.tokyo.androiduitips.main.ui.other.UiData

class ListViewModel : ViewModel() {

    private fun repositoryBuilder() = GitHubRepository()
    val uiLive = MutableLiveData<List<UiData>>()

    fun getGitHubRepositoryData() {
        viewModelScope.launch {
            repositoryBuilder().getRepositories().collect { response ->
                val list = response.map {
                    UiData(
                        id = it.id,
                        name = it.name,
                        url = it.url
                    )
                }
                uiLive.postValue(list)
            }
        }
    }
}