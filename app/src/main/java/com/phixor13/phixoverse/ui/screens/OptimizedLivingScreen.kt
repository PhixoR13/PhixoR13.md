package com.phixor13.phixoverse.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalBar
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun OptimizedLivingScreen(
    uiState: PhixoUiState,
    onToggleGreenJuice: () -> Unit,
    modifier: Modifier = Modifier
) {
    var activeOptimizationNotice by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DeepSpaceDark)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header card: Vida de Indulgencia Optimizada
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("indulgencia_header_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                shape = RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonGreen, NeonCyan, NeonPurple))
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "VIDA DE INDULGENCIA OPTIMIZADA",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        ),
                        color = NeonGreen
                    )
                    Text(
                        text = "SALUD Y PLACER EN EQUILIBRIO CÓSMICO",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = NeonPink
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "El marco reencuadra la búsqueda de la salud de un \"problema técnico\" a un \"problema adaptativo\", utilizando el perfil de comportamiento como guía para un viaje personalizado, indulgente y sin culpa.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        lineHeight = 21.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Framework Hal Wilkerson • Ciclo PDCA • Autor: Josue Illescas Granillo",
                        style = MaterialTheme.typography.labelSmall,
                        color = TextMuted
                    )
                }
            }
        }

        // The 3 Optimization Pillars
        item {
            Text(
                text = "EL MARCO DE OPTIMIZACIÓN",
                style = MaterialTheme.typography.labelSmall,
                color = NeonCyan,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }

        // Pillar 1: Jugo Verde
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleGreenJuice() }
                    .testTag("green_juice_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonGreen, BorderCosmic))
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(NeonGreen.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Eco,
                                contentDescription = null,
                                tint = NeonGreen,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Column {
                            Text(
                                text = "1. Ritual del Jugo Verde",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = NeonGreen
                            )
                            Text(
                                text = "No como restricción, sino como un ritual de empoderamiento matutino.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextSecondary
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(if (uiState.greenJuiceChecked) NeonGreen else SurfaceElevated)
                            .border(1.dp, NeonGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        if (uiState.greenJuiceChecked) {
                            Icon(
                                Icons.Default.Check,
                                contentDescription = "Completado",
                                tint = DeepSpaceDark,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }

        // Pillar 2: SCAMPER Framework
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("scamper_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonPurple, BorderCosmic))
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = NeonPurple,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "2. Marco SCAMPER",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = NeonPurple
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Patio de recreo creativo culinario y de desarrollo técnico: Sustituir, Combinar, Adaptar, Modificar, Proponer otro uso, Eliminar, Revertir.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SurfaceElevated, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "Iteración activa: ${uiState.scamperStep}",
                            style = MaterialTheme.typography.labelSmall,
                            color = ElectricGold
                        )
                    }
                }
            }
        }

        // Pillar 3: PDCA Cycle
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("pdca_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonCyan, BorderCosmic))
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            Icons.Default.Loop,
                            contentDescription = null,
                            tint = NeonCyan,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "3. Ciclo PDCA Continuo",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = NeonCyan
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Plan (Planear) -> Do (Hacer) -> Check (Verificar) -> Act (Actuar): Transforma contratiempos en aprendizaje y evolución continua.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SurfaceElevated, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "Fase actual: ${uiState.pdcaPhase}",
                            style = MaterialTheme.typography.labelSmall,
                            color = NeonCyan
                        )
                    }
                }
            }
        }

        // Balance Section: Salud & Placer
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("balance_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                shape = RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonPink, NeonPurple))
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Default.Favorite, contentDescription = null, tint = NeonPink)
                        Text(
                            text = "SALUD Y PLACER: SOCIOS, NO OPUESTOS",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = NeonPink
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(SurfaceElevated, RoundedCornerShape(10.dp))
                                .padding(12.dp)
                        ) {
                            Column {
                                Text(
                                    text = "Salud Vibrante",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    color = NeonGreen
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Colon y páncreas cuidados con indulgencia consciente, sin castigo ni privación.",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextSecondary
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(SurfaceElevated, RoundedCornerShape(10.dp))
                                .padding(12.dp)
                        ) {
                            Column {
                                Text(
                                    text = "Libertad Mental",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    color = NeonCyan
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Disfrute sin remordimientos, progreso constante y autonomía.",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TextSecondary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { activeOptimizationNotice = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("activate_optimization_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = NeonGreen)
                    ) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = DeepSpaceDark)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "INICIAR OPTIMIZACIÓN CÓSMICA",
                            color = DeepSpaceDark,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    AnimatedVisibility(visible = activeOptimizationNotice) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .background(NeonGreen.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "✨ ¡Optimización iniciada! Equilibrio perfecto entre salud y libertad activado.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = NeonGreen,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
