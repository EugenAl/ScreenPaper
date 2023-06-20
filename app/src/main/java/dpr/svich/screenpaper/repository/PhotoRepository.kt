package dpr.svich.screenpaper.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dpr.svich.screenpaper.model.UnsplashModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotoRepository {

    val BASE_URL = "https://api.unsplash.com/"

    private suspend fun loadListPhotos(service: UnsplashService, category: String): List<UnsplashModel> {
        return service.getSearchCategory(category).body()?.results ?: emptyList()
    }

    suspend fun loadPhoto(category: String): List<UnsplashModel>{
        val gson: Gson = GsonBuilder().setLenient().create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val service = retrofit.create(UnsplashService::class.java)

        return PhotoRepository().loadListPhotos(service, category)
    }
}