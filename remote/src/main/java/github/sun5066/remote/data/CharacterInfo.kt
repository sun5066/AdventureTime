package github.sun5066.remote.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterInfo(
    @field:Json(name = "id") val id: Int = 0,
    @field:Json(name = "slug") val slug: String = "",
    @field:Json(name = "displayName") val displayName: String = "",
    @field:Json(name = "fullName") val fullName: String = "",
    @field:Json(name = "sex") val sex: String = "",
    @field:Json(name = "quotes") val quotes: List<String> = listOf(""),
    @field:Json(name = "sprite") val sprite: String = ""
)
