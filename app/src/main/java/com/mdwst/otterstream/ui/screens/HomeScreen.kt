package com.mdwst.otterstream.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mdwst.otterstream.ui.navigation.NavRoute
import com.mdwst.otterstream.ui.theme.OTTerStreamColors

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OTTerStreamColors.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header with title and actions
            HomeHeader(navController)

            // Featured content section
            FeaturedSection()

            Spacer(modifier = Modifier.height(32.dp))

            // Continue Watching
            CategorySection(
                title = "Continue Watching",
                navController = navController
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Recommended
            CategorySection(
                title = "Recommended",
                navController = navController
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Browse by Type
            BrowseByTypeSection(navController)

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun HomeHeader(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Column {
            // Logo / Title
            Text(
                "OTTerStream",
                style = MaterialTheme.typography.displaySmall,
                color = OTTerStreamColors.Purple,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                "Your curated streaming experience",
                style = MaterialTheme.typography.bodyMedium,
                color = OTTerStreamColors.Gray
            )
        }

        // Search and settings buttons
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(
                onClick = { navController.navigate(NavRoute.Search.route) },
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(OTTerStreamColors.Purple.copy(alpha = 0.2f))
            ) {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = OTTerStreamColors.Purple,
                    modifier = Modifier.size(24.dp)
                )
            }

            IconButton(
                onClick = { navController.navigate(NavRoute.Settings.route) },
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(OTTerStreamColors.Purple.copy(alpha = 0.2f))
            ) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = OTTerStreamColors.Purple,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
private fun FeaturedSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        // Featured banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            OTTerStreamColors.PurpleAccent.copy(alpha = 0.3f),
                            OTTerStreamColors.Black
                        )
                    )
                )
                .clickable { },
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    "Featured Title",
                    style = MaterialTheme.typography.headlineMedium,
                    color = OTTerStreamColors.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    "2024 • Drama",
                    style = MaterialTheme.typography.bodySmall,
                    color = OTTerStreamColors.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OTTerStreamColors.Purple
                        )
                    ) {
                        Text("Play")
                    }
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .height(40.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = OTTerStreamColors.Purple
                        )
                    ) {
                        Text("Info")
                    }
                }
            }
        }
    }
}

@Composable
private fun CategorySection(
    title: String,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleLarge,
            color = OTTerStreamColors.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(5) { index ->
                ContentCard(
                    title = "Title $index",
                    modifier = Modifier.clickable {
                        navController.navigate(NavRoute.Player.createRoute("id_$index", "movie"))
                    }
                )
            }
        }
    }
}

@Composable
private fun ContentCard(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(140.dp)
    ) {
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
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
            Text(
                title,
                style = MaterialTheme.typography.bodySmall,
                color = OTTerStreamColors.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
        Text(
            title,
            style = MaterialTheme.typography.labelMedium,
            color = OTTerStreamColors.White,
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun BrowseByTypeSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            "Browse",
            style = MaterialTheme.typography.titleLarge,
            color = OTTerStreamColors.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BrowseTypeButton(
                label = "Movies",
                onClick = { navController.navigate(NavRoute.Browse.createRoute("movies")) }
            )
            BrowseTypeButton(
                label = "TV Series",
                onClick = { navController.navigate(NavRoute.Browse.createRoute("series")) }
            )
            BrowseTypeButton(
                label = "Addons",
                onClick = { navController.navigate(NavRoute.AddonManager.route) }
            )
        }
    }
}

@Composable
private fun BrowseTypeButton(
    label: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(OTTerStreamColors.DarkCharcoal)
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            label,
            style = MaterialTheme.typography.titleMedium,
            color = OTTerStreamColors.Purple
        )
        Icon(
            Icons.Filled.Add,
            contentDescription = null,
            tint = OTTerStreamColors.Purple,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(24.dp)
        )
    }
}
