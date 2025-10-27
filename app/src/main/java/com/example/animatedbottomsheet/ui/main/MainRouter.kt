package com.example.animatedbottomsheet.ui.main

import android.util.Log
import androidx.navigation.NavHostController
import com.example.animatedbottomsheet.navigation.Page

class MainRouter(
    private val mainNavController: NavHostController
) {

    fun navLoginScreen() {
        mainNavController.navigate(Page.Login)
    }
}