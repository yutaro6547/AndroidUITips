package www.rozkey59.tokyo.androiduitips.main.infra

import io.reactivex.Single
import www.rozkey59.tokyo.androiduitips.main.infra.data.GitHubRepositoryRes
import javax.inject.Inject

class GitHubClient @Inject constructor(
    private val service: GitHubService
){
    fun getRepositories(since: Int): Single<List<GitHubRepositoryRes>> {
        return service.getRepositories(since)
    }
}