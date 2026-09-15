package com.phixor13.phixoverse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phixor13.phixoverse.model.AIModelSpec
import com.phixor13.phixoverse.model.AppTab
import com.phixor13.phixoverse.model.BeaufortLevel
import com.phixor13.phixoverse.model.ChunkingResult
import com.phixor13.phixoverse.model.DiamondLog
import com.phixor13.phixoverse.model.McpConnectorSpec
import com.phixor13.phixoverse.model.NasaTokenInfo
import com.phixor13.phixoverse.model.ProfileIdentity
import com.phixor13.phixoverse.model.TokenAllocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PhixoUiState(
    val currentTab: AppTab = AppTab.DASHBOARD,
    val identity: ProfileIdentity = ProfileIdentity(),
    val totalDiamonds: Int = 5748,
    val diamondLogs: List<DiamondLog> = listOf(
        DiamondLog("1", "15 May 2026", "Daily Reward", 20),
        DiamondLog("2", "14 May 2026", "Price Prediction Winner", 3),
        DiamondLog("3", "14 May 2026", "Daily Reward", 20),
        DiamondLog("4", "14 May 2026", "Join Price Prediction", -10),
        DiamondLog("5", "13 May 2026", "App Bonus", 10),
        DiamondLog("6", "13 May 2026", "Daily Reward", 10),
        DiamondLog("7", "13 May 2026", "Join Price Prediction", -10)
    ),
    val trumpContract: String = "6p6xgHyF7AeE6TZkSmFsko444wqoP15icUSqi2jfGiPN",
    val trumpAllocations: List<TokenAllocation> = listOf(
        TokenAllocation("Creators & CIG Digital 1", 36.00f, "8sD...1vA", "Primary Creator Reserve & Core Development"),
        TokenAllocation("Creators & CIG Digital 2", 18.00f, "5kP...9zL", "Strategic Partnerships & Operations"),
        TokenAllocation("Creators & CIG Digital 3", 18.00f, "2mX...4tQ", "Ecosystem Staking & Infrastructure"),
        TokenAllocation("Liquidity Provisioning", 10.00f, "Raydium / Orca", "DEX Automated Market Maker Pools"),
        TokenAllocation("Public Community", 10.00f, "Fair Launch / ICO", "Global Trading & Holders Distribution"),
        TokenAllocation("Creators & CIG Digital 4", 4.00f, "7jR...3bV", "Marketing & Global Outreach"),
        TokenAllocation("Creators & CIG Digital 5", 2.00f, "9wT...6yC", "Security Audits & Bug Bounties"),
        TokenAllocation("Creators & CIG Digital 6", 2.00f, "4qL...1mE", "Emergency Reserve Fund")
    ),
    val nasaTokens: List<NasaTokenInfo> = listOf(
        NasaTokenInfo("T1", "OB.DAAC (Ocean Biology)", "https://urs.earthdata.nasa.gov", "2026-07-19 01:09 EDT", 0.75f, true),
        NasaTokenInfo("T2", "PO.DAAC (Physical Oceanography)", "https://urs.earthdata.nasa.gov", "2026-07-19 01:09 EDT", 0.75f, true),
        NasaTokenInfo("T3", "LP.DAAC (Land Processes)", "https://urs.earthdata.nasa.gov", "2026-07-19 01:09 EDT", 0.75f, true),
        NasaTokenInfo("T4", "GES.DISC (Atmospheric Data)", "https://urs.earthdata.nasa.gov", "2026-07-19 01:09 EDT", 0.75f, true)
    ),
    val beaufortLevels: List<BeaufortLevel> = listOf(
        BeaufortLevel(0, "Calm", "< 1 knot", "< 1 km/h", "0 m", "Sea like a mirror; smoke rises vertically."),
        BeaufortLevel(1, "Light Air", "1 - 3 knots", "1 - 5 km/h", "0.1 m", "Ripples with appearance of scales; smoke drift indicates wind."),
        BeaufortLevel(2, "Light Breeze", "4 - 6 knots", "6 - 11 km/h", "0.2 - 0.3 m", "Small wavelets; wind felt on face; leaves rustle."),
        BeaufortLevel(3, "Gentle Breeze", "7 - 10 knots", "12 - 19 km/h", "0.6 - 1.0 m", "Large wavelets; crests begin to break; light flags extended."),
        BeaufortLevel(4, "Moderate Breeze", "11 - 16 knots", "20 - 28 km/h", "1.0 - 1.5 m", "Small waves becoming longer; fairly frequent white horses."),
        BeaufortLevel(5, "Fresh Breeze", "17 - 21 knots", "29 - 38 km/h", "2.0 - 2.5 m", "Moderate waves taking more pronounced long form; many white horses."),
        BeaufortLevel(6, "Strong Breeze", "22 - 27 knots", "39 - 49 km/h", "3.0 - 4.0 m", "Large waves begin to form; extensive white foam crests everywhere."),
        BeaufortLevel(7, "High Wind / Near Gale", "28 - 33 knots", "50 - 61 km/h", "4.0 - 5.5 m", "Sea heaps up; white foam from breaking waves blows in streaks."),
        BeaufortLevel(8, "Gale", "34 - 40 knots", "62 - 74 km/h", "5.5 - 7.5 m", "Moderately high waves of greater length; edges of crests break into spindrift."),
        BeaufortLevel(9, "Strong Gale", "41 - 47 knots", "75 - 88 km/h", "7.0 - 10.0 m", "High waves; dense streaks of foam along wind direction; spray affects visibility."),
        BeaufortLevel(10, "Storm", "48 - 55 knots", "89 - 102 km/h", "9.0 - 12.5 m", "Very high waves with long overhanging crests; sea surface takes white appearance."),
        BeaufortLevel(11, "Violent Storm", "56 - 63 knots", "103 - 117 km/h", "11.5 - 16.0 m", "Exceptionally high waves; small ships lost to view behind waves."),
        BeaufortLevel(12, "Hurricane Force", "≥ 64 knots", "≥ 118 km/h", "≥ 14.0 m", "Air filled with foam and spray; sea completely white; driving spray severely limits visibility.")
    ),
    val selectedBeaufortForce: Int = 6,
    val aiModels: List<AIModelSpec> = listOf(
        AIModelSpec("gpt-5.6-terra", "GPT-5.6 Terra", "Versatile & Highly Intelligent", "1,000,000 tokens", "OpenAI / GitHub Copilot", true),
        AIModelSpec("gpt-5.6-sol", "GPT-5.6 Sol", "Versatile & Highly Intelligent", "1,000,000 tokens", "OpenAI / GitHub Copilot"),
        AIModelSpec("gpt-5.6-luna", "GPT-5.6 Luna", "Versatile & Highly Intelligent", "1,000,000 tokens", "OpenAI / GitHub Copilot"),
        AIModelSpec("claude-sonnet-5", "Claude Sonnet 5", "Versatile & Highly Intelligent", "1,000,000 tokens", "Anthropic / GitHub Copilot"),
        AIModelSpec("claude-sonnet-4.6", "Claude Sonnet 4.6", "Versatile & Highly Intelligent", "500,000 tokens", "Anthropic / GitHub Copilot"),
        AIModelSpec("kimi-k2.7", "Kimi K2.7 Code", "Fast & Cost-Efficient", "256,000 tokens", "Moonshot / GitHub Copilot"),
        AIModelSpec("gpt-5.4-mini", "GPT-5.4 mini", "Fast & Cost-Efficient", "128,000 tokens", "OpenAI / GitHub Copilot"),
        AIModelSpec("gemini-3.6-flash", "Gemini 3.6 Flash", "Ecosystem Core", "1,000,000 tokens", "Google DeepMind")
    ),
    val selectedModelId: String = "gemini-3.6-flash",
    val mcpConnectors: List<McpConnectorSpec> = listOf(
        McpConnectorSpec("mcp-1", "Gmail & Google Calendar", "Google Workspace", "Connected", 8, "OAuth 2.0"),
        McpConnectorSpec("mcp-2", "Google Drive & Docs", "Google Workspace", "Connected", 12, "OAuth 2.0"),
        McpConnectorSpec("mcp-3", "Microsoft Teams & OneDrive", "Microsoft 365", "Connected", 15, "OAuth 2.0"),
        McpConnectorSpec("mcp-4", "Canva Creative Hub", "Canva / Grok", "Connected", 6, "App Integration"),
        McpConnectorSpec("mcp-5", "Salesforce CRM", "Salesforce", "Active", 10, "OAuth 2.0"),
        McpConnectorSpec("mcp-6", "PHIXO Dodecahedron Hub", "Fastify Local MCP", "Listening :3000", 12, "Custom Token")
    ),
    val chunkInputSample: String = "PHIXOverse Multi-Agent Protocol: Processing massive codebases and NASA Earthdata telemetry through sliding-window chunking. When token contexts approach the 1M limit, agent_manager.py splits the corpus into optimal sub-windows, preserving persistent summarization across conversational turns.",
    val chunkingResult: ChunkingResult? = null,
    val bannerMessage: String? = null,
    val greenJuiceChecked: Boolean = true,
    val scamperStep: String = "Adapt: Incorporate NASA CFD telemetry into local aerodynamic calculations.",
    val pdcaPhase: String = "Act: Stabilizing sliding window token compression across 14 repos."
)

class PhixoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PhixoUiState())
    val uiState: StateFlow<PhixoUiState> = _uiState.asStateFlow()

    init {
        runChunkCalculation(_uiState.value.chunkInputSample)
    }

    fun selectTab(tab: AppTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun selectBeaufort(force: Int) {
        _uiState.update { it.copy(selectedBeaufortForce = force) }
    }

    fun selectModel(modelId: String) {
        _uiState.update { it.copy(selectedModelId = modelId) }
    }

    fun claimDailyReward() {
        val now = SimpleDateFormat("dd MMM yyyy", Locale.US).format(Date())
        val newLog = DiamondLog(
            id = System.currentTimeMillis().toString(),
            date = now,
            action = "Daily Reward (Space Ranger Check-in)",
            delta = 20
        )
        _uiState.update { state ->
            state.copy(
                totalDiamonds = state.totalDiamonds + 20,
                diamondLogs = listOf(newLog) + state.diamondLogs,
                bannerMessage = "¡+20 Diamantes reclamados con éxito! Saldo: ${state.totalDiamonds + 20}"
            )
        }
    }

    fun joinPricePrediction(amount: Int = 10) {
        val now = SimpleDateFormat("dd MMM yyyy", Locale.US).format(Date())
        val isWinner = (1..3).random() == 1
        val delta = if (isWinner) 3 else -amount
        val actionDesc = if (isWinner) "Price Prediction Winner (+3)" else "Joined Price Prediction (-$amount)"

        val newLog = DiamondLog(
            id = System.currentTimeMillis().toString(),
            date = now,
            action = actionDesc,
            delta = delta
        )
        _uiState.update { state ->
            val updated = state.totalDiamonds + delta
            state.copy(
                totalDiamonds = updated,
                diamondLogs = listOf(newLog) + state.diamondLogs,
                bannerMessage = if (isWinner) "🎯 ¡Ganaste la predicción! +3 Diamantes" else "Predicción registrada: -$amount Diamantes"
            )
        }
    }

    fun renewNasaTokens() {
        _uiState.update { state ->
            val renewed = state.nasaTokens.map { token ->
                token.copy(remainingPercent = 1.0f, expiresAt = "2026-10-15 01:09 EDT")
            }
            state.copy(
                nasaTokens = renewed,
                bannerMessage = "🛰️ Credenciales NASA Earthdata renovadas al 100% de vida útil."
            )
        }
    }

    fun updateChunkInput(text: String) {
        _uiState.update { it.copy(chunkInputSample = text) }
        runChunkCalculation(text)
    }

    private fun runChunkCalculation(text: String) {
        viewModelScope.launch {
            val length = text.length
            val estimatedTokens = (length / 3.8).toInt().coerceAtLeast(1)
            val chunkCapacity = 200_000 // 200k tokens per chunk as in agent_maneger.py
            val numChunks = ((estimatedTokens + chunkCapacity - 1) / chunkCapacity).coerceAtLeast(1)
            val summaryTokens = (numChunks * 450).coerceAtMost(3000)

            val chunks = mutableListOf<String>()
            if (text.isNotBlank()) {
                val step = (text.length / numChunks).coerceAtLeast(1)
                for (i in 0 until numChunks) {
                    val start = i * step
                    val end = ((i + 1) * step).coerceAtMost(text.length)
                    chunks.add(text.substring(start, end))
                }
            } else {
                chunks.add("Ready for input stream...")
            }

            _uiState.update {
                it.copy(
                    chunkingResult = ChunkingResult(
                        totalCharacters = length,
                        estimatedTokens = estimatedTokens,
                        totalChunks = numChunks,
                        chunkSizeTokens = chunkCapacity,
                        summaryTokens = summaryTokens,
                        meetsWindowConstraint = estimatedTokens < 1_000_000,
                        chunks = chunks
                    )
                )
            }
        }
    }

    fun toggleGreenJuice() {
        _uiState.update { it.copy(greenJuiceChecked = !it.greenJuiceChecked) }
    }

    fun dismissBanner() {
        _uiState.update { it.copy(bannerMessage = null) }
    }
}
