package co.proexe.util

import co.proexe.app.utils.extensions.toLocalDateTime
import co.proexe.data.source.tvprogramme.model.data.PreloadedTvProgramme
import co.proexe.data.source.tvprogramme.model.data.TvProgramme
import co.proexe.data.source.tvprogramme.model.data.TvProgrammeCategory
import java.time.LocalDateTime

object TvProgrammeTestHelper {
    val mockkTvProgrammeList = listOf(
        TvProgramme(
            id = 0,
            title = "Ukryta prawda",
            imageUrl = "",
            type = "Paradokument",
            category = TvProgrammeCategory.ALL,
            isFavourite = false,
            startTime = LocalDateTime.now(),
            endTime = LocalDateTime.now().plusHours(2),
            progressPercent = 0
        ),
        TvProgramme(
            id = 1,
            title = "Pojedynek",
            imageUrl = "",
            type = "Kryminał",
            category = TvProgrammeCategory.ALL,
            isFavourite = true,
            startTime = LocalDateTime.now(),
            endTime = LocalDateTime.now().plusHours(2),
            progressPercent = 0
        ),
    )

    const val endTimeDateRaw = "Fri Jul 29 13:45:52 GMT+02:00 2022"
    const val startTimeDateRaw = "Fri Jul 29 11:11:52 GMT+02:00 2022"

    val listOfPreloadedTvPrograms = listOf(
        PreloadedTvProgramme(
            category = "ALL",
            endTimeDateRaw = endTimeDateRaw,
            id = 0,
            imageUrl = "",
            isFavourite = false,
            progressPercent = 45,
            startTimeDateRaw = startTimeDateRaw,
            title = "Pojedynek",
            type = "Kryminal"
        )
    )

    val listOfConvertedTvPrograms = listOf(
        TvProgramme(
            category = TvProgrammeCategory.ALL,
            id = 0,
            imageUrl = "",
            isFavourite = false,
            progressPercent = 45,
            title = "Pojedynek",
            type = "Kryminal",
            startTime = startTimeDateRaw.toLocalDateTime(),
            endTime = endTimeDateRaw.toLocalDateTime(),
        )
    )
}