package dpr.svich.screenpaper

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dpr.svich.screenpaper.model.AssetParamType
import dpr.svich.screenpaper.model.Photo
import dpr.svich.screenpaper.model.Router
import dpr.svich.screenpaper.screens.CategoryScreen
import dpr.svich.screenpaper.screens.DetailScreen
import dpr.svich.screenpaper.screens.FavoriteScreen
import dpr.svich.screenpaper.screens.ListScreen
import dpr.svich.screenpaper.screens.SettingsScreen
import dpr.svich.screenpaper.ui.theme.ScreenPaperTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val getPhotos = Controller().apply { start() }
        setContent {
            ScreenPaperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(topBar = {
                        TopAppBar(
                            title = { Text(getString(R.string.app_name)) },
                            actions = {
                                IconButton(onClick = {
                                    navController.navigate(Router.FAVORITE_SCREEN)
                                }) {
                                    Icon(Icons.Default.Favorite, contentDescription = null)
                                }
                                IconButton(onClick = {
                                    navController.navigate(Router.SETTINGS_SCREEN)
                                }) {
                                    Icon(Icons.Default.Settings, contentDescription = null)
                                }
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = Color.White,
                                actionIconContentColor = Color.White
                            )
                        )
                    },
                        content = { contentPadding ->
                            NavHost(
                                navController = navController,
                                startDestination = Router.MAIN_SCREEN
                            ) {
                                composable(Router.MAIN_SCREEN) {
                                    CategoryScreen(
                                        contentPadding,
                                        navController
                                    )
                                }
                                composable(Router.LIST_SCREEN) {
                                    ListScreen(
                                        contentPadding,
                                        navController,
                                        it.arguments?.getString("category")
                                    )
                                }
                                composable(Router.DETAIL_SCREEN,
                                    arguments = listOf(navArgument("photo"){
                                        type = AssetParamType()
                                    })
                                ) {
                                    val photo = it.arguments?.getParcelable<Photo>("photo")
                                    photo?.let {arg ->
                                        DetailScreen(arg)
                                    }
                                }
                                composable(Router.SETTINGS_SCREEN) { SettingsScreen(contentPadding) }
                                composable(Router.FAVORITE_SCREEN) {
                                    FavoriteScreen(
                                        contentPadding,
                                        navController
                                    )
                                }
                            }
                        })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScreenPaperTheme {
        Greeting("Android")
    }
}