package co.proexe.presentation.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import co.proexe.R
import co.proexe.app.utils.extensions.toTwoDigitString
import co.proexe.data.source.tvprogramme.model.data.TvProgramme
import co.proexe.presentation.common.CircularLoadingIndicator
import co.proexe.presentation.common.theme.AppDimensions
import co.proexe.presentation.common.theme.AppTypography.appTitle
import co.proexe.presentation.common.theme.AppTypography.dayLabel
import co.proexe.presentation.common.theme.AppTypography.programmeDescription
import co.proexe.presentation.common.theme.AppTypography.programmeTitle
import co.proexe.presentation.common.theme.Blue
import co.proexe.presentation.common.theme.LightDark
import co.proexe.presentation.common.theme.MediumDark
import co.proexe.presentation.common.theme.Silver
import co.proexe.presentation.common.theme.Spacing
import co.proexe.presentation.main.view.MainScreenConstants.MAX_PERCENTAGE
import co.proexe.presentation.main.viewmodel.MainScreenViewModel
import co.proexe.presentation.main.viewmodel.MainScreenViewModel.MainScreenUiState.Loading
import co.proexe.presentation.main.viewmodel.MainScreenViewModel.MainScreenUiState.Success
import coil.compose.AsyncImage
import java.time.LocalDateTime

@Composable
fun MainScreen(
    navController: NavController? = null,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val uiState by mainScreenViewModel.uiState.collectAsState()

    when (uiState) {
        is Loading -> {
            CircularLoadingIndicator()
        }
        is Success -> {
            MainScreenContent(
                dayLabels = (uiState as Success).dayLabelsList,
                tvPrograms = (uiState as Success).tvProgramsList
            )
        }
    }

    LaunchedEffect(Unit) {
        mainScreenViewModel.loadData()
    }
}

@Composable
private fun MainScreenContent(
    dayLabels: List<Int>,
    tvPrograms: List<TvProgramme>
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        AppBar()
        TimeStampsRow(dayLabels)
        ProgramsColumn(tvPrograms)
    }
}

@Composable
private fun TimeStampsRow(dayLabels: List<Int>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = Spacing.veryBigPadding,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(Spacing.smallPadding),
        modifier = Modifier
            .background(LightDark)
            .height(AppDimensions.dayLabelsBarHeight)
            .fillMaxWidth()

    ) {
        items(dayLabels.size) {
            Text(
                text = stringResource(id = dayLabels[it]),
                color = Silver,
                style = dayLabel,
                modifier = Modifier
                    .clickable { } // TODO Add function to manage click on day label
            )
        }
    }
}

@Composable
private fun ProgramsColumn(tvProgramme: List<TvProgramme>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.smallPadding)
    ) {
        items(tvProgramme.size) {
            ProgrammeItem(
                tvProgramme[it].title,
                tvProgramme[it].type,
                tvProgramme[it].startTime,
                tvProgramme[it].endTime,
                tvProgramme[it].imageUrl,
                tvProgramme[it].progressPercent,
            )
            Divider(color = MediumDark, thickness = AppDimensions.itemDividerHeight)
        }
    }
}

@Composable
fun ProgrammeItem(
    title: String,
    type: String,
    startTime: LocalDateTime,
    endTime: LocalDateTime,
    imageUrl: String,
    progressPercent: Int
) {
    val durationFormatted =
        "${startTime.hour.toTwoDigitString()}:${startTime.minute.toTwoDigitString()} - ${endTime.hour.toTwoDigitString()}:${endTime.minute.toTwoDigitString()}"
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.smallPadding)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.logo),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(Spacing.smallPadding)
                .size(AppDimensions.imageSize)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.verySmallPadding),
            modifier = Modifier
                .weight(1f)
                .padding(Spacing.smallPadding)
        ) {
            ProgrammeDescription(title, durationFormatted, type, progressPercent)
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_more_vert_white_24dp),
            contentDescription = "",
            tint = Silver,
            modifier = Modifier
                .padding(Spacing.smallPadding)
        )
    }
}

@Composable
private fun ProgrammeDescription(title: String, durationFormatted: String, type: String, progressPercent: Int) {
    Text(
        text = title,
        color = Silver,
        style = programmeTitle
    )
    Text(
        text = "$durationFormatted | $type",
        color = Silver,
        style = programmeDescription
    )
    LinearProgressIndicator(
        progress = progressPercent.toFloat() / MAX_PERCENTAGE,
        color = Blue,
        trackColor = MediumDark,
        modifier = Modifier
            .padding(bottom = Spacing.mediumPadding)
            .fillMaxWidth()
    )
}


@Composable
private fun AppBar() {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.bigPadding)
    ) {
        LeftSectionOfAppBar()
        RightSectionOfAppBar()
    }
}

@Composable
private fun RightSectionOfAppBar() {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_search),
            contentDescription = "",
            tint = Silver,
            modifier = Modifier
                .padding(Spacing.smallPadding)
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_all),
            contentDescription = "",
            tint = Silver,
            modifier = Modifier
                .padding(Spacing.smallPadding)
        )
    }
}

@Composable
private fun LeftSectionOfAppBar() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_hamburger_v),
            contentDescription = "",
            tint = Silver,
            modifier = Modifier
                .padding(Spacing.smallPadding)
        )
        Text(
            text = "Program TV",
            color = Silver,
            style = appTitle,
            modifier = Modifier
                .padding(start = Spacing.mediumPadding)
        )
    }
}

@Preview(device = "spec:width=411dp,height=891dp", showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

object MainScreenConstants {
    const val MAX_PERCENTAGE = 100
}
