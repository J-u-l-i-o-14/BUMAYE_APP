package julio.koffi.daklu.bumayeapp.model

import kotlinx.serialization.Serializable // Importez ceci
import java.util.*

@Serializable // Ajoutez cette annotation
data class Client(
    val _v_id: String = UUID.randomUUID().toString(),

    // Informations personnelles
    val _v_nom: String = "",
    val _v_prenoms: String = "",
    val _v_telephone: String = "",

    // Données de mesures
    val _v_epaule: Double = 0.0,
    val _v_poitrine: Double = 0.0,
    val _v_longueur_taille: Double = 0.0,
    val _v_tour_ventrale: Double = 0.0,
    val _v_hanche: Double = 0.0,
    val _v_longueur_corsage: Double = 0.0,
    val _v_ceinture: Double = 0.0,
    val _v_longueur_jupe: Double = 0.0,
    val _v_longueur_robe: Double = 0.0,
    val _v_longueur_manche: Double = 0.0,
    val _v_taille_manche: Double = 0.0,
    val _v_longueur_pantalon: Double = 0.0,
    val _v_longueur_robe_courte: Double = 0.0,
    val _v_tour_cuisse: Double = 0.0,
    val _v_longueur_genoux: Double = 0.0,
    val _v_tour_genoux: Double = 0.0,
    val _v_bas: Double = 0.0,
    val _v_autres_mesures: String = "",

    // Suivi de commande
    val _v_date_commande: String = "",
    val _v_date_livraison: String = "",

    // Paiement
    val _v_somme_totale: Double = 0.0,
    val _v_avance: Double = 0.0
) {
    // Le calcul du reste n'a pas besoin d'être sérialisé car il est dérivé.
    // kotlinx.serialization ignorera par défaut les propriétés avec un getter personnalisé
    // et pas de backing field, ce qui est correct ici.
    val _v_reste: Double
        get() = _v_somme_totale - _v_avance
}