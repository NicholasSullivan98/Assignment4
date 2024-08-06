package sheridan.sullnich.assignment4.ui.navigation

import sheridan.sullnich.assignment4.R

object DetailsDestination : NavDestination {
    override val route = "marsphotos_details"
    override val titleRes = R.string.nav_details_title
    const val marsphotoIdArg = "marsphoto_id"
    val routeWithArgs = "$route/{$marsphotoIdArg}"
}