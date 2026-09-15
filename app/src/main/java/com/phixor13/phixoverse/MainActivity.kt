package com.phixor13.phixoverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.phixor13.phixoverse.model.AppTab
import com.phixor13.phixoverse.ui.screens.AgentOrchestratorScreen
import com.phixor13.phixoverse.ui.screens.CryptoNasaScreen
import com.phixor13.phixoverse.ui.screens.DashboardScreen
import com.phixor13.phixoverse.ui.screens.OptimizedLivingScreen
import com.phixor13.phixoverse.ui.theme.DeepSpaceDark
import com.phixor13.phixoverse.ui.theme.NeonCyan
import com.phixor13.phixoverse.ui.theme.NeonGreen
import com.phixor13.phixoverse.ui.theme.NeonPink
import com.phixor13.phixoverse.ui.theme.NeonPurple
import com.phixor13.phixoverse.ui.theme.PHIXOverseTheme
import com.phixor13.phixoverse.ui.theme.SurfaceDark
import com.phixor13.phixoverse.ui.theme.SurfaceElevated
import com.phixor13.phixoverse.ui.theme.TextMuted
import com.phixor13.phixoverse.ui.theme.TextPrimary
import com.phixor13.phixoverse.ui.theme.TextSecondary
import com.phixor13.phixoverse.viewmodel.PhixoViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: PhixoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PHIXOverseTheme {
                MainAppContent(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent(viewModel: PhixoViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(listOf(NeonPurple, DeepSpaceDark))
                                )
                                .border(1.5.dp, NeonCyan, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.RocketLaunch,
                                contentDescription = null,
                                tint = NeonCyan,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "PHIXOverse",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold
                            ),
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(NeonPurple.copy(alpha = 0.25f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "X12 DODECAEDRO",
                                style = MaterialTheme.typography.labelSmall,
                                color = NeonCyan
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceDark,
                    titleContentColor = TextPrimary
                ),
                modifier = Modifier.testTag("main_top_bar")
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = SurfaceDark,
                contentColor = TextPrimary,
                modifier = Modifier.testTag("bottom_nav_bar")
            ) {
                NavigationBarItem(
                    selected = uiState.currentTab == AppTab.DASHBOARD,
                    onClick = { viewModel.selectTab(AppTab.DASHBOARD) },
                    icon = {
                        Icon(Icons.Default.RocketLaunch, contentDescription = "Space Ranger")
                    },
                    label = { Text("Ranger", style = MaterialTheme.typography.labelSmall) },
                    colors = navBarItemColors(),
                    modifier = Modifier.testTag("nav_tab_dashboard")
                )
                NavigationBarItem(
                    selected = uiState.currentTab == AppTab.CRYPTO_NASA,
                    onClick = { viewModel.selectTab(AppTab.CRYPTO_NASA) },
                    icon = {
                        Icon(Icons.Default.CurrencyBitcoin, contentDescription = "Crypto & NASA")
                    },
                    label = { Text("Crypto/NASA", style = MaterialTheme.typography.labelSmall) },
                    colors = navBarItemColors(),
                    modifier = Modifier.testTag("nav_tab_crypto_nasa")
                )
                NavigationBarItem(
                    selected = uiState.currentTab == AppTab.ORCHESTRATOR,
                    onClick = { viewModel.selectTab(AppTab.ORCHESTRATOR) },
                    icon = {
                        Icon(Icons.Default.Hub, contentDescription = "AI & MCP")
                    },
                    label = { Text("AI & MCP", style = MaterialTheme.typography.labelSmall) },
                    colors = navBarItemColors(),
                    modifier = Modifier.testTag("nav_tab_orchestrator")
                )
                NavigationBarItem(
                    selected = uiState.currentTab == AppTab.OPTIMIZED_LIVING,
                    onClick = { viewModel.selectTab(AppTab.OPTIMIZED_LIVING) },
                    icon = {
                        Icon(Icons.Default.Eco, contentDescription = "Indulgencia")
                    },
                    label = { Text("Indulgencia", style = MaterialTheme.typography.labelSmall) },
                    colors = navBarItemColors(),
                    modifier = Modifier.testTag("nav_tab_indulgencia")
                )
            }
        },
        containerColor = DeepSpaceDark
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState.currentTab) {
                AppTab.DASHBOARD -> DashboardScreen(
                    uiState = uiState,
                    onClaimReward = { viewModel.claimDailyReward() },
                    onJoinPrediction = { viewModel.joinPricePrediction(10) },
                    onDismissBanner = { viewModel.dismissBanner() }
                )
                AppTab.CRYPTO_NASA -> CryptoNasaScreen(
                    uiState = uiState,
                    onSelectBeaufort = { force -> viewModel.selectBeaufort(force) },
                    onRenewNasaTokens = { viewModel.renewNasaTokens() }
                )
                AppTab.ORCHESTRATOR -> AgentOrchestratorScreen(
                    uiState = uiState,
                    onSelectModel = { id -> viewModel.selectModel(id) },
                    onUpdateChunkInput = { text -> viewModel.updateChunkInput(text) }
                )
                AppTab.OPTIMIZED_LIVING -> OptimizedLivingScreen(
                    uiState = uiState,
                    onToggleGreenJuice = { viewModel.toggleGreenJuice() }
                )
            }
        }
    }
}

@Composable
private fun navBarItemColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = NeonCyan,
    selectedTextColor = NeonCyan,
    indicatorColor = SurfaceElevated,
    unselectedIconColor = TextSecondary,
    unselectedTextColor = TextMuted
)
