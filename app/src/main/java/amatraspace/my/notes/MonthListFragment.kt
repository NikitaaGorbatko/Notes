package amatraspace.my.notes

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MonthListFragment(dateHolder: DateHolder) {
    LazyColumn(
        modifier = Modifier.padding(bottom = 56.dp),
        content = {
            for (i in 1..31) {
                item { DayItem(date = DateHolder(i, 11, 1211)) }
            }
        }
    )
}