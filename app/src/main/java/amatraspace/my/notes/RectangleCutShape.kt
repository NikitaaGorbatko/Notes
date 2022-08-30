package amatraspace.my.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class RectangleCutShape(private val cornerRadius: Float) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = draw(size = size)
        )
    }

    private fun draw(size: Size): Path {
        return Path().apply {
            reset()

            lineTo(size.width * 0.38f, 0f)
            arcTo(
                rect = Rect(
                    left = size.width * 0.38f,
                    top = -cornerRadius,
                    right = size.width * 0.38f + 2 * cornerRadius,
                    bottom = cornerRadius
                ),
                startAngleDegrees = 180.0f,
                sweepAngleDegrees = -90.0f,
                forceMoveTo = false
            )
            lineTo(size.width * 0.62f - cornerRadius, cornerRadius)

            arcTo(
                rect = Rect(
                    left = size.width * 0.62f - 2 * cornerRadius,
                    top = -cornerRadius,
                    right = size.width * 0.62f,
                    bottom = cornerRadius
                ),
                startAngleDegrees = 90.0f,
                sweepAngleDegrees = -90.0f,
                forceMoveTo = false
            )

            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            lineTo(0f, 0f)
            close()
        }
    }
}

@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    cutoutShape: Shape? = null,
    elevation: Dp = AppBarDefaults.BottomAppBarElevation,
    contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
//    val fabPlacement = LocalFabPlacement.current
//    val shape = if (cutoutShape != null && fabPlacement?.isDocked == true) {
//        BottomAppBarCutoutShape(cutoutShape, fabPlacement)
//    } else {
//        RectangleShape
//    }
    // TODO: b/150609566 clarify emphasis for children
    AppBar(
        backgroundColor,
        contentColor,
        elevation,
        contentPadding,
        RectangleCutShape(65f),
        modifier,
        content
    )
}

@Composable
private fun AppBar(
    backgroundColor: Color,
    contentColor: Color,
    elevation: Dp,
    contentPadding: PaddingValues,
    shape: Shape,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = shape,
        modifier = modifier
    ) {
        Row(
            Modifier.fillMaxWidth()
                .padding(contentPadding)
                .height(AppBarHeight),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

private val AppBarHeight = 56.dp
private val BottomNavigationHeight = 56.dp

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,//.background(color = MaterialTheme.colors.primarySurface, shape = RectangleCutShape(65f)),
    backgroundColor: Color = Color.White.copy(alpha = 1f),
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = RectangleCutShape(65f),
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primarySurface, shape = RectangleCutShape(65f))
                .height(BottomNavigationHeight)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}