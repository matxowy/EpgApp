package co.proexe.domain.tvprogramme.usecases

import co.proexe.data.tvprogramme.repository.TvProgrammeRepository
import javax.inject.Inject

class GetTvProgramsUseCase @Inject constructor(private val tvProgrammeRepository: TvProgrammeRepository) {
    suspend operator fun invoke() = tvProgrammeRepository.getTvPrograms()
}