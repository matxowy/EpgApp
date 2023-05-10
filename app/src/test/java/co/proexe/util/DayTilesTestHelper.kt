package co.proexe.util

import co.proexe.R
import co.proexe.data.source.tvprogramme.model.data.DayTile

object DayTilesTestHelper {
    val mockkListOfDayTiles = listOf(
        DayTile(timestamp = 2, dayLabel = R.string.day_after_tomorrow),
        DayTile(timestamp = 0, dayLabel = R.string.today),
    )
}