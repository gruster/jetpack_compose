package android.example.testovoe_jetpack.navigation

sealed class Screen(val route: String) {
    object Profile: Screen(route = "profile")
    object AboutAppScreen: Screen(route = "about_app_screen")
    object AddEmailScreen: Screen(route = "add_email_screen")
    object AddInfoScreen: Screen(route = "add_info_screen")
    object AddPhoneScreen: Screen(route = "add_phone_screen")
}