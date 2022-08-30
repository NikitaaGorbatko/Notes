package amatraspace.my.notes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp

private val WEEK_LENGTH = 7

@Composable
fun CalendarFragment(
    year: Int,
    monthNum: Int,
    onArrowClick: (b: Boolean) -> Unit,
    clickedDay: Int,
    onDayClick: (date: Int) -> Unit,
    //distributedItems: List<DistributedItem>,
) {
    //val distributedItemsCopy = distributedItems.toMutableList()
    val months = stringArrayResource(R.array.months)
    CalendarProvider.setMonth(monthNum)
    val days = stringArrayResource(R.array.days)

    val month = months[CalendarProvider.monthNum]
    var counter = 1
    var firstWeek = true

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (day in days) {
                Day(day, style = Style.BODY2)
            }
        }
        for (i in 1..6) {
            Week(
                param_counter = counter,
                clickedDay = clickedDay,
                onNext = { counter = it },
                onDayClick = { onDayClick(it)},
                isFirstWeek = firstWeek
            )
            firstWeek = false
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .paddingFrom(alignmentLine = FirstBaseline, 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    onArrowClick(false)
                },
                content = { Icon(Icons.TwoTone.ArrowBack, contentDescription = null) },
            )
            Text(
                modifier = Modifier.padding(top = 9.dp),
                text = "$year $month",
                style = MaterialTheme.typography.h6,
            )
            IconButton(
                onClick = {
                    onArrowClick(true)
                },
                content = { Icon(Icons.TwoTone.ArrowForward, contentDescription = null) },
            )
        }
    }
    counter = 1
}

@Composable
private fun Week(
    param_counter: Int,
    clickedDay: Int,
    onNext: (updatedCounter: Int) -> Unit,
    onDayClick: (date: Int) -> Unit,
    isFirstWeek: Boolean
) {
    var counter = param_counter
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (i in 1..7) {
            if (isFirstWeek && i >= CalendarProvider.firstDayOfFirstWeek || counter <= CalendarProvider.days && !isFirstWeek) {
                Day(
                    num = "${counter++}",
                    clicked = clickedDay == counter - 1,
                    clickable = true,
                    onClick = { onDayClick(it) }
                )
            } else {
                Day()
            }
        }
        onNext(counter)
    }
}

