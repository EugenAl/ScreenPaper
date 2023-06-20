package dpr.svich.screenpaper.model

data class UnsplashUrls(
    val regular: String,
    val small: String
)

data class UnsplashModel(
    val id: String,
    val description: String,
    val urls: UnsplashUrls
)
