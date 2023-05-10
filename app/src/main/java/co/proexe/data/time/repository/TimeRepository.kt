package co.proexe.data.time.repository

import co.proexe.data.source.tvprogramme.model.data.DayTile

interface TimeRepository {
    suspend fun getDayTiles(): List<DayTile>
}