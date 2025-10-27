package com.example.animatedbottomsheet.ui.widget

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun AnimatedIconBox(
    modifier: Modifier = Modifier,
    drawableResId: Int,
) {
    val scale = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(drawableResId) { // Use drawableResId as key if it might change
        // 1. Bounce entrance
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )

        // 2. Rocking rotation: +50° → -50° → 0°
        rotation.animateTo(50f, tween(600, easing = EaseInOut))
        rotation.animateTo(-50f, tween(800, easing = EaseInOut))
        rotation.animateTo(0f, tween(400))
    }

    Box(
        modifier = modifier
            .scale(scale.value)
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