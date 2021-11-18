package github.sun5066.remote

import github.sun5066.remote.data.CharacterInfo
import retrofit2.http.GET

interface AdventureTimeService {

    @GET("characters")
    suspend fun getCharacterInfoList(): List<CharacterInfo>
}