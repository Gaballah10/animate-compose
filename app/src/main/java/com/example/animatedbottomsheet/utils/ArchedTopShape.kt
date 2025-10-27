
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class ArchedTopShape(
    private val archHeight: Dp = 24.dp,
    private val cornerRadius: Dp = 28.dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val archHeightPx = with(density) { archHeight.toPx() }
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val width = size.width
        val height = size.height

        return Outline.Generic(
            path = Path().apply {
                // Start from bottom-left
                moveTo(0f, height)

                // Left side up
                lineTo(0f, cornerRadiusPx)

                // Top-left rounded corner (optional, but keeps smoothness)
                arcTo(
                    rect = Rect(
                        left = 0f,
                        top = 0f,
                        right = cornerRadiusPx * 2,
                        bottom = cornerRadiusPx * 2
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                // Top edge with arch
                val archWidth = width
                val archCenterX = width / 2f
                val archRadius = archWidth / 2f

                // Move to start of arch (top-left after corner)
                lineTo(cornerRadiusPx, 0f)

                // Alternative: use cubic Bezier for a soft arch
                cubicTo(
                    x1 = archCenterX / 2f,
                    y1 = -archHeightPx,
                    x2 = archCenterX + archCenterX / 2f,
                    y2 = -archHeightPx,
                    x3 = width - cornerRadiusPx,
                    y3 = 0f
                )

                // Top-right rounded corner
                arcTo(
                    rect = Rect(
                        left = width - cornerRadiusPx * 2,
                        top = 0f,
                        right = width,
                        bottom = cornerRadiusPx * 2
                    ),
                    startAngleDegrees = 270f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )

                // Right side down
                lineTo(width, height)

                // Close the path
                close()
            }
        )
    }
}


