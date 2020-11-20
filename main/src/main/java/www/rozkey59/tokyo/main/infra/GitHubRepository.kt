package www.rozkey59.tokyo.main.infra

import kotlinx.coroutines.flow.Flow
import www.rozkey59.tokyo.androiduitips.main.infra.mapper.GitHubMapper
import www.rozkey59.tokyo.main.ui.presentation.other.ListData
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val client: www.rozkey59.tokyo.main.infra.GitHubClient
){

    suspend fun getRepositories(since: Int): Flow<List<www.rozkey59.tokyo.main.ui.presentation.other.ListData>> {
        return client
            .getRepositories(since)
            .map { GitHubMapper.transform(it) }
    }
}