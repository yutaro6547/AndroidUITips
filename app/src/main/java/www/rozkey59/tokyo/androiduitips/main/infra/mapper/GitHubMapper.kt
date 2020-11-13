package www.rozkey59.tokyo.androiduitips.main.infra.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import www.rozkey59.tokyo.androiduitips.main.infra.data.GitHubRepositoryRes
import www.rozkey59.tokyo.androiduitips.main.ui.other.ListData

object GitHubMapper {
    fun transform(response: List<GitHubRepositoryRes>) : List<ListData> {
        return response.map {
            ListData(
                id = it.id,
                name = it.name,
                userUrl = it.owner.avatarUrl,
                description = it.description,
                issueEventsUrl = it.issueEventsUrl
            )
        }
    }
}