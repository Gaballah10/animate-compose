package com.example.animatedbottomsheet.ui.widget

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun HorizontalIconList(iconResIds: List<Int>, reverse: Boolean = true, modifier: Modifier) {
    if (iconResIds.isEmpty()) return

    val repeatCount = 100
    val extendedList = List(repeatCount) { iconResIds }.flatten()
    val initialIndex = (extendedList.size / 2) / iconResIds.size * iconResIds.size

    val lazyListState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth(),
        state = lazyListState,
        flingBehavior = ScrollableDefaults.flingBehavior()
    ) {
        items(
            count = extendedList.size,
            key = { index -> extendedList[index] }
        ) { index ->
            GridItem(drawableId = extendedList[index])
        }
    }

    LaunchedEffect(reverse) { // Restart if direction changes
        var lastTime = System.nanoTime()
        val speedPxPerSec = 50f

        while (true) {
            delay(16)
            if (!lazyListState.isScrollInProgress) {
                val now = System.nanoTime()
                val deltaSec = (now - lastTime) / 1_000_000_000.0f
                lastTime = now

                // 🔁 Reverse direction: negative delta
                val scrollDelta = if (reverse) -speedPxPerSec * deltaSec else speedPxPerSec * deltaSec
                lazyListState.scrollBy(scrollDelta)
            }
        }
    }
}

@Composable
fun GridItem(@DrawableRes drawableId: Int) {
    Box(
        modifier = Modifier
            .size(110.dp) // Adjust size as needed
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFE0F2FE)) // Light blue background
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = null, // or provide meaningful description
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Fit
        )
    }
}