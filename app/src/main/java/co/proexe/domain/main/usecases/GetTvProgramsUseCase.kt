package co.proexe.domain.main.usecases

import co.proexe.data.main.repository.MainRepository
import javax.inject.Inject

class GetTvProgramsUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke() = mainRepository.getTvPrograms()
}