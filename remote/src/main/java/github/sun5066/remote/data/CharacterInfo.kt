package github.sun5066.remote.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class CharacterInfo(
    @field:Json(name = "id") val id: Int = 0,
    @field:Json(name = "slug") val slug: String = "",
    @field:Json(name = "displayName") val displayName: String = "",
    @field:Json(name = "fullName") val fullName: String = "",
    @field:Json(name = "sex") val sex: String = "",
    @field:Json(name = "quotes") val quotes: List<String> = listOf(""),
    @field:Json(name = "sprite") val sprite: String = ""
): Parcelable
