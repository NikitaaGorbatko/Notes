package amatraspace.my.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.lang.Exception

enum class Style { BODY1, BODY2 }

data class DateHolder(val day: Int, val month: Int, val year: Int) {
    val intDate: Int
        get() = year * 10000 + month * 100 + day

    constructor(date: Int) : this(date % 100, (date % 10000 - (date % 100)) / 100, date / 10000)

    fun stringDate(): String {
        val day = if (day < 10) "0${day}" else day
        val month = if (month < 10) "0${month}" else month
        return "$day.$month.$year"
    }
}

@Composable
fun Day(
    num: String = "",
    style: Style = Style.BODY1,
    clickable: Boolean = false,
    onClick: (date: Int) -> Unit = { _ -> },
    //list: List<DistributedItem> = listOf(),
    clicked: Boolean = false,
    maxWidth: Dp = 0.dp,
) {
    val modifier = if (clickable) {
        val date = DateHolder(num.toInt(), CalendarProvider.monthNum, CalendarProvider.year)
        Modifier
            .height(50.dp)
            .width(50.dp)
            .padding(1.dp)
            .clip(RoundedCornerShape(10.dp))
            //.border(1.dp, Color.Black, RoundedCornerShape(10.dp))
            .background(if (clicked) Color.LightGray else Color.Unspecified)
            .clickable { onClick(date.intDate) }
    } else {
        Modifier
            .height(50.dp)
            .width(50.dp)
            .padding(1.dp)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = num,
                style = if (style == Style.BODY1) {
                    MaterialTheme.typography.body1
                } else {
                    MaterialTheme.typography.body2
                },
                textAlign = TextAlign.Center
            )
            val a = try {
                num.toInt()
            } catch (ex: Exception) { -1 }

            if (a % 10 == 0) {
                Divider(
                    Modifier
                        //.align(Alignment.Center)
                        .height(2.dp)
                        //.padding(top = 3.dp)
                        .width(10.dp)
                        .background(color = Color.DarkGray, shape = RoundedCornerShape(100))
                )
            }

        }

    }
}