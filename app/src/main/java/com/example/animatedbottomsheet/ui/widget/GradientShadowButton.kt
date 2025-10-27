package com.example.animatedbottomsheet.ui.widget

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animatedbottomsheet.ui.theme.AnimatedBottomSheetTheme

@Composable
fun GradientShadowButton(modifier: Modifier = Modifier ,text: String ,  onClick: () -> Unit)  {
    AnimatedBottomSheetTheme {
        Box(modifier.fillMaxWidth().padding(bottom = 50.dp), contentAlignment = Alignment.Center ) {
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()

            // Create transition with pressed state
            val transition = updateTransition(
                targetState = isPressed,
                label = "button_press_transition"
            )

            fun <T> buttonPressAnimation() = tween<T>(
                durationMillis = 250,
                easing = EaseInOut
            )

            // Animate all properties using the transition
            val shadowAlpha by transition.animateFloat(
                label = "shadow_alpha",
                transitionSpec = { buttonPressAnimation() }
            ) { pressed ->
                if (pressed) 0f else 0.4f
            }

            val innerShadowAlpha by transition.animateFloat(
                label = "shadow_alpha",
                transitionSpec = { buttonPressAnimation() }
            ) { pressed ->
                if (pressed) 0.4f else 0f
            }

            val blueDropShadowColor = Color(0xFF13515D)

            val darkRedDropShadowColor = Color(0xFF8C1B25)

            val greenInnerShadowColor1 = Color(0xFF184F59)

            val redInnerShadowColor2 = Color(0xFF54252B)

            val blueDropShadow by transition.animateColor(
                label = "shadow_color",
                transitionSpec = { buttonPressAnimation() }
            ) { pressed ->
                if (pressed) Color.Transparent else blueDropShadowColor
            }

            // [START_EXCLUDE]
            val darkBlueDropShadow by transition.animateColor(
                label = "shadow_color",
                transitionSpec = { buttonPressAnimation() }
            ) { pressed ->
                if (pressed) Color.Transparent else darkRedDropShadowColor
            }

            val innerShadowColor1 by transition.animateColor(
                label = "shadow_color",
                transitionSpec = { buttonPressAnimation() }
            ) { pressed ->
                if (pressed) greenInnerShadowColor1
                else redInnerShadowColor2
            }

            val innerShadowColor2 by transition.animateColor(
                label = "shadow_color",
                transitionSpec = { buttonPressAnimation() }
            ) { pressed ->
                if (pressed) Color(0x330000FF)
                else Color(0x336600FF)
            }

            Box(
                modifier.shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(16.dp), // must match your background shape
                    clip = false // usually false so shadow isn't clipped
                ).clickable(
                        interactionSource, indication = null
                    ) {
                        onClick.invoke()
                    }
                    .width(340.dp)
                    .height(58.dp)
                    .align(Alignment.Center)
                    .dropShadow(
                        shape = RoundedCornerShape(70.dp),
                        shadow = Shadow(
                            radius = 10.dp,
                            spread = 0.dp,
                            color = blueDropShadow,
                            offset = DpOffset(x = 0.dp, -(2).dp),
                            alpha = shadowAlpha
                        )
                    )
                    .dropShadow(
                        shape = RoundedCornerShape(70.dp),
                        shadow = Shadow(
                            radius = 10.dp,
                            spread = 0.dp,
                            color = darkBlueDropShadow,
                            offset = DpOffset(x = 2.dp, 6.dp),
                            alpha = shadowAlpha
                        )
                    )
                    // note that the background needs to be defined before defining the inner shadow
                    .background(
                        color = Color(0xFF000000),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(70.dp),
                        shadow = Shadow(
                            radius = 8.dp,
                            spread = 4.dp,
                            color = innerShadowColor2,
                            offset = DpOffset(x = 4.dp, 0.dp)
                        )
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(70.dp),
                        shadow = Shadow(
                            radius = 20.dp,
                            spread = 4.dp,
                            color = innerShadowColor1,
                            offset = DpOffset(x = 4.dp, 0.dp),
                            alpha = innerShadowAlpha
                        )
                    )

            ) {
                Text(
                    text = text,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Center
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 50.dp,
                            vertical = 18.dp
                        )
                )
            }
        }
    }
}