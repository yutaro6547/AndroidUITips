package www.rozkey59.tokyo.androiduitips.main.infra

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import www.rozkey59.tokyo.androiduitips.main.infra.data.GitHubRepositoryRes

class GitHubClient {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

    private fun retrofitBuilder() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getRepositories(): Flow<List<GitHubRepositoryRes>> {
        val service = retrofitBuilder().create(GitHubService::class.java)
        return flow {
            emit(service.getRepositories())
        }.flowOn(Dispatchers.IO)
    }
}