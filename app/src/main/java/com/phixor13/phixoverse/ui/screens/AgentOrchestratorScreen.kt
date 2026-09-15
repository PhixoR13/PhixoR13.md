package com.phixor13.phixoverse.ui.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cable
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Transform
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.phixor13.phixoverse.model.AIModelSpec
import com.phixor13.phixoverse.model.McpConnectorSpec
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
fun AgentOrchestratorScreen(
    uiState: PhixoUiState,
    onSelectModel: (String) -> Unit,
    onUpdateChunkInput: (String) -> Unit,
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
        // Section: Token Chunking Sliding Window (agent_maneger.py)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("sliding_window_chunker_card"),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                shape = RoundedCornerShape(16.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonCyan, NeonPurple))
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Transform,
                            contentDescription = "Sliding Window",
                            tint = NeonCyan,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "SLIDING-WINDOW TOKEN ENGINE",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = NeonCyan
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Implementación del script agent_manager.py: prevención de desbordamiento de contexto (límite 1M tokens) mediante particionamiento dinámico.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = uiState.chunkInputSample,
                        onValueChange = onUpdateChunkInput,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("chunk_input_field"),
                        label = { Text("Texto a procesar / Stream de tokens") },
                        minLines = 3,
                        maxLines = 5,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextSecondary,
                            focusedBorderColor = NeonCyan,
                            unfocusedBorderColor = BorderCosmic,
                            focusedLabelColor = NeonCyan,
                            unfocusedLabelColor = TextMuted,
                            focusedContainerColor = SurfaceElevated,
                            unfocusedContainerColor = SurfaceElevated
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Chunk stats badges
                    uiState.chunkingResult?.let { res ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(SurfaceElevated, RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Text("Tokens Est.", style = MaterialTheme.typography.labelSmall, color = TextMuted)
                                    Text("%,d".format(res.estimatedTokens), style = MaterialTheme.typography.bodyMedium, color = NeonCyan, fontWeight = FontWeight.Bold)
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(SurfaceElevated, RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Text("Sub-ventanas", style = MaterialTheme.typography.labelSmall, color = TextMuted)
                                    Text("${res.totalChunks} Chunks", style = MaterialTheme.typography.bodyMedium, color = ElectricGold, fontWeight = FontWeight.Bold)
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(SurfaceElevated, RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Column {
                                    Text("Resumen Persist.", style = MaterialTheme.typography.labelSmall, color = TextMuted)
                                    Text("${res.summaryTokens} tok", style = MaterialTheme.typography.bodyMedium, color = NeonPink, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = if (res.meetsWindowConstraint) NeonGreen else NeonPink,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = if (res.meetsWindowConstraint) "Ventana segura (< 1,000,000 tokens) • Sin desbordamiento de contexto" else "ADVERTENCIA: Contexto crítico",
                                style = MaterialTheme.typography.labelSmall,
                                color = if (res.meetsWindowConstraint) NeonGreen else NeonPink
                            )
                        }
                    }
                }
            }
        }

        // Section: AI Model Selector (Copilot & Multi-Agent)
        item {
            Text(
                text = "MATRIZ DE MODELOS DE IA",
                style = MaterialTheme.typography.labelSmall,
                color = NeonPurple,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }

        items(uiState.aiModels) { model ->
            val isSelected = model.id == uiState.selectedModelId
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectModel(model.id) }
                    .testTag("model_item_${model.id}"),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) SurfaceElevated else SurfaceDark
                ),
                border = if (isSelected) CardDefaults.outlinedCardBorder().copy(
                    brush = Brush.horizontalGradient(listOf(NeonCyan, NeonPurple))
                ) else null
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) NeonPurple else SurfaceElevated),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                if (model.category.contains("Fast")) Icons.Default.Speed else Icons.Default.Psychology,
                                contentDescription = null,
                                tint = if (isSelected) TextPrimary else NeonCyan,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = model.name,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = if (isSelected) NeonCyan else TextPrimary
                                )
                                if (isSelected) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "[ACTIVO]",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = NeonGreen
                                    )
                                }
                            }
                            Text(
                                text = "${model.provider} • Ventana: ${model.contextWindow}",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextMuted
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(BorderCosmic.copy(alpha = 0.4f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = if (model.category.contains("Fast")) "Rápido" else "Inteligente",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (model.category.contains("Fast")) ElectricGold else NeonCyan
                        )
                    }
                }
            }
        }

        // Section: Grok MCP Connectors
        item {
            Text(
                text = "CONECTORES MCP (MODEL CONTEXT PROTOCOL)",
                style = MaterialTheme.typography.labelSmall,
                color = NeonCyan,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 8.dp)
            )
        }

        items(uiState.mcpConnectors) { mcp ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = SurfaceDark)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Default.Cable,
                            contentDescription = null,
                            tint = NeonGreen,
                            modifier = Modifier.size(20.dp)
                        )
                        Column {
                            Text(
                                text = mcp.name,
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                                color = TextPrimary
                            )
                            Text(
                                text = "${mcp.provider} • ${mcp.toolCount} herramientas • ${mcp.authType}",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextMuted
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(NeonGreen.copy(alpha = 0.15f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = mcp.status,
                            style = MaterialTheme.typography.labelSmall,
                            color = NeonGreen,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
