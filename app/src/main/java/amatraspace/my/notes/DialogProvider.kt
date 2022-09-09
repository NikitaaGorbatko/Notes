package amatraspace.my.notes

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@ExperimentalUnitApi
@ExperimentalAnimationApi
@Composable
fun DayDialog(
    dateHolder: DateHolder,
    showSnackbar: ((message: String, actionLabel: String) -> Unit)? = null,
    close: () -> Unit
) {
    var bookName by remember { mutableStateOf("") }
    var bookCost by remember { mutableStateOf("") }

    InternalDialog(dateHolder = dateHolder, showSnackbar = showSnackbar, close = close)
}

@ExperimentalUnitApi
@ExperimentalAnimationApi
@Composable
fun InternalDialog(
    dateHolder: DateHolder,
    showSnackbar: ((message: String, actionLabel: String) -> Unit)? = null,
    close: () -> Unit
) {
    Dialog(properties = DialogProperties(), onDismissRequest = { close() }) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colors.surface)
                .height(intrinsicSize = IntrinsicSize.Min)
                .width(IntrinsicSize.Max)
                .padding(24.dp, 0.dp, 0.dp, 0.dp)
        ) {
//            Box(Modifier.height(56.dp)) {
//                Text(
//                    text = "${dateHolder.day} ${dateHolder.getMonthString(stringArrayResource(id = R.array.months))} ${dateHolder.year}",
//                    style = MaterialTheme.typography.h6,
//                    modifier = Modifier
//                        .paddingFrom(alignmentLine = FirstBaseline, 40.dp)
//                )
//            }
            Column() {
                repeat(4) {
                    BookCard("Заметка №$it", "Текста задачи представлен здесь")
                }
            }
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 8.dp, bottom = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .paddingFrom(alignmentLine = FirstBaseline, 40.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {
                            //onArrowClick(false)
                        },
                        content = { Icon(Icons.TwoTone.ArrowBack, contentDescription = null) },
                    )
                    Text(
                        text = "${dateHolder.day} ${dateHolder.getMonthString(stringArrayResource(id = R.array.months))} ${dateHolder.year}",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .paddingFrom(alignmentLine = FirstBaseline, 40.dp)
                    )
                    IconButton(
                        onClick = {
                            //onArrowClick(true)
                        },
                        content = { Icon(Icons.TwoTone.ArrowForward, contentDescription = null) },
                    )
                }
//                TextButton(
//                    enabled = bookName.length > 3 && try {
//                        bookCost.toInt() > 0
//                    } catch (_: Exception) {
//                        false
//                    },
//                    onClick = {
////                        if (dao.insertBook(bookName, bookCost.toInt()) > 0) {
////                            showSnackbar("$bookName добавлена", "ОК")
////                        }
//                        close()
//                    }
//                ) { Text("ДОБАВИТЬ", style = MaterialTheme.typography.button) }
            }
        }
    }
}
