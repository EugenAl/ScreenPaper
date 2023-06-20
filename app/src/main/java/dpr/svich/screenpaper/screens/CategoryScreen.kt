package dpr.svich.screenpaper.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dpr.svich.screenpaper.model.Router

data class Category(
    val categoryName: String,
    val photo: String
)

@Composable
fun CategoryScreen(
    contentPaddingValues: PaddingValues,
    navController: NavController
) {
    val categories = listOf(
        Category(
            "Kittens",
            "https://plus.unsplash.com/premium_photo-1675848495392-" +
                    "6b9a3b962df0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90" +
                    "by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80"
        ),
        Category(
            "Vehicles",
            "https://images.unsplash.com/photo-1630165356623-266076e" +
                    "aceb6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfH" +
                    "x8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        ),
        Category(
            "Weapons",
            "https://images.unsplash.com/photo-1595590424283-b8f1784" +
                    "2773f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfH" +
                    "x8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        ),
        Category(
            "Nature",
            "https://images.unsplash.com/photo-1469474968028-56623f02e4" +
                    "2e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8f" +
                    "GVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=874&q=80"
        ),
        Category(
            "Aircraft's",
            "https://images.unsplash.com/photo-1562805612-9c340007e0b" +
                    "9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx" +
                    "8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        ),
        Category(
            "Games",
            "https://images.unsplash.com/photo-1577741314755-048d8525" +
                    "d31e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlf" +
                    "Hx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = contentPaddingValues) {
            categories.map {
                item {
                    CategoryItem(
                        category = it,
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        onItemClick = {
                            Log.i("CategoryList", "Info ${it.categoryName}")
                            navController.navigate(
                                Router.LIST_SCREEN.replace(
                                    "{category}",
                                    it.categoryName
                                )
                            )
                        })
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    category: Category = Category(
        "Kittens", "https://sun9-76.userapi.com/impf/c852228/" +
                "v852228236/d89b2/ucfDWc2RcXw.jpg?size=1831x2160&quality=" +
                "96&sign=b9166ba9ae5230af542a43bf942bc8fb&type=album"
    ),
    backgroundColor: Color = Color.White,
    onItemClick: () -> Unit = {}
) {
    Column(modifier = modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(15.dp))
        .clickable { onItemClick() }
        .background(backgroundColor)
        .fillMaxWidth()) {
        AsyncImage(
            model = category.photo,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        Text(
            text = category.categoryName,
            modifier = Modifier.padding(8.dp),
            color = Color.White
        )
    }
}