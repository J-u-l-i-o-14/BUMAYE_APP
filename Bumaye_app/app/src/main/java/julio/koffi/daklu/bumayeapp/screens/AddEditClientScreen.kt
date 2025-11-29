package julio.koffi.daklu.bumayeapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import julio.koffi.daklu.bumayeapp.data.ClientRepository
import julio.koffi.daklu.bumayeapp.model.Client
import julio.koffi.daklu.bumayeapp.utils.ValidationUtils
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Import specific pour la version auto-mirror


// Écran d'ajout/modification de client
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditClientScreen(
    clientId: String?,
    onNavigateBack: () -> Unit
) {
    // Obtenir les données client existantes
    val existingClient = clientId?.let { ClientRepository._v_getClientById(it) }
    val isEditing = existingClient != null

    // État du formulaire
    var nom by remember { mutableStateOf(existingClient?._v_nom ?: "") }
    var prenoms by remember { mutableStateOf(existingClient?._v_prenoms ?: "") }
    var telephone by remember { mutableStateOf(existingClient?._v_telephone ?: "") }

    // Champs de mesures
    var epaule by remember { mutableStateOf(existingClient?._v_epaule?.toString() ?: "") }
    var poitrine by remember { mutableStateOf(existingClient?._v_poitrine?.toString() ?: "") }
    var longueurTail by remember { mutableStateOf(existingClient?._v_longueur_taille?.toString() ?: "") }
    var tourVentrale by remember { mutableStateOf(existingClient?._v_tour_ventrale?.toString() ?: "") }
    var hanche by remember { mutableStateOf(existingClient?._v_hanche?.toString() ?: "") }
    var longueurCorsage by remember { mutableStateOf(existingClient?._v_longueur_corsage?.toString() ?: "") }
    var ceinture by remember { mutableStateOf(existingClient?._v_ceinture?.toString() ?: "") }
    var longueurJupe by remember { mutableStateOf(existingClient?._v_longueur_jupe?.toString() ?: "") }
    var longueurRobe by remember { mutableStateOf(existingClient?._v_longueur_robe?.toString() ?: "") }
    var longueurManche by remember { mutableStateOf(existingClient?._v_longueur_manche?.toString() ?: "") }
    var tailleManche by remember { mutableStateOf(existingClient?._v_taille_manche?.toString() ?: "") }
    var longueurPantalon by remember { mutableStateOf(existingClient?._v_longueur_pantalon?.toString() ?: "") }
    var longueurRobeCourte by remember { mutableStateOf(existingClient?._v_longueur_robe_courte?.toString() ?: "") }
    var tourCuisse by remember { mutableStateOf(existingClient?._v_tour_cuisse?.toString() ?: "") }
    var longueurGenoux by remember { mutableStateOf(existingClient?._v_longueur_genoux?.toString() ?: "") }
    var tourGenoux by remember { mutableStateOf(existingClient?._v_tour_genoux?.toString() ?: "") }
    var bas by remember { mutableStateOf(existingClient?._v_bas?.toString() ?: "") }
    var autresMesures by remember { mutableStateOf(existingClient?._v_autres_mesures ?: "") }

    // Commande et paiement
    var dateCommande by remember { mutableStateOf(existingClient?._v_date_commande ?: "") }
    var dateLivraison by remember { mutableStateOf(existingClient?._v_date_livraison ?: "") }
    var sommeTotale by remember { mutableStateOf(existingClient?._v_somme_totale?.toString() ?: "") }
    var avance by remember { mutableStateOf(existingClient?._v_avance?.toString() ?: "") }

    // États d'erreur
    var errors by remember { mutableStateOf(mapOf<String, String>()) }

    // Validation du formulaire
    fun validateForm(): Boolean {
        val newErrors = mutableMapOf<String, String>()

        if (!ValidationUtils._v_isValidName(nom)) {
            newErrors["nom"] = ValidationUtils.ErrorMessages._v_REQUIRED_FIELD
        }
        if (!ValidationUtils._v_isValidName(prenoms)) {
            newErrors["prenoms"] = ValidationUtils.ErrorMessages._v_REQUIRED_FIELD
        }
        if (!ValidationUtils._v_isValidPhoneNumber(telephone)) {
            newErrors["telephone"] = ValidationUtils.ErrorMessages._v_INVALID_PHONE
        }
        if (dateCommande.isBlank()) {
            newErrors["date_commande"] = ValidationUtils.ErrorMessages._v_REQUIRED_FIELD
        } else if (!ValidationUtils._v_isValidDateFormat(dateCommande)) {
            newErrors["date_commande"] = ValidationUtils.ErrorMessages._v_INVALID_DATE
        }
        if (dateLivraison.isBlank()) {
            newErrors["date_livraison"] = ValidationUtils.ErrorMessages._v_REQUIRED_FIELD
        } else if (!ValidationUtils._v_isValidDateFormat(dateLivraison)) {
            newErrors["date_livraison"] = ValidationUtils.ErrorMessages._v_INVALID_DATE
        } else if (!ValidationUtils._v_isDateAfterOrEqual(dateCommande, dateLivraison)) {
            newErrors["date_livraison"] = ValidationUtils.ErrorMessages._v_INVALID_DATE_ORDER
        }
        if (sommeTotale.isBlank()) {
            newErrors["somme_totale"] = ValidationUtils.ErrorMessages._v_REQUIRED_FIELD
        } else if (!ValidationUtils._v_isValidAmount(sommeTotale)) {
            newErrors["somme_totale"] = ValidationUtils.ErrorMessages._v_INVALID_AMOUNT
        }
        if (avance.isBlank()) {
            newErrors["avance"] = ValidationUtils.ErrorMessages._v_REQUIRED_FIELD
        } else if (!ValidationUtils._v_isValidAmount(avance)) {
            newErrors["avance"] = ValidationUtils.ErrorMessages._v_INVALID_AMOUNT
        } else if (sommeTotale.isNotBlank() && avance.toDoubleOrNull() != null &&
            sommeTotale.toDoubleOrNull() != null &&
            avance.toDouble() > sommeTotale.toDouble()) {
            newErrors["avance"] = ValidationUtils.ErrorMessages._v_INSUFFICIENT_ADVANCE
        }

        errors = newErrors
        return newErrors.isEmpty()
    }

    // Sauvegarder le client
    fun saveClient() {
        if (validateForm()) {
            val client = Client(
                _v_id = existingClient?._v_id ?: "",
                _v_nom = nom.trim(),
                _v_prenoms = prenoms.trim(),
                _v_telephone = telephone,
                _v_epaule = epaule.toDoubleOrNull() ?: 0.0,
                _v_poitrine = poitrine.toDoubleOrNull() ?: 0.0,
                _v_longueur_taille = longueurTail.toDoubleOrNull() ?: 0.0,
                _v_tour_ventrale = tourVentrale.toDoubleOrNull() ?: 0.0,
                _v_hanche = hanche.toDoubleOrNull() ?: 0.0,
                _v_longueur_corsage = longueurCorsage.toDoubleOrNull() ?: 0.0,
                _v_ceinture = ceinture.toDoubleOrNull() ?: 0.0,
                _v_longueur_jupe = longueurJupe.toDoubleOrNull() ?: 0.0,
                _v_longueur_robe = longueurRobe.toDoubleOrNull() ?: 0.0,
                _v_longueur_manche = longueurManche.toDoubleOrNull() ?: 0.0,
                _v_taille_manche = tailleManche.toDoubleOrNull() ?: 0.0,
                _v_longueur_pantalon = longueurPantalon.toDoubleOrNull() ?: 0.0,
                _v_longueur_robe_courte = longueurRobeCourte.toDoubleOrNull() ?: 0.0,
                _v_tour_cuisse = tourCuisse.toDoubleOrNull() ?: 0.0,
                _v_longueur_genoux = longueurGenoux.toDoubleOrNull() ?: 0.0,
                _v_tour_genoux = tourGenoux.toDoubleOrNull() ?: 0.0,
                _v_bas = bas.toDoubleOrNull() ?: 0.0,
                _v_autres_mesures = autresMesures,
                _v_date_commande = dateCommande,
                _v_date_livraison = dateLivraison,
                _v_somme_totale = sommeTotale.toDoubleOrNull() ?: 0.0,
                _v_avance = avance.toDoubleOrNull() ?: 0.0
            )

            if (isEditing) {
                ClientRepository._v_updateClient(client)
            } else {
                ClientRepository._v_addClient(client)
            }
            onNavigateBack()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // TopAppBar
        TopAppBar(
            title = {
                Text(if (isEditing) "Modifier client" else "Ajouter client") // Modifier/Ajouter client
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                }
            },
            actions = {
                IconButton(onClick = { saveClient() }) {
                    Icon(Icons.Default.Done, contentDescription = "Sauvegarder")
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
            // Section informations personnelles
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Informations Personnelles",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedTextField(
                        value = nom,
                        onValueChange = { nom = it },
                        label = { Text("Nom *") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errors.containsKey("nom"),
                        supportingText = errors["nom"]?.let { { Text(it) } }
                    )

                    OutlinedTextField(
                        value = prenoms,
                        onValueChange = { prenoms = it },
                        label = { Text("Prénoms *") },
                        modifier = Modifier.fillMaxWidth(),
                        isError = errors.containsKey("prenoms"),
                        supportingText = errors["prenoms"]?.let { { Text(it) } }
                    )

                    OutlinedTextField(
                        value = telephone,
                        onValueChange = { telephone = it },
                        label = { Text("Numéro de téléphone *") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        isError = errors.containsKey("telephone"),
                        supportingText = errors["telephone"]?.let { { Text(it) } }
                    )
                }
            }

            // Section mesures
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Mesures",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = epaule,
                            onValueChange = { epaule = it },
                            label = { Text("Épaule") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        OutlinedTextField(
                            value = poitrine,
                            onValueChange = { poitrine = it },
                            label = { Text("Poitrine") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = longueurTail,
                            onValueChange = { longueurTail = it },
                            label = { Text("Longueur taille") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        OutlinedTextField(
                            value = tourVentrale,
                            onValueChange = { tourVentrale = it },
                            label = { Text("Tour ventrale") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = hanche,
                            onValueChange = { hanche = it },
                            label = { Text("Hanche") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        OutlinedTextField(
                            value = longueurCorsage,
                            onValueChange = { longueurCorsage = it },
                            label = { Text("Longueur corsage") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = ceinture,
                            onValueChange = { ceinture = it },
                            label = { Text("Ceinture") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        OutlinedTextField(
                            value = longueurJupe,
                            onValueChange = { longueurJupe = it },
                            label = { Text("Longueur jupe") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = longueurRobe,
                            onValueChange = { longueurRobe = it },
                            label = { Text("Longueur robe") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        OutlinedTextField(
                            value = longueurManche,
                            onValueChange = { longueurManche = it },
                            label = { Text("Longueur manche") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = tailleManche,
                            onValueChange = { tailleManche = it },
                            label = { Text("Taille manche") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        OutlinedTextField(
                            value = longueurPantalon,
                            onValueChange = { longueurPantalon = it },
                            label = { Text("Longueur pantalon") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = longueurRobeCourte,
                            onValueChange = { longueurRobeCourte = it },
                            label = { Text("Longueur robe courte") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        OutlinedTextField(
                            value = tourCuisse,
                            onValueChange = { tourCuisse = it },
                            label = { Text("Tour de cuisse") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = longueurGenoux,
                            onValueChange = { longueurGenoux = it },
                            label = { Text("Longueur de genoux") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                        OutlinedTextField(
                            value = tourGenoux,
                            onValueChange = { tourGenoux = it },
                            label = { Text("Tour de genoux") },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                        )
                    }

                    OutlinedTextField(
                        value = bas,
                        onValueChange = { bas = it },
                        label = { Text("Bas") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )

                    OutlinedTextField(
                        value = autresMesures,
                        onValueChange = { autresMesures = it },
                        label = { Text("Autres mesures") },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 3
                    )
                }
            }

            // Section suivi de commande
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Suivi de Commande",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedTextField(
                        value = dateCommande,
                        onValueChange = { dateCommande = it },
                        label = { Text("Date de commande * (JJ/MM/AAAA)") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("01/01/2024") },
                        isError = errors.containsKey("date_commande"),
                        supportingText = errors["date_commande"]?.let { { Text(it) } }
                    )

                    OutlinedTextField(
                        value = dateLivraison,
                        onValueChange = { dateLivraison = it },
                        label = { Text("Date de livraison * (JJ/MM/AAAA)") },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("15/01/2024") },
                        isError = errors.containsKey("date_livraison"),
                        supportingText = errors["date_livraison"]?.let { { Text(it) } }
                    )
                }
            }

            // Section paiement
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Paiement",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    OutlinedTextField(
                        value = sommeTotale,
                        onValueChange = { sommeTotale = it },
                        label = { Text("Somme totale *") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        isError = errors.containsKey("somme_totale"),
                        supportingText = errors["somme_totale"]?.let { { Text(it) } }
                    )

                    OutlinedTextField(
                        value = avance,
                        onValueChange = { avance = it },
                        label = { Text("Avance *") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        isError = errors.containsKey("avance"),
                        supportingText = errors["avance"]?.let { { Text(it) } }
                    )

                    //Affichage du reste calculé automatiquement
                    val reste = (sommeTotale.toDoubleOrNull() ?: 0.0) - (avance.toDoubleOrNull() ?: 0.0)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (reste > 0) MaterialTheme.colorScheme.errorContainer
                            else MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Reste à payer:",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "$reste",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = if (reste > 0) MaterialTheme.colorScheme.error
                                else MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bouton Sauvegarder
            Button(
                onClick = { saveClient() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                        text = if (isEditing) "Mettre à jour les informations client " else "Enregistrer le client",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}