package com.example.telematics_engine

import android.graphics.Paint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.atan2
import kotlin.math.min
import kotlin.math.roundToInt

@Composable
fun PieChart(
    modifier: Modifier,
    progressV: Int,
    color: Color,
    isDonut: Boolean = true,
//    percentColor: Color = Color.White
    maxProgress: Float = 1000f
) {
    val progress:Float=progressV.toFloat()/maxProgress

    var activePie by remember {
        mutableStateOf(-1)
    }

    var startAngle = 270f

    BoxWithConstraints(modifier = modifier) {

        val sideSize = min(constraints.maxWidth, constraints.maxHeight)
        val padding = (sideSize * if (isDonut) 30 else 20) / 100f


        val pathPortion = remember {
            Animatable(initialValue = 0f)
        }
        LaunchedEffect(key1 = true) {
            pathPortion.animateTo(
                1f, animationSpec = tween(1000)
            )
        }

        val size = Size(sideSize.toFloat() - padding, sideSize.toFloat() - padding)

        Canvas(
            modifier = Modifier
                .width(sideSize.dp)
                .height(sideSize.dp)
                .pointerInput(true) {

                    if (!isDonut)
                        return@pointerInput

                    detectTapGestures {
                        val clickedAngle = convertTouchEventPointToAngle(
                            sideSize.toFloat(),
                            sideSize.toFloat(),
                            it.x,
                            it.y
                        )
                        return@detectTapGestures
                    }
                }
        ) {

            drawPie(
                color = color,
                startAngle = startAngle,
                arcProgress = progress * 360f,
                size = size,
                padding = padding,
                isDonut = isDonut,
                isActive = true
            )


            drawContext.canvas.nativeCanvas.apply {
                val fontSize = 60.toDp().toPx()
                drawText(
                    "${progress * 100f.roundToInt()}%",
                    (sideSize / 2) + fontSize / 4, (sideSize / 2) + fontSize / 3,
                    Paint().apply {
//                            color = percentColor
                        textSize = fontSize
                        textAlign = Paint.Align.CENTER
                    }
                )
            }
        }
    }

}

private fun DrawScope.drawPie(
    color: Color,
    startAngle: Float,
    arcProgress: Float,
    size: Size,
    padding: Float,
    isDonut: Boolean = false,
    isActive: Boolean = false
): Path {

    return Path().apply {
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = arcProgress,
            useCenter = !isDonut,
            size = size,
            style = if (isDonut) Stroke(
                width = if (isActive) 120f else 100f,
            ) else Fill,

            topLeft = Offset(padding / 2, padding / 2)
        )
    }
}

private fun convertTouchEventPointToAngle(
    width: Float,
    height: Float,
    xPos: Float,
    yPos: Float
): Double {
    var x = xPos - (width * 0.5f)
    val y = yPos - (height * 0.5f)

    var angle = Math.toDegrees(atan2(y.toDouble(), x.toDouble()) + Math.PI / 2)
    angle = if (angle < 0) angle + 360 else angle
    return angle
}

@Preview
@Composable
fun ChartPreview() {
    PieChart(
        modifier = Modifier,
        progressV = 500,
        color = Color(0xFFbf95d4)
    )
}
