package com.mdwst.otterstream.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mdwst.otterstream.ui.navigation.NavRoute
import com.mdwst.otterstream.ui.theme.OTTerStreamColors

@Composable
fun BrowseScreen(navController: NavHostController, category: String) {
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
                        tint = OTTerStreamColors.Purple,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Text(
                    category.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.headlineSmall,
                    color = OTTerStreamColors.White,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            // Grid of content
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(30) { index ->
                    BrowseContentItem(
                        title = "Item $index",
                        onClick = {
                            navController.navigate(
                                NavRoute.Player.createRoute("id_$index", category)
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BrowseContentItem(
    title: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            OTTerStreamColors.PurpleAccent.copy(alpha = 0.3f),
                            OTTerStreamColors.DarkCharcoal
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                title,
                style = MaterialTheme.typography.labelSmall,
                color = OTTerStreamColors.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
