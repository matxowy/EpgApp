package co.proexe.data.main.repository

import co.proexe.data.source.tvprogramme.model.data.TvProgramme

interface MainRepository {
    suspend fun getTvPrograms(): List<TvProgramme>
}