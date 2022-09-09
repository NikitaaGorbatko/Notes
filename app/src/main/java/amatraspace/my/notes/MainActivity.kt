package amatraspace.my.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import amatraspace.my.notes.ui.theme.NotesTheme
import android.view.WindowManager
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {

    @ExperimentalPagerApi
    @ExperimentalUnitApi
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NotesTheme {
                // A surface container using the 'background' color from the theme
                MyScaffold()
            }
        }
    }
}