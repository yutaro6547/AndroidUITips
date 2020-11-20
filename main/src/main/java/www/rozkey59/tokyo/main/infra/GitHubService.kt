package www.rozkey59.tokyo.main.infra

import retrofit2.http.GET
import retrofit2.http.Query
import www.rozkey59.tokyo.androiduitips.main.infra.data.GitHubRepositoryRes

interface GitHubService {
    @GET("/repositories")
    suspend fun getRepositories(
        @Query("since") since: Int
    ) : List<GitHubRepositoryRes>
}