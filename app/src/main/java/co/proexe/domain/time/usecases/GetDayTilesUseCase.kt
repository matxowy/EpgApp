package co.proexe.domain.time.usecases

import co.proexe.data.time.repository.TimeRepository
import javax.inject.Inject

class GetDayTilesUseCase @Inject constructor(private val repository: TimeRepository) {
    suspend operator fun invoke() = repository.getDayTiles()
}