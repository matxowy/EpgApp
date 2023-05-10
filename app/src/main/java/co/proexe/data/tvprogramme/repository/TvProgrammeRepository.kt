package co.proexe.data.tvprogramme.repository

import co.proexe.data.source.tvprogramme.model.data.TvProgramme

interface TvProgrammeRepository {
    suspend fun getTvPrograms(): List<TvProgramme>
}