package amatraspace.my.notes

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Group(
    title: String,
    children: @Composable ColumnScope.() -> Unit
) {
    Column {
        Text(text = title,
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .padding(top = 30.dp, bottom = 15.dp),
        )
        Column(content = children)
    }
}

@Composable
fun RowEntry(
    icon: ImageVector?,
    label: String,
) {
    Row(
        modifier = Modifier.padding(
            start = 40.dp,
            end = 40.dp,
            top = 10.dp,
            bottom = 10.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(icon != null) {
            Icon(
                icon,
                tint = MaterialTheme.colors.primaryVariant,
                contentDescription = "Menu icon for $label"
            )
        } else {
            Spacer(modifier = Modifier.width(24.dp))
        }

        Text(
            text = label,
            modifier = Modifier.padding(start = 15.dp),
            color = MaterialTheme.colors.onSurface,
            maxLines = 2
        )
    }
}
