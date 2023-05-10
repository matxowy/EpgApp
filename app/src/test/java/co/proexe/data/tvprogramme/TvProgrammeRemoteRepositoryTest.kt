package co.proexe.data.tvprogramme

import co.proexe.data.source.tvprogramme.TvProgrammeApi
import co.proexe.data.source.tvprogramme.TvProgrammeConverter
import co.proexe.data.source.tvprogramme.model.data.PreloadedTvProgramme
import co.proexe.data.tvprogramme.repository.TvProgrammeRemoteRepository
import co.proexe.util.TvProgrammeTestHelper.listOfConvertedTvPrograms
import co.proexe.util.TvProgrammeTestHelper.listOfPreloadedTvPrograms
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class TvProgrammeRemoteRepositoryTest {
    private val response = mockk<Response<List<PreloadedTvProgramme>>> {
        every { body() } returns listOfPreloadedTvPrograms
    }
    private val mockkTvProgrammeApi = mockk<TvProgrammeApi> {
        coEvery { getTvPrograms() } returns response
    }

    private val mockkTvProgrammeConverter = mockk<TvProgrammeConverter> {
        every { convertToTvProgrammeList(any()) } returns listOfConvertedTvPrograms
    }

    private val systemUnderTest = TvProgrammeRemoteRepository(mockkTvProgrammeApi, mockkTvProgrammeConverter)

    @Test
    fun `when getTvPrograms is triggered then getTvPrograms from TvProgrammeApi should be called`() = runTest {
        systemUnderTest.getTvPrograms()

        coVerify(exactly = 1) { mockkTvProgrammeApi.getTvPrograms() }
    }

    @Test
    fun `when getTvPrograms is triggered then converter function from TvProgrammeConverter should be called`() = runTest {
        systemUnderTest.getTvPrograms()

        coVerify(exactly = 1) { mockkTvProgrammeConverter.convertToTvProgrammeList(response) }
    }
}