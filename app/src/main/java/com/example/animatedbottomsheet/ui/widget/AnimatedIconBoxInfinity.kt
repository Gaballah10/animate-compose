package com.example.animatedbottomsheet.ui.widget

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedIconBoxInfinity(
    modifier: Modifier = Modifier,
    drawableResId: Int,
) {
    // For bounce entrance (runs once)
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium,
            visibilityThreshold = 0.01f
        ),
        label = "scale"
    )

    // For infinite rotation
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(drawableResId) {
        // Only run bounce once
        // (animateFloatAsState above handles scale from 0 → 1 with bounce)

        while (true) {
            rotation.animateTo(
                targetValue = 50f,
                animationSpec = tween(
                    durationMillis = 40000, // slow: 2 seconds
                    easing = LinearEasing // or EaseInOut for smoother start/end
                )
            )
            rotation.animateTo(
                targetValue = -50f,
                animationSpec = tween(
                    durationMillis = 40000,
                    easing = LinearEasing
                )
            )
        }
    }

    Box(
        modifier = modifier
            .scale(scale)
            .rotate(rotation.value)
    ) {
        Image(
            painter = painterResource(id = drawableResId),
            contentDescription = null,
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Crop
        )
    }
}
