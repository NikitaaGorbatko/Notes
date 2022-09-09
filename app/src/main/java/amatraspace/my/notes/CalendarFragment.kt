package amatraspace.my.notes

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun CalendarFragment(
    calendarProvider: CalendarProvider,
    onArrowClick: (b: Boolean) -> Unit,
    clickedDay: Int,
    onDayClick: (date: Int) -> Unit,
) {
    var pagerState = rememberPagerState()
//    LaunchedEffect(pagerState) {
//        pagerState.scrollToPage(12, pageOffset = 0f)
//
//    }
    val months = stringArrayResource(R.array.months)
    val days = stringArrayResource(R.array.days)
    val monthCalendarProviders = mutableListOf<CalendarProvider>()
    for (i in 1..12) {
        monthCalendarProviders.add(CalendarProvider(i))
    }

    val monthName = months[calendarProvider.monthNum]



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(count = 12, state = pagerState) { page ->
            Column(Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
            ) {
                var counter = 1
                var firstWeek = true
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    for (day in days) {
                        Day(num = day, style = Style.BODY2)
                    }
                }
                for (i in 1..6) {
                    Week(
                        firstDayOfFirstWeek = monthCalendarProviders[page].firstDayOfFirstWeek,
                        daysAmount = monthCalendarProviders[page].daysAmount,
                        param_counter = counter,
                        clickedDay = clickedDay,
                        onNext = { counter = it },
                        onDayClick = { onDayClick(it) },
                        isFirstWeek = firstWeek
                    )
                    firstWeek = false
                }
//                Month(
//                    calendarProvider = monthCalendarProviders[pagerState.currentPage],
//                    clickedDay = clickedDay,
//                    onDayClick = { onDayClick(it) },
//                    days = days
//                )
            }
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
                text = "${calendarProvider.year} $monthName",
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
}


@Composable
private fun Month(
    calendarProvider: CalendarProvider,
    clickedDay: Int,
    onDayClick: (date: Int) -> Unit,
    days: Array<String>
) {
    Column() {
        var counter = 1
        var firstWeek = true
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (day in days) {
                Day(num = day, style = Style.BODY2)
            }
        }
        for (i in 1..6) {
            Week(
                firstDayOfFirstWeek = calendarProvider.firstDayOfFirstWeek,
                daysAmount = calendarProvider.daysAmount,
                param_counter = counter,
                clickedDay = clickedDay,
                onNext = { counter = it },
                onDayClick = { onDayClick(it) },
                isFirstWeek = firstWeek
            )
            firstWeek = false
        }
    }

}

@Composable
private fun Week(
    //date: Int,
    firstDayOfFirstWeek: Int,
    daysAmount: Int,
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
            if (isFirstWeek && i >= firstDayOfFirstWeek || counter <= daysAmount && !isFirstWeek) {
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

