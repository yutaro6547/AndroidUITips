package www.rozkey59.tokyo.androiduitips.main.infra

import retrofit2.http.GET
import www.rozkey59.tokyo.androiduitips.main.infra.data.GitHubRepositoryRes

interface GitHubService {
    @GET("/repositories")
    suspend fun getRepositories() : List<GitHubRepositoryRes>
}