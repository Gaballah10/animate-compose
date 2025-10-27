package com.example.animatedbottomsheet.ui.intro.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.animatedbottomsheet.ui.data.CAROUSEL_ROWS
import com.example.animatedbottomsheet.ui.intro.viewmodel.IntroViewModel
import com.example.animatedbottomsheet.ui.main.MainRouter
import com.example.animatedbottomsheet.ui.widget.BlueDotBottomSheet
import com.example.animatedbottomsheet.ui.widget.MultiCarouselScreen

@Composable
fun IntroPage(viewModel: IntroViewModel, mainRouter: MainRouter) {

    IntroScreen(
        viewModel,
        onNavigateNext = {
            mainRouter.navLoginScreen()
        }
    )
}

@Composable
fun IntroScreen(
    viewModel: IntroViewModel,
    onNavigateNext: () -> Unit
) {
    //--- State to control bottom sheet visibility
    var showBottomSheet by remember { mutableStateOf(false) }

    //--- Show bottom sheet immediately when screen appears
    LaunchedEffect(Unit) {
        showBottomSheet = true
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MultiCarouselScreen(rows = CAROUSEL_ROWS)
    }

    //--- Bottom sheet displayed over the screen
    if (showBottomSheet) {
        BlueDotBottomSheet(
            viewModel,
            onDismissRequest = { showBottomSheet = false },
            onNextClick = {
                showBottomSheet = false
                onNavigateNext()
            }
        )
    }
}