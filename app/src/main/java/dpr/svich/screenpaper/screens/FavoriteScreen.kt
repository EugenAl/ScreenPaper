package dpr.svich.screenpaper.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dpr.svich.screenpaper.model.Photo

@Composable
fun FavoriteScreen(contentPaddingValues: PaddingValues, navController: NavController){
    val photos = listOf(
        Photo("", ""),
        Photo("", ""),
        Photo("", ""),
        Photo("", ""),
        Photo("", ""),
        Photo("", "")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = contentPaddingValues) {
            photos.map {
                item {
                    ListItem(
                        onItemClick = {
                            Log.i("ListScreen", "Info ${it.src}")
                            navController.navigate("detail")
                        }
                    )
                }
            }
        }
    }
}