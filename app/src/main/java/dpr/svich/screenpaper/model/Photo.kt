package dpr.svich.screenpaper.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val name: String = "",
    val src: String = "",
    val isFavorite: Boolean = false
) : Parcelable