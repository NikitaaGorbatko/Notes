package amatraspace.my.notes

import androidx.annotation.DrawableRes

sealed class Screen(val route: String, @DrawableRes val icon: Int) {
    object Calendar : Screen("calendar", R.drawable.ic_calendar)
    object Month : Screen("month", R.drawable.ic_menu)
    object Search : Screen("search", R.drawable.ic_home)
    object Account : Screen("account", R.drawable.ic_account)
}