package com.phixor13.phixoverse.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.FolderSpecial
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phixor13.phixoverse.model.DiamondLog
import com.phixor13.phixoverse.ui.theme.BorderCosmic
import com.phixor13.phixoverse.ui.theme.DeepSpaceDark
import com.phixor13.phixoverse.ui.theme.ElectricGold
import com.phixor13.phixoverse.ui.theme.NeonCyan
import com.phixor13.phixoverse.ui.theme.NeonGreen
import com.phixor13.phixoverse.ui.theme.NeonPink
import com.phixor13.phixoverse.ui.theme.NeonPurple
import com.phixor13.phixoverse.ui.theme.SurfaceDark
import com.phixor13.phixoverse.ui.theme.SurfaceElevated
import com.phixor13.phixoverse.ui.theme.TextMuted
import com.phixor13.phixoverse.ui.theme.TextPrimary
import com.phixor13.phixoverse.ui.theme.TextSecondary
import com.phixor13.phixoverse.viewmodel.PhixoUiState

@Composable
fun DashboardScreen(
    uiState: PhixoUiState,
    onClaimReward: () -> Unit,
    onJoinPrediction: () -> Unit,
    onDismissBanner: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DeepSpaceDark)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Banner notification
        item {
            AnimatedVisibility(
                visible = uiState.bannerMessage != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                uiState.bannerMessage?.let { msg ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("banner_notification"),
                        colors = CardDefaults.cardColors(containerColor = SurfaceElevated),
                        border = CardDefaults.outlinedCardBorder().copy(
                            brush = Brush.horizontalGradient(listOf(NeonPurple, NeonCyan))
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = msg,
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextPrimary,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = onDismissBanner,
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Cerrar",
                                    tint = TextSecondary
                                )
                            }
                        }
                    }
                }
            }
        }

        // Hero Space Ranger Identity Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("identity_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                shape = RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonPurple, NeonPink, NeonCyan))
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(listOf(NeonPurple, DeepSpaceDark))
                                )
                                .border(2.dp, NeonCyan, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.RocketLaunch,
                                contentDescription = "Space Ranger",
                                tint = NeonCyan,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = uiState.identity.handle,
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = NeonCyan
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(
                                    Icons.Default.CheckCircle,
                                    contentDescription = "Verificado",
                                    tint = NeonGreen,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Text(
                                text = uiState.identity.title,
                                style = MaterialTheme.typography.bodyMedium,
                                color = NeonPink,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = uiState.identity.bio,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = "Ubicación",
                            tint = TextMuted,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "${uiState.identity.location} (${uiState.identity.coordinates})",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMuted
                        )
                    }
                }
            }
        }

        // Stats Counters Grid (CMC Diamonds, Repos, BLINK Badges)
        item {
            Text(
                text = "ESTADÍSTICAS & RECURSOS",
                style = MaterialTheme.typography.labelSmall,
                color = NeonCyan,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Diamonds Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .testTag("diamond_counter_card"),
                    colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                    border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(ElectricGold, BorderCosmic)))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Diamond,
                                contentDescription = "Diamantes",
                                tint = ElectricGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "CMC DIAMONDS",
                                style = MaterialTheme.typography.labelSmall,
                                color = ElectricGold
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "%,d".format(uiState.totalDiamonds),
                            style = MaterialTheme.typography.headlineMedium,
                            color = TextPrimary
                        )
                        Text(
                            text = "CoinMarketCap Web3",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }

                // Repos Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .testTag("repos_counter_card"),
                    colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                    border = CardDefaults.outlinedCardBorder().copy(brush = Brush.linearGradient(listOf(NeonCyan, BorderCosmic)))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.FolderSpecial,
                                contentDescription = "Repositorios",
                                tint = NeonCyan,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "REPOSITORIOS",
                                style = MaterialTheme.typography.labelSmall,
                                color = NeonCyan
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "14+ Activos",
                            style = MaterialTheme.typography.headlineMedium,
                            color = TextPrimary
                        )
                        Text(
                            text = "PhixoR13 & FIXO",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }
            }
        }

        // Diamond Interactive Actions
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceElevated)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "ACCIONES DE DIAMANTES CMC",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onClaimReward,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("claim_reward_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
                        ) {
                            Icon(Icons.Default.CardGiftcard, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Check-in (+20)")
                        }
                        OutlinedButton(
                            onClick = onJoinPrediction,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("prediction_button"),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = NeonCyan),
                            border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.horizontalGradient(listOf(NeonCyan, NeonPink)))
                        ) {
                            Icon(Icons.Default.TrendingUp, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Predecir (-10/+3)")
                        }
                    }
                }
            }
        }

        // Gaming & Badges Ecosystem
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("gaming_badges_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                border = CardDefaults.outlinedCardBorder().copy(brush = Brush.horizontalGradient(listOf(NeonGreen, BorderCosmic)))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Gamepad,
                            contentDescription = "Gaming",
                            tint = NeonGreen,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ECOSISTEMA GAMING & HARDWARE",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = NeonGreen
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    GamingRow("Xbox Gen9 Console", "Partner Preview · Forza Horizon 6 (FH6) Ready", NeonCyan)
                    GamingRow("Toyota GAZOO Racing", "Toyota GR GT & Performance Aerodynamics", ElectricGold)
                    GamingRow("Weverse BLINK 10th", "Badges: 4 Media · 4 Likes | Roll #33 · Code 0757", NeonPink)
                    GamingRow("Call of Duty Legacy", "Origin de FoP 638 (Modo Carga Explosiva)", TextSecondary)
                }
            }
        }

        // Recent Diamonds Ledger
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "HISTORIAL DE MOVIMIENTOS",
                    style = MaterialTheme.typography.labelSmall,
                    color = NeonCyan
                )
                Text(
                    text = "${uiState.diamondLogs.size} registros",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextMuted
                )
            }
        }

        items(uiState.diamondLogs) { log ->
            DiamondLogRow(log = log)
        }
    }
}

@Composable
private fun GamingRow(label: String, detail: String, accentColor: androidx.compose.ui.graphics.Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(accentColor)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = label, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, fontWeight = FontWeight.SemiBold)
            Text(text = detail, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
        }
    }
}

@Composable
private fun DiamondLogRow(log: DiamondLog) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = log.action,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextPrimary,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = log.date,
                    style = MaterialTheme.typography.labelSmall,
                    color = TextMuted
                )
            }
            Text(
                text = if (log.delta > 0) "+${log.delta}" else "${log.delta}",
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold),
                color = if (log.delta > 0) NeonGreen else NeonPink
            )
        }
    }
}
