package www.rozkey59.tokyo.main.infra

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import www.rozkey59.tokyo.androiduitips.main.infra.data.GitHubRepositoryRes
import javax.inject.Inject

class GitHubClient @Inject constructor(
    private val service: GitHubService
){

    suspend fun getRepositories(since: Int): Flow<List<GitHubRepositoryRes>> {
        return flow {
            try {
                emit(service.getRepositories(since))
            } catch (e: HttpException) {
                // TODO: Error Handling
            }
        }.flowOn(Dispatchers.IO)
    }
}