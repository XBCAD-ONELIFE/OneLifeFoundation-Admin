package com.example.onelifefoundation_admin.util

import androidx.annotation.DrawableRes
import com.example.onelifefoundation_admin.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.img,
        title = "One foundation logo",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Second : OnBoardingPage(
        image = R.drawable.img_1,
        title = "Welcome",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Third : OnBoardingPage(
        image = R.drawable.img,
        title = "Enjoy",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Four : OnBoardingPage(
        image = R.drawable.img_1,
        title = "Welcome",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
}