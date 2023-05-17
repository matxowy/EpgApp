package co.proexe.data.source.tvprogramme

import co.proexe.app.utils.extensions.toLocalDateTime
import co.proexe.data.source.tvprogramme.model.data.PreloadedTvProgramme
import co.proexe.data.source.tvprogramme.model.data.TvProgramme
import co.proexe.data.source.tvprogramme.model.data.TvProgrammeCategory
import retrofit2.Response
import javax.inject.Inject

class TvProgrammeConverter @Inject constructor() {

    fun convertToTvProgrammeList(response: Response<List<PreloadedTvProgramme>>): List<TvProgramme> {
        val preloadedTvProgrammeList = response.body()!!
        return preloadedTvProgrammeList.map { preloadedTvProgramme ->
            convertToTvProgramme(preloadedTvProgramme)
        }
    }

    private fun convertToTvProgramme(preloadedTvProgramme: PreloadedTvProgramme) = TvProgramme(
        id = preloadedTvProgramme.id,
        title = preloadedTvProgramme.title,
        imageUrl = preloadedTvProgramme.imageUrl,
        type = preloadedTvProgramme.type,
        category = setCategory(preloadedTvProgramme.category),
        isFavorite = preloadedTvProgramme.isFavorite,
        startTime = preloadedTvProgramme.startTimeDateRaw.toLocalDateTime(),
        endTime = preloadedTvProgramme.endTimeDateRaw.toLocalDateTime(),
        progressPercent = preloadedTvProgramme.progressPercent
    )

    private fun setCategory(category: String): TvProgrammeCategory =
        when (category) {
            ALL -> TvProgrammeCategory.ALL
            KIDS -> TvProgrammeCategory.KIDS
            EDUCATIONAL -> TvProgrammeCategory.EDUCATIONAL
            MOVIES_AND_SERIES -> TvProgrammeCategory.MOVIES_AND_SERIES
            INFO -> TvProgrammeCategory.INFO
            MUSIC -> TvProgrammeCategory.MUSIC
            GENERAL -> TvProgrammeCategory.GENERAL
            SPORT -> TvProgrammeCategory.SPORT
            LIFESTYLE -> TvProgrammeCategory.LIFESTYLE
            else -> TvProgrammeCategory.ALL
        }

    companion object {
        const val ALL = "ALL"
        const val KIDS = "KIDS"
        const val EDUCATIONAL = "EDUCATIONAL"
        const val MOVIES_AND_SERIES = "MOVIES_AND_SERIES"
        const val INFO = "INFO"
        const val MUSIC = "MUSIC"
        const val GENERAL = "GENERAL"
        const val SPORT = "SPORT"
        const val LIFESTYLE = "LIFESTYLE"
    }

}