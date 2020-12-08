package www.rozkey59.tokyo.androiduitips.main.infra

import io.reactivex.Single
import www.rozkey59.tokyo.androiduitips.main.infra.mapper.GitHubMapper
import www.rozkey59.tokyo.androiduitips.main.ui.other.ListData
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val client: GitHubClient
){
    fun getRepositories(since: Int): Single<List<ListData>> {
        return client
            .getRepositories(since)
            .map { GitHubMapper.transform(it) }
    }
}