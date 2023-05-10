package co.proexe.data.source.tvprogramme

import co.proexe.data.source.tvprogramme.model.data.PreloadedTvProgramme
import retrofit2.Response
import retrofit2.http.GET

interface TvProgrammeApi {

    @GET("/b/GG8C")
    suspend fun getTvPrograms() : Response<List<PreloadedTvProgramme>>
}