package www.rozkey59.tokyo.androiduitips.main.infra

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import www.rozkey59.tokyo.androiduitips.main.infra.data.GitHubRepositoryRes
import www.rozkey59.tokyo.androiduitips.main.infra.mapper.GitHubMapper
import www.rozkey59.tokyo.androiduitips.main.ui.other.ListData

class GitHubRepository {

    private fun clientBuilder(): GitHubClient {
        return GitHubClient()
    }

    suspend fun getRepositories(since: Int): Flow<List<ListData>> {
        return clientBuilder().getRepositories(since)
            .map { GitHubMapper.transform(it) }
    }
}