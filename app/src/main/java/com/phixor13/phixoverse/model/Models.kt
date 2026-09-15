package com.phixor13.phixoverse.model

data class ProfileIdentity(
    val name: String = "Josue Eduardo Illescas Granillo",
    val handle: String = "@PhixoR13",
    val title: String = "Space Ranger · CEO FIXO MX12 · FoP 638",
    val location: String = "Ciudad Juárez, Chihuahua, MX",
    val coordinates: String = "31°37'27\" N, 106°23'11\" W",
    val organization: String = "FIXO MX12 (#8943)",
    val gamingTag: String = "PhixoR13 / FoP 638 (Xbox Gen9)",
    val bio: String = "Fullstack Developer | Creative Technologist | Space Ranger at SpaceY. Building high-leverage decentralized systems, AI agents, and aerodynamic research."
)

data class DiamondLog(
    val id: String,
    val date: String,
    val action: String,
    val delta: Int
)

data class TokenAllocation(
    val label: String,
    val percentage: Float,
    val wallet: String,
    val description: String
)

data class NasaTokenInfo(
    val id: String,
    val center: String,
    val endpoint: String,
    val expiresAt: String,
    val remainingPercent: Float,
    val active: Boolean
)

data class BeaufortLevel(
    val force: Int,
    val title: String,
    val knots: String,
    val kmh: String,
    val waveHeight: String,
    val observation: String
)

data class AIModelSpec(
    val id: String,
    val name: String,
    val category: String, // "Fast & Cost-Efficient" or "Versatile & Highly Intelligent"
    val contextWindow: String,
    val provider: String,
    val isDefault: Boolean = false
)

data class McpConnectorSpec(
    val id: String,
    val name: String,
    val provider: String,
    val status: String,
    val toolCount: Int,
    val authType: String
)

data class ChunkingResult(
    val totalCharacters: Int,
    val estimatedTokens: Int,
    val totalChunks: Int,
    val chunkSizeTokens: Int,
    val summaryTokens: Int,
    val meetsWindowConstraint: Boolean,
    val chunks: List<String>
)

enum class AppTab(val title: String) {
    DASHBOARD("Space Ranger"),
    CRYPTO_NASA("Crypto & NASA"),
    ORCHESTRATOR("AI & MCP"),
    OPTIMIZED_LIVING("Indulgencia")
}
