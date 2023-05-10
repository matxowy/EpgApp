package co.proexe.app.navigation

sealed class Screen(val route: String) {
    object Main : Screen("main_screen")
}