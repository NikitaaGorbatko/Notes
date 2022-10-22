package amatraspace.my.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

enum class Style { BODY1, BODY2, BODY3 }

data class DateHolder(val day: Int, val month: Int, val year: Int) {
    val intDate: Int
        get() = year * 10000 + month * 100 + day

    constructor(date: Int) : this(date % 100, (date % 10000 - (date % 100)) / 100, date / 10000)

    fun stringDate(): String {
        val day = if (day < 10) "0${day}" else day
        val month = if (month < 10) "0${month}" else month
        return "$day.$month.$year"
    }

    fun getMonthString(array: Array<String>) = array[month]
}

@Composable
fun Day(
    //date: Int? = null,//date in dateHolder format
    num: String = "",
    style: Style = Style.BODY1,
    clickable: Boolean = false,
    onClick: (date: Int) -> Unit = { _ -> },
    //clicked: Boolean = false,
) {
    val modifier = if (clickable) {
        Modifier
            .size(50.dp)
            //.padding(1.dp)
            .clip(RoundedCornerShape(10.dp))
            //.border(1.dp, Color.Black, RoundedCornerShape(10.dp))
            //.background(if (clicked) Color.LightGray else Color.Unspecified)
            .clickable { onClick(num.toInt()) }
    } else {
        val isNull = num.toIntOrNull() ?: 0
        if (isNull == 0) {
            Modifier.size(50.dp)
        } else {
            Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.LightGray)
        }
            //.padding(1.dp)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = num,
            color = if (style == Style.BODY3) {Color.LightGray} else Color.Black,
            style = when (style) {
                Style.BODY1 -> MaterialTheme.typography.body1
                Style.BODY2 -> MaterialTheme.typography.body2
                Style.BODY3 -> MaterialTheme.typography.body1
            },
            textAlign = TextAlign.Center
        )
//            val a = try {
//                num.toInt()
//            } catch (ex: Exception) { -1 }
//
//            if (a % 10 == 0) {
//                Divider(
//                    Modifier
//                        //.align(Alignment.Center)
//                        .height(2.dp)
//                        //.padding(top = 3.dp)
//                        .width(10.dp)
//                        .background(color = Color.DarkGray, shape = RoundedCornerShape(100))
//                )
//            }

    }
}