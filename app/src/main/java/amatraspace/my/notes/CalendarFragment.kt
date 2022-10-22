package amatraspace.my.notes

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun CalendarFragment(
    calendarProvider: CalendarProvider,
    onArrowClick: (b: Boolean) -> Unit,
    clickedDate: Int,
    onDayClick: (date: Int) -> Unit,
) {
    var pagerState = rememberPagerState()
//    LaunchedEffect(pagerState) {
//        pagerState.scrollToPage(12, pageOffset = 0f)
//
//    }
    val coroutineScope = rememberCoroutineScope()
    val months = stringArrayResource(R.array.months)
    val days = stringArrayResource(R.array.days)
    val monthCalendarProviders = mutableListOf<CalendarProvider>()
    for (i in 1..120) {
        monthCalendarProviders.add(CalendarProvider(i))
    }


    val monthName = months[calendarProvider.monthNum]

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp, top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HorizontalPager(count = 120, state = pagerState) { page ->
                Month(
                    daysInPreviousMonth = monthCalendarProviders[page].daysAmount,
                    calendarProvider = monthCalendarProviders[page + 1],
                    clickedDate = clickedDate,
                    onDayClick = { onDayClick(it) },
                    days = days
                )
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
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage - 1,
                                pageOffset = 1f
                            )
                        }
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
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1,
                                pageOffset = 1f
                            )
                        }
                        onArrowClick(true)
                    },
                    content = { Icon(Icons.TwoTone.ArrowForward, contentDescription = null) },
                )
            }
        }
    }
}


@Composable
private fun Month(
    daysInPreviousMonth: Int,
    calendarProvider: CalendarProvider,
    clickedDate: Int,
    onDayClick: (date: Int) -> Unit,
    days: Array<String>
) {
    Column() {
        var counter = 1
        var firstWeek = true
        var wasLast = false
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for (day in days) {
                Day(num = day, style = Style.BODY2)
            }
        }
        for (i in 1..6) {
            if (wasLast) {
                break
            }
            Week(
                daysInPreviousMonth = daysInPreviousMonth,
                calendarProvider = calendarProvider,
                param_counter = counter,
                clickedDate = clickedDate,
                onNext = { counter = it },
                onDayClick = { onDayClick(it) },
                isFirstWeek = firstWeek,
                wasLast = { wasLast = true }
            )
            firstWeek = false
        }
    }

}
typealias DaysRow = MutableList<@Composable () -> Unit>

@Composable
private fun Week(
    daysInPreviousMonth: Int,
    calendarProvider: CalendarProvider,
    param_counter: Int,
    clickedDate: Int,
    onNext: (updatedCounter: Int) -> Unit,
    onDayClick: (date: Int) -> Unit,
    isFirstWeek: Boolean,
    wasLast: () -> Unit
) {
    var counter = param_counter
    var nextMonthCounter = 1
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (i in 1..7) {
            if (isFirstWeek && i >= calendarProvider.firstDayOfFirstWeek || counter <= calendarProvider.daysAmount && !isFirstWeek) {
                Day(
                    num = "${counter++}",
                    //clicked = false,//clickedDate == DateHolder(counter - 1, calendarProvider.monthNum, calendarProvider.year).intDate,
                    clickable = true,
                    onClick = {
                        onDayClick(
                            DateHolder(
                                day = it,
                                month = calendarProvider.monthNum + 1,
                                year = calendarProvider.year
                            ).intDate
                        )
                    }
                )
            } else {
                if (isFirstWeek) {
                    val day = daysInPreviousMonth - (calendarProvider.firstDayOfFirstWeek - 1) + i
                    Day("$day")
                } else {
                    wasLast()
                    Day("${nextMonthCounter++}")
                }

            }
        }
        onNext(counter)
    }
}

