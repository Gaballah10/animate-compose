package com.example.animatedbottomsheet.ui.widget

import ArchedTopShape
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.animatedbottomsheet.ui.intro.viewmodel.IntroViewModel
import com.example.animatedbottomsheet.utils.SusaFontFamily
import com.example.animatedbottomsheet.utils.VayuFontFamily
import com.example.animatedbottomsheet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlueDotBottomSheet(
    viewModel: IntroViewModel,
    onDismissRequest: () -> Unit,
    onNextClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { sheetValue ->
            when (sheetValue) {
                SheetValue.Hidden -> false
                else -> true
            }
        })

    val firstStep by viewModel.stepOneVisibility.collectAsState()
    val secondStep by viewModel.stepTwoVisibility.collectAsState()
    val thirdStep by viewModel.stepThreeVisibility.collectAsState()

    val buttonText = when (viewModel.introState.collectAsState().value) {
        0 -> "Get Started"
        1 -> "Next"
        2 -> "Finish"
        else -> "Continue"
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = ArchedTopShape(archHeight = 40.dp, cornerRadius = 0.dp),
        scrimColor = Color.Black.copy(alpha = 0.1f),
        dragHandle = null,
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            val (topArch, blueDotTxt, calmTxt, getStartedBtn, hints) = createRefs()

            TopBlackArch(
                archHeight = 40.dp,
                strokeWidth = 15.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(topArch) { top.linkTo(parent.top) }
            )

            Text(
                modifier = Modifier.constrainAs(blueDotTxt) {
                    top.linkTo(topArch.bottom, margin = (-40).dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = "Blue Dot.",
                fontSize = 48.sp,
                fontFamily = SusaFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(color = 0xFF222222)
            )


            Text(
                modifier = Modifier.constrainAs(calmTxt) {
                    top.linkTo(topArch.bottom, margin = 18.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = "The Calm Way to Travel",
                fontFamily = VayuFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Black
            )

            Column(
                Modifier
                    .constrainAs(hints) {
                        top.linkTo(calmTxt.bottom, margin = 20.dp)
                        start.linkTo(parent.start, margin = 20.dp)
                        end.linkTo(parent.end, margin = 20.dp)
                    }
                    .animateContentSize(
                        animationSpec = tween(500, easing = LinearOutSlowInEasing)
                    )
            ) {
                PlanTogetherHint(firstStep, modifier = Modifier)

                VoteTogetherHint(secondStep, modifier = Modifier)

                EssentialTogetherHint(thirdStep, modifier = Modifier)

            }

            GradientShadowButton(
                modifier = Modifier
                    .constrainAs(getStartedBtn) {
                        top.linkTo(hints.bottom, margin = 50.dp)
                        start.linkTo(parent.start, margin = 40.dp)
                        end.linkTo(parent.end, margin = 40.dp)

                    }, text = buttonText, onClick = {

                    viewModel.moveNextState()
                    if (buttonText == "Continue") onNextClick.invoke()
                }
            )
        }
    }
}


@Composable
fun PlanTogetherHint(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight } // start below
        ) + fadeIn(animationSpec = tween(500)),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight } // slide down to hide
        ) + fadeOut(animationSpec = tween(200))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .background(
                    color = Color.Transparent, // light gray background
                    shape = RoundedCornerShape(12.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_travel_step_1_modified),
                contentDescription = "Plan Together",
                modifier = Modifier.size(68.dp)
            )

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = "Plan Together",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF222222)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Friends join in, plans feel lighter.",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun VoteTogetherHint(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight } // start below
        ) + fadeIn(animationSpec = tween(500)),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight } // slide down to hide
        ) + fadeOut(animationSpec = tween(200))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .background(
                    color = Color.Transparent, // light gray background
                    shape = RoundedCornerShape(12.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_travel_step_2_modified),
                contentDescription = "Plan Together",
                modifier = Modifier.size(68.dp)
            )

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = "Vote Together",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF222222)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Everyone adds, everyone decides.",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


@Composable
fun EssentialTogetherHint(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight } // start below
        ) + fadeIn(animationSpec = tween(500)),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight } // slide down to hide
        ) + fadeOut(animationSpec = tween(200))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .background(
                    color = Color.Transparent, // light gray background
                    shape = RoundedCornerShape(12.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_travel_step_3_modified),
                contentDescription = "Plan Together",
                modifier = Modifier.size(68.dp)
            )

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = "Only Essentials",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF222222)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Skip the chaos, keep what matters.",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

