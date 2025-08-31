package com.example.isalmicomposeui.home

import androidx.annotation.DrawableRes
import com.example.isalmicomposeui.R

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int,
    val label: String
) {
    object Quran  : BottomNavItem("quran",  R.drawable.ic_moshaf, "Quran")
    object Hadeth : BottomNavItem("hadeth", R.drawable.ic_hadeth, "Hadeth")
    object Sebha  : BottomNavItem("sebha",  R.drawable.ic_sebha,  "Sebha")
    object Radio  : BottomNavItem("radio",  R.drawable.ic_radio,  "Radio")
}

val BottomNavItems = listOf(
    BottomNavItem.Quran,
    BottomNavItem.Hadeth,
    BottomNavItem.Sebha,
    BottomNavItem.Radio
)