package www.rozkey59.tokyo.androiduitips.main.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import www.rozkey59.tokyo.androiduitips.BuildConfig
import www.rozkey59.tokyo.androiduitips.main.infra.GitHubService

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.github.com/"

    @Provides
    fun provideRetrofit(): Retrofit {
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

    @Provides
    fun provideGitHubService(retrofit: Retrofit): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }
}