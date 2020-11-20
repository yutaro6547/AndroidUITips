package www.rozkey59.tokyo.main.infra.mapper

object GitHubMapper {
    fun transform(response: List<www.rozkey59.tokyo.main.infra.data.GitHubRepositoryRes>) : List<www.rozkey59.tokyo.main.ui.presentation.other.ListData> {
        return response.map {
            www.rozkey59.tokyo.main.ui.presentation.other.ListData(
                id = it.id,
                name = it.name,
                userUrl = it.owner.avatarUrl,
                description = it.description,
                issueEventsUrl = it.issueEventsUrl
            )
        }
    }
}