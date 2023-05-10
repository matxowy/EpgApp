package co.proexe.data.main.repository

import co.proexe.data.source.tvprogramme.TvProgrammeApi
import co.proexe.data.source.tvprogramme.TvProgrammeConverter
import co.proexe.data.source.tvprogramme.model.data.TvProgramme
import javax.inject.Inject

class MainRepositoryRemoteRepository @Inject constructor(
    private val tvProgrammeApi: TvProgrammeApi,
    private val tvProgrammeConverter: TvProgrammeConverter
) : MainRepository {
    override suspend fun getTvPrograms(): List<TvProgramme> {
        // TODO Add error handling
        val response = tvProgrammeApi.getTvPrograms()
        return tvProgrammeConverter.convertToTvProgrammeList(response)
    }

}