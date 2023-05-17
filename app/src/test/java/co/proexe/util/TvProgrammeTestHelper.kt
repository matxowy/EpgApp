package co.proexe.util

import co.proexe.app.utils.extensions.toLocalDateTime
import co.proexe.data.source.tvprogramme.model.data.PreloadedTvProgramme
import co.proexe.data.source.tvprogramme.model.data.TvProgramme
import co.proexe.data.source.tvprogramme.model.data.TvProgrammeCategory
import java.time.LocalDateTime

object TvProgrammeTestHelper {
    const val startTime = "2023-05-17T09:34:50"
    const val endTime = "2023-05-17T11:34:50"
    const val endTimeDateRaw = "Fri Jul 29 13:45:52 GMT+02:00 2022"
    const val startTimeDateRaw = "Fri Jul 29 11:11:52 GMT+02:00 2022"

    val mockkTvProgrammeList = listOf(
        TvProgramme(
            id = 0,
            title = "Ukryta prawda",
            imageUrl = "",
            type = "Paradokument",
            category = TvProgrammeCategory.ALL,
            isFavorite = false,
            startTime = LocalDateTime.parse(startTime),
            endTime = LocalDateTime.parse(endTime),
            progressPercent = 0
        ),
        TvProgramme(
            id = 1,
            title = "Pojedynek",
            imageUrl = "",
            type = "Kryminał",
            category = TvProgrammeCategory.ALL,
            isFavorite = true,
            startTime = LocalDateTime.parse(startTime),
            endTime = LocalDateTime.parse(endTime),
            progressPercent = 0
        ),
    )
    val mockkNoFavoriteTvProgrammeList = listOf(
        TvProgramme(
            id = 0,
            title = "Ukryta prawda",
            imageUrl = "",
            type = "Paradokument",
            category = TvProgrammeCategory.ALL,
            isFavorite = false,
            startTime = LocalDateTime.parse(startTime),
            endTime = LocalDateTime.parse(endTime),
            progressPercent = 0
        ),
        TvProgramme(
            id = 1,
            title = "Pojedynek",
            imageUrl = "",
            type = "Kryminał",
            category = TvProgrammeCategory.ALL,
            isFavorite = false,
            startTime = LocalDateTime.parse(startTime),
            endTime = LocalDateTime.parse(endTime),
            progressPercent = 0
        ),
        TvProgramme(
            id = 2,
            title = "Hobbit",
            imageUrl = "",
            type = "Przygodowy",
            category = TvProgrammeCategory.KIDS,
            isFavorite = false,
            startTime = LocalDateTime.parse(startTime),
            endTime = LocalDateTime.parse(endTime),
            progressPercent = 0
        ),
        TvProgramme(
            id = 3,
            title = "Harry Potter",
            imageUrl = "",
            type = "Przygodowy",
            category = TvProgrammeCategory.KIDS,
            isFavorite = false,
            startTime = LocalDateTime.parse(startTime),
            endTime = LocalDateTime.parse(endTime),
            progressPercent = 0
        ),
    )

    val mockkFavoriteOnTopTvProgrammeList = listOf(
        TvProgramme(
            id = 1,
            title = "Pojedynek",
            imageUrl = "",
            type = "Kryminał",
            category = TvProgrammeCategory.ALL,
            isFavorite = true,
            startTime = LocalDateTime.parse(startTime),
            endTime = LocalDateTime.parse(endTime),
            progressPercent = 0
        ),
        TvProgramme(
            id = 0,
            title = "Ukryta prawda",
            imageUrl = "",
            type = "Paradokument",
            category = TvProgrammeCategory.ALL,
            isFavorite = false,
            startTime = LocalDateTime.parse(startTime),
            endTime = LocalDateTime.parse(endTime),
            progressPercent = 0
        ),
        TvProgramme(
            id = 2,
            title = "Hobbit",
            imageUrl = "",
            type = "Przygodowy",
            category = TvProgrammeCategory.KIDS,
            isFavorite = false,
            startTime = LocalDateTime.parse(startTime),
            endTime = LocalDateTime.parse(endTime),
            progressPercent = 0
        ),
        TvProgramme(
            id = 3,
            title = "Harry Potter",
            imageUrl = "",
            type = "Przygodowy",
            category = TvProgrammeCategory.KIDS,
            isFavorite = false,
            startTime = LocalDateTime.parse(startTime),
            endTime = LocalDateTime.parse(endTime),
            progressPercent = 0
        ),
    )

    val listOfPreloadedTvPrograms = listOf(
        PreloadedTvProgramme(
            category = "ALL",
            endTimeDateRaw = endTimeDateRaw,
            id = 0,
            imageUrl = "",
            isFavorite = false,
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
            isFavorite = false,
            progressPercent = 45,
            title = "Pojedynek",
            type = "Kryminal",
            startTime = startTimeDateRaw.toLocalDateTime(),
            endTime = endTimeDateRaw.toLocalDateTime(),
        )
    )
}