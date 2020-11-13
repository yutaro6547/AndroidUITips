package www.rozkey59.tokyo.androiduitips.main.infra

import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import www.rozkey59.tokyo.androiduitips.BuildConfig
import www.rozkey59.tokyo.androiduitips.main.infra.data.GitHubRepositoryRes

class GitHubClient {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

    private fun retrofitBuilder() : Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        if (BuildConfig.DEBUG) {
            val clientBuilder = OkHttpClient().newBuilder()
            clientBuilder.addInterceptor(HttpLoggingInterceptor())
            retrofitBuilder.client(clientBuilder.build())
        }
        return retrofitBuilder.build()
    }

    suspend fun getRepositories(since: Int): Flow<List<GitHubRepositoryRes>> {
        val service = retrofitBuilder().create(GitHubService::class.java)
        return flow {
            try {
                emit(service.getRepositories(since))
            } catch (e: HttpException) {
                // TODO: Error Handling
            }
        }.flowOn(Dispatchers.IO)
    }
}