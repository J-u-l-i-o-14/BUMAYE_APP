package julio.koffi.daklu.bumayeapp.data

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import julio.koffi.daklu.bumayeapp.model.Client
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.builtins.ListSerializer // Important pour désérialiser une liste

// Repository pour les clients
object ClientRepository {
    private val _v_clients = mutableStateListOf<Client>()
    private lateinit var sharedPreferences: SharedPreferences

    private const val PREFS_NAME = "bumaye_app_prefs"
    private const val CLIENTS_KEY = "clients_list"

    // Doit être appelé depuis la classe Application
    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadClients()
    }

    // Obtenir tous les clients
    val _v_allClients: List<Client> get() = _v_clients

    // Ajouter un client
    fun _v_addClient(client: Client) {
        _v_clients.add(client)
        saveClients()
    }

    // Obtenir client par ID
    fun _v_getClientById(id: String): Client? {
        return _v_clients.find { it._v_id == id }
    }

    // Mettre à jour un client
    fun _v_updateClient(client: Client) {
        val _v_index = _v_clients.indexOfFirst { it._v_id == client._v_id }
        if (_v_index != -1) {
            _v_clients[_v_index] = client
            saveClients()
        }
    }

    // Supprimer un client
    fun _v_deleteClient(id: String) {
        val originalSize = _v_clients.size
        _v_clients.removeAll { it._v_id == id }
        if (_v_clients.size < originalSize) { // Vérifie si un élément a été réellement supprimé
            saveClients()
        }
    }

    // Rechercher par nom ou téléphone
    fun _v_searchClients(query: String): List<Client> {
        if (query.isBlank()) return _v_clients

        return _v_clients.filter {
            it._v_nom.contains(query, ignoreCase = true) ||
                    it._v_prenoms.contains(query, ignoreCase = true) ||
                    it._v_telephone.contains(query, ignoreCase = true)
        }
    }

    private fun saveClients() {
        if (::sharedPreferences.isInitialized) { // S'assurer que SharedPreferences est initialisé
            val clientsJson = Json.encodeToString(_v_clients.toList()) // Convertir en List pour la sérialisation
            sharedPreferences.edit().putString(CLIENTS_KEY, clientsJson).apply()
        }
    }

    private fun loadClients() {
        if (::sharedPreferences.isInitialized) {
            val clientsJson = sharedPreferences.getString(CLIENTS_KEY, null)
            if (!clientsJson.isNullOrEmpty()) {
                try {
                    // Pour désérialiser une liste, on a besoin du serializer de Client
                    val clientList = Json.decodeFromString(ListSerializer(Client.serializer()), clientsJson)
                    _v_clients.clear()
                    _v_clients.addAll(clientList)
                } catch (e: Exception) {
                    // Gérer l'erreur de désérialisation, par ex. logger ou commencer avec une liste vide
                    e.printStackTrace()
                     _v_clients.clear() // S'assurer que la liste est vide en cas d'erreur
                }
            } else {
                 _v_clients.clear() // S'assurer que la liste est vide si rien n'est sauvegardé
            }
        }
    }
}