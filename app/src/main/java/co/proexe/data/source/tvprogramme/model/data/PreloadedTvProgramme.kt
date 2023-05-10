package co.proexe.data.source.tvprogramme.model.data

data class PreloadedTvProgramme(
    val category: String,
    val endTimeDateRaw: String,
    val id: Int,
    val imageUrl: String,
    val isFavourite: Boolean,
    val progressPercent: Int,
    val startTimeDateRaw: String,
    val title: String,
    val type: String
)