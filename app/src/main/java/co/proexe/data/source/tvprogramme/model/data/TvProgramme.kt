package co.proexe.data.source.tvprogramme.model.data

import java.time.LocalDateTime

data class TvProgramme(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val type: String,
    val category: TvProgrammeCategory,
    val isFavourite: Boolean,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val progressPercent: Int
)