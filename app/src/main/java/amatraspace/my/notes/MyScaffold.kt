package amatraspace.my.notes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.charlex.compose.BottomDrawerScaffold
import de.charlex.compose.rememberBottomDrawerScaffoldState

@ExperimentalMaterialApi
@Composable
fun MyScaffold() {
    val navController = rememberNavController()
    val topBarHeight = with(LocalDensity.current) { 56.dp.toPx() }
    val screens = listOf(Screen.Calendar, Screen.Month, Screen.Search, Screen.Account)
    var clickedDay by remember { mutableStateOf(0) }
    var categoryName by remember { mutableStateOf("") }
    var categories by remember { mutableStateOf(listOf("Учеба", "Дом", "Работа", "Задачи")) }
    var isError by remember { mutableStateOf(false) }
    var monthNum by remember { mutableStateOf(CalendarProvider.monthNum) }

    BottomDrawerScaffold(
        scaffoldState = rememberBottomDrawerScaffoldState(drawerTopInset = topBarHeight.toInt()),
        content = {
            NavHost(navController = navController, startDestination = "calendar") {
                composable("calendar") {
//                    CalendarFragment(
//                        year = CalendarProvider.year,
//                        monthNum = CalendarProvider.monthNum,
//                        onArrowClick = {},
//                        clickedDay = -1
//                    )
                }
                composable("month") {
                    MonthListFragment(DateHolder(20, 11, 2022))
                }
                composable("search") {  }
                composable("account") {  }
            }
        },
        bottomBar = {
            BottomNavigation(elevation = 8.dp) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                BottomNavigationItem(
                    //label = { Text(Screen.CALENDAR.string) },
                    selected = currentDestination?.hierarchy?.any { it.route == screens[0].route } == true,
                    onClick = {
                        navController.navigate(screens[0].route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screens[0].icon),
                            tint = Color.White,
                            contentDescription = ""
                        )
                    }
                )
                BottomNavigationItem(
                    //label = { Text(Screen.CALENDAR.string) },
                    selected = currentDestination?.hierarchy?.any { it.route == screens[1].route } == false,
                    onClick = {
                        navController.navigate(screens[1].route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screens[1].icon),
                            tint = Color.White,
                            contentDescription = ""
                        )
                    }
                )
                BottomNavigationItem(
                    //label = {  },
                    enabled = false,
                    selected = false,
                    onClick = {  },
                    icon = {  }
                )
                BottomNavigationItem(
                    //label = { Text(Screen.ALL.string) },

                    selected = currentDestination?.hierarchy?.any { it.route == screens[2].route } == true,
                    onClick = {
                        navController.navigate(screens[2].route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screens[2].icon),
                            tint = Color.White,
                            contentDescription = ""
                        )
                    }
                )
                BottomNavigationItem(
                    //label = { Text(Screen.ALL.string) },
                    selected = currentDestination?.hierarchy?.any { it.route == screens[3].route } == true,
                    onClick = {
                        navController.navigate(screens[3].route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screens[3].icon),
                            tint = Color.White,
                            contentDescription = ""
                        )
                    }
                )
            }
        },
//        floatingActionButtonPosition = FabPosition.End,
//        isFloatingActionButtonDocked = false,
//        floatingActionButton = {
//            FloatingActionButton(
//                shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
//                onClick = {
//
//                },
//            ) {
//                Icon(Icons.Filled.Add, "Add icon")
//            }
//        },
        backgroundColor = Color(0xFFF2F2F2),
        drawerBackgroundColor = Color.Transparent,
        drawerElevation = 0.dp,
        //drawerModifier = Modifier.height(700.dp),
        drawerPeekHeight = 66.dp, //was 66
        drawerContent = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(550.dp)//height of drawer
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 10.dp,
                    ),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                elevation = 4.dp
            ) {
                Column() {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Divider(
                            modifier = Modifier
                                .width(60.dp)
                                .height(3.dp)
                                .background(
                                    color = Color.Black,
                                    shape = RoundedCornerShape(1.dp)
                                ),
                        )
                    }
                    CalendarFragment(
                        year = CalendarProvider.year,
                        monthNum = monthNum,
                        onArrowClick = {
                            if (it) {
                                CalendarProvider.setMonth(++monthNum)
                            } else {
                                CalendarProvider.setMonth(--monthNum)
                            }
                            clickedDay = -1
                        },
                        clickedDay = clickedDay,
                        onDayClick = { clickedDay = DateHolder(it).day }
                    )
                }
            }
        }
    )
}

//Column{
//    Box(modifier = Modifier
//        .fillMaxWidth()
//        .height(25.dp)
//    ) {
//        Divider(
//            Modifier
//                .align(Alignment.Center)
//                .height(3.dp)
//                //.padding(top = 3.dp)
//                .width(60.dp)
//                .background(color = Color.Black)
//        )
//    }
//    LazyColumn(
//        modifier = Modifier
//            //.verticalScroll(ScrollState(initial = 1)) // No remeber. We want the list always start at 0
//            .fillMaxSize(),
//    ) {
//        item {
//            Group(title = "Категории") {
//                categories.forEach {
//                    RowEntry(icon = Icons.Default.ShoppingCart, label = it)
//                }
//            }
//        }
//        item {
//            Row(
//                modifier = Modifier.padding(40.dp, 10.dp)
//            ) {
//                Column(modifier = Modifier.padding(bottom = 40.dp)) {
//                    OutlinedTextField(
//                        value = categoryName,
//                        onValueChange = {
//                            categoryName = it
//                            isError = false
//                        },
//                        label = { Text("Название") },
//                        isError = isError,
//                    )
//                    if (isError) {
//                        Text(
//                            text = "Короткое название",
//                            color = MaterialTheme.colors.error,
//                            style = MaterialTheme.typography.caption,
//                            modifier = Modifier.padding(start = 16.dp)
//                        )
//                    }
//                }
//                Button(
//                    modifier = Modifier.padding(start = 8.dp),
//                    onClick = {
//                        if (categoryName.length < 3) {
//                            isError = true
//                        } else {
//                            categories = categories + categoryName
//                            categoryName = ""
//                        }
//                    },
//                    content = {  }
//                )
//            }
//        }
//        item {
//            Spacer(modifier = Modifier.requiredHeight(136.dp))   //FIXME find a better solution
//        }
//    }
//}




