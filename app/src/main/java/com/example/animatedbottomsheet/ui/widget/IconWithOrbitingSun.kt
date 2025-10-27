package com.example.animatedbottomsheet.ui.widget

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun IconWithOrbitingSun(
    modifier: Modifier = Modifier,
    drawableResId: Int,
) {
    // Angle for circular motion (0f to 360f)
    val angle = remember { Animatable(0f) }

    // Start infinite slow rotation (360° = 2π radians ≈ 6.28f)
    LaunchedEffect(drawableResId) {
        while (true) {
            angle.animateTo(
                targetValue = 360f,
                animationSpec = tween(
                    durationMillis = 20_000, // 20 seconds per full orbit (slow!)
                    easing = LinearEasing
                )
            )
            angle.snapTo(0f) // reset to avoid float overflow
        }
    }

    // Radius of orbit (distance from center)
    val orbitRadius = 180.dp

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // 🌟 The sun (small yellow circle)
        Box(
            modifier = Modifier
                .offset {
                    // Convert polar → cartesian coordinates
                    val rad = Math.toRadians(angle.value.toDouble())
                    val x = (orbitRadius.value * cos(rad)).dp
                    val y = (orbitRadius.value * sin(rad)).dp
                    IntOffset(x.roundToPx(), y.roundToPx())
                }
                .size(24.dp)
                .shadow(4.dp, CircleShape)
                .background(Color(0xFFFFD700), shape = CircleShape)
        )

        // 🖼️ Your animated icon
        AnimatedIconBoxInfinity(
            modifier = Modifier.size(300.dp),
            drawableResId = drawableResId
        )
    }
}



@Composable
fun IconWithSunAndMoon(
    modifier: Modifier = Modifier,
    drawableResId: Int,
) {
    val angle = remember { Animatable(0f) }

    LaunchedEffect(drawableResId) {
        while (true) {
            angle.animateTo(
                targetValue = 360f,
                animationSpec = tween(
                    durationMillis = 20_000, // 20 seconds per full orbit
                    easing = LinearEasing
                )
            )
            angle.snapTo(0f)
        }
    }

    val orbitRadius = 180.dp

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // 🌞 Sun (orbits clockwise)
        Box(
            modifier = Modifier
                .offset {
                    val rad = Math.toRadians(angle.value.toDouble())
                    val x = (orbitRadius.value * cos(rad)).dp
                    val y = (orbitRadius.value * sin(rad)).dp
                    IntOffset(x.roundToPx(), y.roundToPx())
                }
                .size(24.dp)
                .shadow(4.dp, CircleShape)
                .background(Color(0xFFFFD700), shape = CircleShape) // gold
        )

        // 🌙 Moon (opposite side: angle + 180°)
        Box(
            modifier = Modifier
                .offset {
                    val oppositeAngle = angle.value + 180f
                    val rad = Math.toRadians(oppositeAngle.toDouble())
                    val x = (orbitRadius.value * cos(rad)).dp
                    val y = (orbitRadius.value * sin(rad)).dp
                    IntOffset(x.roundToPx(), y.roundToPx())
                }
                .size(20.dp)
                .shadow(3.dp, CircleShape)
                .background(Color(0xFFB0BEC5), shape = CircleShape) // soft gray
        )

        // 🖼️ Your animated icon
        AnimatedIconBoxInfinity(
            modifier = Modifier.size(300.dp),
            drawableResId = drawableResId
        )
    }
}