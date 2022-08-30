package amatraspace.my.notes

import amatraspace.my.notes.ui.theme.Typography
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DayItem(date: DateHolder) {
   Card(
       modifier = Modifier
           .padding(10.dp)
           .fillMaxWidth()
           .wrapContentHeight(),
       shape = MaterialTheme.shapes.medium,
       elevation = 5.dp,
       backgroundColor = MaterialTheme.colors.surface
   ) {
       Row(
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Column(Modifier.padding(start = 16.dp, bottom = 16.dp)) {
               //title
               Text(
                   text = "${date.day}.08.2022",
                   style = Typography.h6,
                   modifier = Modifier.paddingFromBaseline(40.dp)
               )
               //tasks
               Text(
                   text = "Задач нет",
                   style = Typography.body1,
                   modifier = Modifier
                       .paddingFromBaseline(28.dp)
               )
           }
           Box(
               modifier = Modifier
                   .fillMaxHeight()
                   .padding(end = 16.dp),
               contentAlignment = Alignment.Center
           ) {
               Button(onClick = { /*TODO*/ }) {
                   Text("+")
               }
           }
       }
   }
}