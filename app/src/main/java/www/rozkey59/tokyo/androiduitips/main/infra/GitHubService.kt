package www.rozkey59.tokyo.androiduitips.main.infra

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import www.rozkey59.tokyo.androiduitips.main.infra.data.GitHubRepositoryRes

interface GitHubService {
    @GET("/repositories")
    fun getRepositories(
        @Query("since") since: Int
    ) : Single<List<GitHubRepositoryRes>>
}