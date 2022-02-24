package android.example.testovoe_jetpack.pojo

import androidx.annotation.DrawableRes

data class BottomMenuContent (
    val title: String,
    val counter: Int,
    @DrawableRes val iconId: Int
)
