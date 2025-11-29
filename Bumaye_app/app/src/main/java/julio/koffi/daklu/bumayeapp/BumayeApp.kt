package julio.koffi.daklu.bumayeapp

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import julio.koffi.daklu.bumayeapp.screens.*

// États de navigation
enum class BumayeScreen {
    HOME,
    ADD_CLIENT,
    EDIT_CLIENT,
    CLIENT_DETAILS
}

// Composant principal de l'application
@Composable
fun BumayeApp() {
    var _v_currentScreen by remember { mutableStateOf(BumayeScreen.HOME) }
    var _v_selectedClientId by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        when (_v_currentScreen) {
            BumayeScreen.HOME -> {
                // Affichage de l'écran principal
                HomeScreen(
                    onAddClientClick = {
                        _v_currentScreen = BumayeScreen.ADD_CLIENT
                    },
                    onClientClick = { clientId ->
                        _v_selectedClientId = clientId
                        _v_currentScreen = BumayeScreen.CLIENT_DETAILS
                    },
                    onEditClient = { clientId ->
                        _v_selectedClientId = clientId
                        _v_currentScreen = BumayeScreen.EDIT_CLIENT
                    }
                )
            }
            BumayeScreen.ADD_CLIENT -> {
                AddEditClientScreen(
                    clientId = null,
                    onNavigateBack = {
                        _v_currentScreen = BumayeScreen.HOME
                    }
                )
            }
            BumayeScreen.EDIT_CLIENT -> {
                AddEditClientScreen(
                    clientId = _v_selectedClientId,
                    onNavigateBack = {
                        _v_currentScreen = BumayeScreen.HOME
                    }
                )
            }
            BumayeScreen.CLIENT_DETAILS -> {
                ClientDetailsScreen(
                    clientId = _v_selectedClientId ?: "",
                    onNavigateBack = {
                        _v_currentScreen = BumayeScreen.HOME
                    },
                    onEditClient = { clientId ->
                        _v_selectedClientId = clientId
                        _v_currentScreen = BumayeScreen.EDIT_CLIENT
                    }
                )
            }
        }
    }
}