package julio.koffi.daklu.bumayeapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

// Utilitaires de validation
object ValidationUtils {

    //Valider numéro de téléphone
    fun _v_isValidPhoneNumber(phone: String): Boolean {
        return phone.matches(Regex("^[0-9]{8,12}$"))
    }

    //Valider nom
    fun _v_isValidName(name: String): Boolean {
        return name.trim().isNotEmpty() && name.length >= 2
    }

    // Valider format de date
    fun _v_isValidDateFormat(date: String): Boolean {
        return try {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(date)
            true
        } catch (e: ParseException) {
            false
        }
    }

    //Comparer les dates
    fun _v_isDateAfterOrEqual(startDate: String, endDate: String): Boolean {
        return try {
            val _v_formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val _v_start = _v_formatter.parse(startDate)
            val _v_end = _v_formatter.parse(endDate)
            _v_end != null && _v_start != null && !_v_end.before(_v_start)
        } catch (e: ParseException) {
            false
        }
    }

    // Valider montant
    fun _v_isValidAmount(amount: String): Boolean {
        return try {
            val _v_value = amount.toDouble()
            _v_value >= 0
        } catch (e: NumberFormatException) {
            false
        }
    }

    // Obtenir messages d'erreur
    object ErrorMessages {
        const val _v_REQUIRED_FIELD = "Ce champ est obligatoire"
        const val _v_INVALID_PHONE = "Numéro de téléphone invalide (8-12 chiffres)"
        const val _v_INVALID_DATE = "Format de date invalide (JJ/MM/AAAA)"
        const val _v_INVALID_DATE_ORDER = "La date de livraison doit être >= à la date de commande"
        const val _v_INVALID_AMOUNT = "Montant invalide"
        const val _v_INSUFFICIENT_ADVANCE = "L'avance ne peut pas être supérieure au total"
        // Messages d'erreur en chinois
        const val _v_CHINESE_ERROR = "无效的输入数据"
    }
}