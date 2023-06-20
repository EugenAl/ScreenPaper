package dpr.svich.screenpaper.repository

import dpr.svich.screenpaper.BuildConfig
import dpr.svich.screenpaper.model.UnsplashModel
import dpr.svich.screenpaper.model.UnsplashSearch
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashService {

    @Headers(
        "Accept-Version: v1",
        "Authorization: Client-ID 5SuGG4i7dmzHzkAha2MddWQPmLSErf6OMm32S7LDTq8"
    )
    @GET("photos")
    suspend fun getListPhoto(): Response<List<UnsplashModel>>

    @Headers(
        "Accept-Version: v1",
        "Authorization: Client-ID ${BuildConfig.API_KEY}"
    )
    @GET("search/photos")
    suspend fun getSearchCategory(
        @Query("query") category: String,
        @Query("per_page") count: Int = 20
    ): Response<UnsplashSearch>
}