package www.rozkey59.tokyo.androiduitips.main.infra

import kotlinx.coroutines.flow.Flow
import www.rozkey59.tokyo.androiduitips.main.infra.data.GitHubRepositoryRes

class GitHubRepository {

    private fun clientBuilder(): GitHubClient {
        return GitHubClient()
    }

    suspend fun getRepositories(since: Int): Flow<List<GitHubRepositoryRes>> {
        return clientBuilder().getRepositories(since)
    }
}