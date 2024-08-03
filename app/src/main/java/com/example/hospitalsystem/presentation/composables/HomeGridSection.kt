package com.example.hospitalsystem.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GridSection(
    colorCard1: Long,
    colorCard2: Long,
    colorCard3: Long,
    colorCard4: Long,
    colorCard5: Long,
    titleCard1: String,
    titleCard2: String,
    titleCard3: String,
    titleCard4: String,
    titleCard5: String,
    iconCard1: Int,
    iconCard2: Int,
    iconCard3: Int,
    iconCard4: Int,
    iconCard5: Int,
    onClickCard1: () -> Unit,
    onClickCard2: () -> Unit,
    onClickCard3: () -> Unit,
    onClickCard4: () -> Unit,
    onClickCard5: () -> Unit,
    isCard5Visible: Boolean,
    isPortrait: Boolean
) {
    var card1Clicked by remember { mutableStateOf(false) }
    var card2Clicked by remember { mutableStateOf(false) }
    var card3Clicked by remember { mutableStateOf(false) }
    var card4Clicked by remember { mutableStateOf(false) }
    var card5Clicked by remember { mutableStateOf(false) }

    if (isPortrait) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(
                    modifier = Modifier.wrapContentWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HomeCard(
                        title = titleCard1,
                        color = Color(colorCard1),
                        modifier = Modifier
                            .height(220.dp)
                            .width(170.dp),
                        iconDrawable = iconCard1,
                        enabled = !card1Clicked
                    ) {
                        card1Clicked = true
                        onClickCard1()
                    }
                    HomeCard(
                        title = titleCard2,
                        color = Color(colorCard2),
                        modifier = Modifier
                            .height(180.dp)
                            .width(170.dp),
                        iconDrawable = iconCard2,
                        enabled = !card2Clicked
                    ) {
                        card2Clicked = true
                        onClickCard2()
                    }
                }
                Column(
                    modifier = Modifier.wrapContentWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    HomeCard(
                        title = titleCard3,
                        color = Color(colorCard3),
                        modifier = Modifier
                            .height(180.dp)
                            .width(170.dp),
                        iconDrawable = iconCard3,
                        enabled = !card3Clicked
                    ) {
                        card3Clicked = true
                        onClickCard3()
                    }
                    HomeCard(
                        title = titleCard4,
                        color = Color(colorCard4),
                        modifier = Modifier
                            .height(220.dp)
                            .width(170.dp),
                        iconDrawable = iconCard4,
                        enabled = !card4Clicked
                    ) {
                        card4Clicked = true
                        onClickCard4()
                    }
                }
            }
            if (isCard5Visible) {
                HomeCard(
                    title = titleCard5,
                    color = Color(colorCard5),
                    modifier = Modifier
                        .height(180.dp)
                        .wrapContentWidth(),
                    iconDrawable = iconCard5,
                    enabled = !card5Clicked
                ) {
                    card5Clicked = true
                    onClickCard5()
                }
            }
        }
    } else {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HomeCard(
                title = titleCard1,
                color = Color(colorCard1),
                modifier = Modifier
                    .height(220.dp)
                    .weight(1f),
                iconDrawable = iconCard1,
                enabled = !card1Clicked
            ) {
                card1Clicked = true
                onClickCard1()
            }
            HomeCard(
                title = titleCard2,
                color = Color(colorCard2),
                modifier = Modifier
                    .height(180.dp)
                    .weight(1f),
                iconDrawable = iconCard2,
                enabled = !card2Clicked
            ) {
                card2Clicked = true
                onClickCard2()
            }
            HomeCard(
                title = titleCard3,
                color = Color(colorCard3),
                modifier = Modifier
                    .height(180.dp)
                    .weight(1f),
                iconDrawable = iconCard3,
                enabled = !card3Clicked
            ) {
                card3Clicked = true
                onClickCard3()
            }
            HomeCard(
                title = titleCard4,
                color = Color(colorCard4),
                modifier = Modifier
                    .height(220.dp)
                    .weight(1f),
                iconDrawable = iconCard4,
                enabled = !card4Clicked
            ) {
                card4Clicked = true
                onClickCard4()
            }
            if (isCard5Visible) {
                HomeCard(
                    title = titleCard5,
                    color = Color(colorCard5),
                    modifier = Modifier
                        .height(180.dp)
                        .weight(1f),
                    iconDrawable = iconCard5,
                    enabled = !card5Clicked
                ) {
                    card5Clicked = true
                    onClickCard5()
                }
            }
        }
    }
}