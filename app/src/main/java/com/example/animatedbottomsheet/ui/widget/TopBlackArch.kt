package com.example.animatedbottomsheet.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun TopBlackArch(
    archHeight: Dp = 24.dp,
    strokeWidth: Dp = 10.dp,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val archHeightPx = with(density) { archHeight.toPx() }
    val strokeWidthPx = with(density) { strokeWidth.toPx() }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(archHeight + strokeWidth)
    ) {
        val width = size.width
        val centerY = 0f

        drawPath(
            path = Path().apply {
                moveTo(0f, centerY)
                cubicTo(
                    x1 = width / 4f,
                    y1 = -archHeightPx,
                    x2 = 3 * width / 4f,
                    y2 = -archHeightPx,
                    x3 = width,
                    y3 = centerY
                )
            },
            color = Color.Black,
            style = Stroke(width = strokeWidthPx)
        )
    }
}

