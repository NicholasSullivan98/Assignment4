package sheridan.sullnich.assignment4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import sheridan.sullnich.assignment4.ui.details.MarsPhotoDetailsViewModel
import sheridan.sullnich.assignment4.ui.details.MarsPhotosDetailsScreen
import sheridan.sullnich.assignment4.ui.list.MarsPhotosScreen
import sheridan.sullnich.assignment4.ui.list.MarsPhotosViewModel

@Composable
fun MarsPhotoNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = ListDestination.route) {
        composable(route = ListDestination.route) {
            val viewModel: MarsPhotosViewModel = hiltViewModel()
            MarsPhotosScreen(
                viewModel = viewModel,
                onItemClick = { id ->
                    navController.navigate("${DetailsDestination.route}/$id")
                },
            )
        }
        composable(
            route = DetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsDestination.marsphotoIdArg) {
                type = NavType.IntType
            })
        ) {
            val viewModel: MarsPhotoDetailsViewModel = hiltViewModel()
            MarsPhotosDetailsScreen(viewModel = viewModel, onBack = { navController.popBackStack() })
        }
    }
}