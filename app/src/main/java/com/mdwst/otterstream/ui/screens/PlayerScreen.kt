package com.mdwst.otterstream.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mdwst.otterstream.ui.theme.OTTerStreamColors

@Composable
fun PlayerScreen(navController: NavHostController, metaId: String, type: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OTTerStreamColors.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Video player area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                OTTerStreamColors.PurpleAccent.copy(alpha = 0.4f),
                                OTTerStreamColors.DarkCharcoal
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = "Play",
                        tint = OTTerStreamColors.Purple,
                        modifier = Modifier.size(64.dp)
                    )
                    Text(
                        "Now Playing: $metaId",
                        style = MaterialTheme.typography.titleMedium,
                        color = OTTerStreamColors.White,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                // Back button
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(OTTerStreamColors.Black.copy(alpha = 0.7f))
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = OTTerStreamColors.Purple
                    )
                }
            }

            // Info section
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text(
                    "Content Title",
                    style = MaterialTheme.typography.headlineSmall,
                    color = OTTerStreamColors.White
                )
                Text(
                    "2024 • Drama",
                    style = MaterialTheme.typography.bodySmall,
                    color = OTTerStreamColors.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    "This is a detailed description of the content being played. It contains information about the plot, cast, and other relevant details.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OTTerStreamColors.White,
                    modifier = Modifier.padding(top = 16.dp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
