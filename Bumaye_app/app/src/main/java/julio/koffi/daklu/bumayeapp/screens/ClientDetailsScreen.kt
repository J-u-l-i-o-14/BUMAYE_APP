package julio.koffi.daklu.bumayeapp.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import julio.koffi.daklu.bumayeapp.data.ClientRepository


// Écran de détails du client
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientDetailsScreen(
    clientId: String,
    onNavigateBack: () -> Unit,
    onEditClient: (String) -> Unit
) {
    val _v_client = ClientRepository._v_getClientById(clientId)

    if (_v_client == null) {
        //Client non trouvé
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Client non trouvé",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error
                )
                Text(
                    text = "Informations client non trouvées", // Informations client non trouvées
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Button(onClick = onNavigateBack) {
                    Text("Retour")
                }
            }
        }
        return
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // TopAppBar
        TopAppBar(
            title = { Text("Détails Client") },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                }
            },
            actions = {
                IconButton(onClick = { onEditClient(clientId) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Modifier")
                }
            }
        )

        // Contenu défilant
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Carte titre client
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "${_v_client._v_nom} ${_v_client._v_prenoms}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "Téléphone: ${_v_client._v_telephone}", // Téléphone
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Date de commande",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = _v_client._v_date_commande,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "la date de livraison",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = _v_client._v_date_livraison,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                }
            }

            // Carte statut paiement
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (_v_client._v_reste > 0)
                        MaterialTheme.colorScheme.errorContainer
                    else MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = " État des Paiements",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Somme totale:", style = MaterialTheme.typography.bodyMedium)
                        Text(
                            "${_v_client._v_somme_totale}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Avance payée:", style = MaterialTheme.typography.bodyMedium)
                        Text(
                            "${_v_client._v_avance}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Divider()

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            " Reste à payer:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "${_v_client._v_reste}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = if (_v_client._v_reste > 0)
                                MaterialTheme.colorScheme.error
                            else MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            // Données de mesures corporelles
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Mesures Corporelles",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    //Mesures haut du corps
                    MeasurementSection(
                        title = "Haut du corps",
                        measurements = listOf(
                            "Épaule" to _v_client._v_epaule,
                            "Poitrine" to _v_client._v_poitrine,
                            "Longueur taille" to _v_client._v_longueur_taille,
                            "Tour ventrale" to _v_client._v_tour_ventrale,
                            "Longueur corsage" to _v_client._v_longueur_corsage
                        )
                    )

                    Divider()

                    //Mesures bas du corps
                    MeasurementSection(
                        title = "Bas du corps",
                        measurements = listOf(
                            "Hanche" to _v_client._v_hanche,
                            "Ceinture" to _v_client._v_ceinture,
                            "Tour de cuisse" to _v_client._v_tour_cuisse,
                            "Longueur de genoux" to _v_client._v_longueur_genoux,
                            "Tour de genoux" to _v_client._v_tour_genoux,
                            "Bas" to _v_client._v_bas
                        )
                    )

                    Divider()

                    //Mesures vêtements
                    MeasurementSection(
                        title = "Mesures Vêtements",
                        measurements = listOf(
                            "Longueur jupe" to _v_client._v_longueur_jupe,
                            "Longueur robe" to _v_client._v_longueur_robe,
                            "Longueur robe courte" to _v_client._v_longueur_robe_courte,
                            "Longueur manche" to _v_client._v_longueur_manche,
                            "Taille manche" to _v_client._v_taille_manche,
                            "Longueur pantalon" to _v_client._v_longueur_pantalon
                        )
                    )

                    // Autres mesures
                    if (_v_client._v_autres_mesures.isNotEmpty()) {
                        Divider()
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Autres Mesures",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            ) {
                                Text(
                                    text = _v_client._v_autres_mesures,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            //Bouton Modifier
            Button(
                onClick = { onEditClient(clientId) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Modifier les Informations",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

//Composant section de mesures
@Composable
fun MeasurementSection(
    title: String,
    measurements: List<Pair<String, Double>>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )

        measurements.chunked(2).forEach { rowMeasurements ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowMeasurements.forEach { (label, value) ->
                    MeasurementItem(
                        label = label,
                        value = value,
                        modifier = Modifier.weight(1f)
                    )
                }
                //Remplir l'espace vide si une seule mesure
                if (rowMeasurements.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

//Composant élément de mesure
@Composable
fun MeasurementItem(
    label: String,
    value: Double,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (value > 0)
                MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = if (value > 0) "$value cm" else "Non defini", // Non défini
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (value > 0) FontWeight.Medium else FontWeight.Normal,
                color = if (value > 0)
                    MaterialTheme.colorScheme.onSecondaryContainer
                else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}