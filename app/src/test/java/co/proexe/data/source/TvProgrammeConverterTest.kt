package co.proexe.data.source

import co.proexe.data.source.tvprogramme.TvProgrammeConverter
import co.proexe.data.source.tvprogramme.model.data.PreloadedTvProgramme
import co.proexe.util.TvProgrammeTestHelper.listOfConvertedTvPrograms
import co.proexe.util.TvProgrammeTestHelper.listOfPreloadedTvPrograms
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import retrofit2.Response

class TvProgrammeConverterTest {

    private val response = mockk<Response<List<PreloadedTvProgramme>>> {
        every { body() } returns listOfPreloadedTvPrograms
    }

    private val systemUnderTest = TvProgrammeConverter()

    @Test
    fun `convertToTvProgrammeList should convert to correct TvProgrammeList`() {
        val result = systemUnderTest.convertToTvProgrammeList(response)

        result shouldBe listOfConvertedTvPrograms
    }
}