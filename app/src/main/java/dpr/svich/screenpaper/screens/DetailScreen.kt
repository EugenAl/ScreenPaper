package dpr.svich.screenpaper.screens


import android.R.attr.src
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dpr.svich.screenpaper.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL


@Composable
fun DetailScreen(
    photo: Photo = Photo(
        name = "img", src = "https://sun9-76.userapi.com/impf/c852228/" +
                "v852228236/d89b2/ucfDWc2RcXw.jpg?size=1831x2160&quality=" +
                "96&sign=b9166ba9ae5230af542a43bf942bc8fb&type=album"
    )
) {
    val context = LocalContext.current
    Box {
        AsyncImage(
            model = photo.src,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
        )
        Button(
            onClick = { setWallpaperFromUrl(photo.src, context) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(4.dp)
        ) {
            Text(text = "Set as screenpaper")
        }
    }
}

fun setWallpaperFromUrl(src: String, context: Context){
    try {
        runBlocking(Dispatchers.IO) {
            val url = URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            val image = BitmapFactory.decodeStream(input)
            Log.i("DetailsScreen", "Image downloaded")

            val wallpaperManager = WallpaperManager.getInstance(context)
            wallpaperManager.setBitmap(image)
            Log.i("DetailsScreen", "Wallpaper set")
        }
    } catch (e: Exception){
        Log.i("DetailsScreen", "Something went wrong: ${e.message}")
        e.printStackTrace()
    }
}