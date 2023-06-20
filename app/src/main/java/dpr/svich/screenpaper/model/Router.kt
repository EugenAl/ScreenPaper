package dpr.svich.screenpaper.model

class Router {
    companion object{
        val MAIN_SCREEN = "category"
        val LIST_SCREEN = "list/{category}"
        val DETAIL_SCREEN = "detail?photo={photo}"

        val FAVORITE_SCREEN = "favorite"
        val SETTINGS_SCREEN = "settings"
    }
}