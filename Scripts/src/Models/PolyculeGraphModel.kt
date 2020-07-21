package PolyculeGrapher

data class PolyculeGraphModel(
        val nodes: List<PolyculeNodeModel>,
        val connections: List<PolyculeConnectionModel>
)



data class PolyculeNodeModel(
        val id: Int,
        val name: String
)



data class PolyculeConnectionModel(
        val members: MutablePair<PolyculeNodeModel, PolyculeNodeModel>
)