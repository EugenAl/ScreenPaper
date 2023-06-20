package dpr.svich.screenpaper.screens

import android.net.Uri
import android.util.Log
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import dpr.svich.screenpaper.MainViewModel
import dpr.svich.screenpaper.model.Photo
import dpr.svich.screenpaper.model.Router

@Composable
fun ListScreen(
    contentPaddingValues: PaddingValues,
    navController: NavController,
    category: String?,
    model: MainViewModel = viewModel()
) {
    category?.let { model.loadPhoto(it) }
    val photos = model.photos.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp),
            contentPadding = contentPaddingValues
        ) {
            photos.value?.let { list ->
                items(list.size) {
                    ListItem(
                        onItemClick = {
                            Log.i("ListScreen", "Info ${list[it]}")
                            val json = Uri.encode(Gson().toJson(
                                Photo(name = list[it].id, src = list[it].urls.regular)
                            ))
                            navController.navigate(
                                Router.DETAIL_SCREEN.replace("{photo}", json)
                            )
                        },
                        photo = Photo(name = list[it].id, src = list[it].urls.small)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    photo: Photo = Photo(
        name = "sunshine.jpg", src = "https://sun9-76.userapi.com/impf/c852228/" +
                "v852228236/d89b2/ucfDWc2RcXw.jpg?size=1831x2160&quality=" +
                "96&sign=b9166ba9ae5230af542a43bf942bc8fb&type=album"
    ),
    onItemClick: () -> Unit = {}
) {
    Column(modifier = modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(15.dp))
        .clickable { onItemClick() }
        .fillMaxWidth()) {
        AsyncImage(
            model = photo.src,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }

}