package com.mdwst.otterstream.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun SearchScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    var results by remember { mutableStateOf(emptyList<String>()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OTTerStreamColors.Black)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header with search input
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = OTTerStreamColors.Purple
                    )
                }

                TextField(
                    value = searchQuery,
                    onValueChange = { newValue ->
                        searchQuery = newValue
                        results = if (newValue.isNotEmpty()) {
                            (1..10).map { "Result: $newValue #$it" }
                        } else {
                            emptyList()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(OTTerStreamColors.DarkCharcoal),
                    placeholder = {
                        Text(
                            "Search movies, series...",
                            color = OTTerStreamColors.Gray
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(
                                onClick = { searchQuery = ""; results = emptyList() }
                            ) {
                                Icon(
                                    Icons.Filled.Clear,
                                    contentDescription = "Clear",
                                    tint = OTTerStreamColors.Gray
                                )
                            }
                        }
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = OTTerStreamColors.DarkCharcoal,
                        unfocusedContainerColor = OTTerStreamColors.DarkCharcoal,
                        focusedTextColor = OTTerStreamColors.White,
                        unfocusedTextColor = OTTerStreamColors.White,
                        focusedIndicatorColor = OTTerStreamColors.Purple,
                        unfocusedIndicatorColor = OTTerStreamColors.Purple.copy(alpha = 0.3f)
                    )
                )
            }

            // Search results
            if (results.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(results.size) { index ->
                        SearchResultItem(
                            title = results[index],
                            onClick = {
                                navController.navigate(
                                    NavRoute.Player.createRoute("id_$index", "movie")
                                )
                            }
                        )
                    }
                }
            } else if (searchQuery.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Start typing to search",
                        style = MaterialTheme.typography.bodyLarge,
                        color = OTTerStreamColors.Gray
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchResultItem(
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        OTTerStreamColors.PurpleAccent.copy(alpha = 0.2f),
                        OTTerStreamColors.DarkCharcoal
                    )
                )
            )
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            title,
            style = MaterialTheme.typography.titleMedium,
            color = OTTerStreamColors.White,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}
