package com.phixor13.phixoverse.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phixor13.phixoverse.model.BeaufortLevel
import com.phixor13.phixoverse.model.NasaTokenInfo
import com.phixor13.phixoverse.model.TokenAllocation
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
import kotlin.math.sin

@Composable
fun CryptoNasaScreen(
    uiState: PhixoUiState,
    onSelectBeaufort: (Int) -> Unit,
    onRenewNasaTokens: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedBeaufort = uiState.beaufortLevels.firstOrNull { it.force == uiState.selectedBeaufortForce }
        ?: uiState.beaufortLevels[6]

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DeepSpaceDark)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section: Solana TRUMP Token
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("trump_token_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                shape = RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonPurple, NeonPink))
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.CurrencyBitcoin,
                                contentDescription = "Crypto",
                                tint = ElectricGold,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "TRUMP TOKEN (SOLANA)",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = TextPrimary
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(NeonGreen.copy(alpha = 0.2f))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "SOLANA MAINNET",
                                style = MaterialTheme.typography.labelSmall,
                                color = NeonGreen
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "CONTRATO OFICIAL:",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                    Text(
                        text = uiState.trumpContract,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        ),
                        color = NeonCyan
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "DESGLOSE DE DISTRIBUCIÓN (TOKENOMICS)",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    uiState.trumpAllocations.forEach { allocation ->
                        AllocationBarRow(allocation)
                    }
                }
            }
        }

        // Section: NASA Earthdata Tokens
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("nasa_tokens_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                shape = RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonCyan, NeonGreen))
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Public,
                                contentDescription = "NASA",
                                tint = NeonCyan,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "NASA EARTHDATA TOKENS",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = NeonCyan
                            )
                        }
                        Button(
                            onClick = onRenewNasaTokens,
                            modifier = Modifier
                                .height(34.dp)
                                .testTag("renew_nasa_tokens_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = NeonGreen),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                        ) {
                            Icon(Icons.Default.Autorenew, contentDescription = null, modifier = Modifier.size(16.dp), tint = DeepSpaceDark)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Renovar", color = DeepSpaceDark, style = MaterialTheme.typography.labelSmall)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "HOST: urs.earthdata.nasa.gov • Válidos hasta 2026-07-19 01:09 EDT",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    uiState.nasaTokens.forEach { token ->
                        NasaTokenRow(token)
                    }
                }
            }
        }

        // Section: NASA Aerodynamics & Beaufort Scale CFD
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("beaufort_cfd_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                shape = RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonCyan, BorderCosmic))
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Air,
                            contentDescription = "Aerodinámica",
                            tint = NeonCyan,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "SIMULADOR AERODINÁMICO & BEAUFORT",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = TextPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Telemetría CFD NASA: Escala Beaufort Fuerza 0 a 12",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Beaufort Chips selector
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(uiState.beaufortLevels) { b ->
                            val isSelected = b.force == uiState.selectedBeaufortForce
                            FilterChip(
                                selected = isSelected,
                                onClick = { onSelectBeaufort(b.force) },
                                label = { Text("F${b.force}") },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = NeonCyan,
                                    selectedLabelColor = DeepSpaceDark,
                                    containerColor = SurfaceElevated,
                                    labelColor = TextSecondary
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // CFD Aerodynamic Canvas Simulation
                    AerodynamicCfdCanvas(force = selectedBeaufort.force)

                    Spacer(modifier = Modifier.height(14.dp))

                    // Selected Beaufort Stats
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        MetricBadge(label = "Fuerza", value = "Force ${selectedBeaufort.force}", color = NeonPink, modifier = Modifier.weight(1f))
                        MetricBadge(label = "Nudos", value = selectedBeaufort.knots, color = NeonCyan, modifier = Modifier.weight(1f))
                        MetricBadge(label = "Velocidad", value = selectedBeaufort.kmh, color = ElectricGold, modifier = Modifier.weight(1f))
                        MetricBadge(label = "Olas", value = selectedBeaufort.waveHeight, color = NeonGreen, modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = selectedBeaufort.title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = NeonCyan
                    )
                    Text(
                        text = selectedBeaufort.observation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun AllocationBarRow(allocation: TokenAllocation) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = allocation.label, style = MaterialTheme.typography.bodyMedium, color = TextPrimary)
            Text(
                text = "${allocation.percentage}%",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace),
                color = NeonCyan
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        LinearProgressIndicator(
            progress = { allocation.percentage / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = if (allocation.percentage >= 15f) NeonPurple else if (allocation.percentage >= 10f) NeonCyan else NeonPink,
            trackColor = SurfaceElevated
        )
        Text(
            text = allocation.description,
            style = MaterialTheme.typography.labelSmall,
            color = TextMuted
        )
    }
}

@Composable
private fun NasaTokenRow(token: NasaTokenInfo) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .background(SurfaceElevated, RoundedCornerShape(8.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = token.center, style = MaterialTheme.typography.bodyMedium, color = TextPrimary, fontWeight = FontWeight.SemiBold)
            Text(text = "Exp: ${token.expiresAt}", style = MaterialTheme.typography.labelSmall, color = TextMuted)
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(if (token.remainingPercent > 0.5f) NeonGreen.copy(alpha = 0.2f) else ElectricGold.copy(alpha = 0.2f))
                .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
            Text(
                text = "${(token.remainingPercent * 100).toInt()}% Activo",
                style = MaterialTheme.typography.labelSmall,
                color = if (token.remainingPercent > 0.5f) NeonGreen else ElectricGold,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun MetricBadge(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(SurfaceElevated, RoundedCornerShape(8.dp))
            .border(1.dp, color.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(text = label, style = MaterialTheme.typography.labelSmall, color = TextMuted, fontSize = 10.sp)
            Text(text = value, style = MaterialTheme.typography.labelSmall, color = color, fontWeight = FontWeight.Bold, maxLines = 1)
        }
    }
}

@Composable
private fun AerodynamicCfdCanvas(force: Int) {
    val infiniteTransition = rememberInfiniteTransition(label = "aerodynamic_loop")
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 6.28318f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = (2000 / (force.coerceAtLeast(1) * 0.4 + 0.6)).toInt().coerceIn(300, 3000),
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "phase"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(DeepSpaceDark)
            .border(1.dp, NeonCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val lineCount = 6
            val amplitude = (force * 3f + 4f).coerceAtMost(height / 4f)

            for (i in 0 until lineCount) {
                val yBase = height * (i + 1) / (lineCount + 1)
                val path = Path()
                path.moveTo(0f, yBase)

                var x = 0f
                while (x <= width) {
                    val angle = (x / width) * 4 * Math.PI.toFloat() + phase + (i * 0.8f)
                    val yOffset = sin(angle) * amplitude
                    path.lineTo(x, yBase + yOffset)
                    x += 10f
                }

                val strokeColor = when {
                    force >= 10 -> NeonPink
                    force >= 6 -> NeonCyan
                    force >= 3 -> NeonGreen
                    else -> NeonPurple
                }

                drawPath(
                    path = path,
                    color = strokeColor.copy(alpha = 0.5f + (i % 2) * 0.3f),
                    style = Stroke(width = (2f + (force * 0.3f)), cap = StrokeCap.Round)
                )
            }

            // Wind particles
            val particleCount = force * 4 + 6
            for (p in 0 until particleCount) {
                val px = ((p * 73f + phase * 80f) % width)
                val py = (height * ((p * 37) % 100) / 100f)
                drawCircle(
                    color = Color.White.copy(alpha = 0.7f),
                    radius = (force * 0.3f + 1.5f),
                    center = Offset(px, py)
                )
            }
        }
    }
}
