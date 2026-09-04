package com.mdwst.otterstream.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mdwst.otterstream.ui.theme.OTTerStreamColors

@Composable
fun SettingsScreen(navController: NavHostController) {
    var streamQuality by remember { mutableStateOf("1080p") }
    var autoPlay by remember { mutableStateOf(true) }
    var subtitles by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OTTerStreamColors.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = OTTerStreamColors.Purple
                    )
                }
                Text(
                    "Settings",
                    style = MaterialTheme.typography.headlineSmall,
                    color = OTTerStreamColors.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    SettingSection(title = "Playback")
                    SettingToggle(
                        label = "Auto-play",
                        value = autoPlay,
                        onChange = { autoPlay = it }
                    )
                    SettingToggle(
                        label = "Subtitles",
                        value = subtitles,
                        onChange = { subtitles = it }
                    )
                }

                item {
                    SettingSection(title = "Quality")
                    QualityOption(
                        label = "720p",
                        selected = streamQuality == "720p",
                        onClick = { streamQuality = "720p" }
                    )
                    QualityOption(
                        label = "1080p",
                        selected = streamQuality == "1080p",
                        onClick = { streamQuality = "1080p" }
                    )
                    QualityOption(
                        label = "4K",
                        selected = streamQuality == "4K",
                        onClick = { streamQuality = "4K" }
                    )
                }

                item {
                    SettingSection(title = "About")
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(OTTerStreamColors.DarkCharcoal)
                            .padding(16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "OTTerStream v1.0.0",
                            style = MaterialTheme.typography.bodyMedium,
                            color = OTTerStreamColors.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingSection(title: String) {
    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
        color = OTTerStreamColors.Purple,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
private fun SettingToggle(
    label: String,
    value: Boolean,
    onChange: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(OTTerStreamColors.DarkCharcoal)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                label,
                style = MaterialTheme.typography.bodyMedium,
                color = OTTerStreamColors.White
            )
            Switch(
                checked = value,
                onCheckedChange = onChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = OTTerStreamColors.Purple,
                    checkedTrackColor = OTTerStreamColors.PurpleAccent.copy(alpha = 0.5f)
                )
            )
        }
    }
}

@Composable
private fun QualityOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (selected) OTTerStreamColors.Purple.copy(alpha = 0.3f)
                else OTTerStreamColors.DarkCharcoal
            )
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyMedium,
            color = if (selected) OTTerStreamColors.Purple else OTTerStreamColors.White
        )
    }
}
