# Bumaye App - Application de Gestion des Mesures Clients

## 📱 Description du Projet

L'application **Bumaye App** est une solution mobile développée pour la maison de haute couture "Maison BUMAYE" afin de faciliter la gestion des mesures de leurs clients. Cette application Android permet la prise de mesures, l'enregistrement, la consultation, ainsi que la modification et la suppression des fiches clients.

## 🎯 Fonctionnalités Principales

- ✅ **Ajouter un nouveau client** avec toutes ses mesures corporelles
- ✅ **Afficher la liste des clients** enregistrés avec informations essentielles
- ✅ **Consulter les détails complets** d'un client sélectionné
- ✅ **Modifier les informations** d'un client existant
- ✅ **Supprimer un client** de la liste (avec confirmation)
- ✅ **Recherche par nom ou numéro** de téléphone
- ✅ **Validation des données** en temps réel
- ✅ **Calcul automatique** du reste à payer
- ✅ **Interface multilingue** (Français/Chinois)

# Bumaye App - Application de Gestion des Mesures Clients (100% Jetpack Compose)

## 📱 Description du Projet

L'application **Bumaye App** est une solution mobile développée pour la maison de haute couture "Maison BUMAYE" afin de faciliter la gestion des mesures de leurs clients. Cette application Android utilise **exclusivement Jetpack Compose** - aucun XML n'est utilisé pour l'interface utilisateur.

## 🎯 Fonctionnalités Principales

- ✅ **Ajouter un nouveau client** avec toutes ses mesures corporelles
- ✅ **Afficher la liste des clients** enregistrés avec informations essentielles
- ✅ **Consulter les détails complets** d'un client sélectionné
- ✅ **Modifier les informations** d'un client existant
- ✅ **Supprimer un client** de la liste (avec confirmation)
- ✅ **Recherche par nom ou numéro** de téléphone
- ✅ **Validation des données** en temps réel
- ✅ **Calcul automatique** du reste à payer
- ✅ **Interface multilingue** (Français/Chinois)
- ✅ **Design 100% Jetpack Compose** - Aucun XML utilisé

## 🚀 Technologies Modernes Utilisées

### Stack Technique 100% Compose
- **Kotlin** - Langage de programmation principal
- **Jetpack Compose** - Framework UI moderne (AUCUN XML)
- **Material Design 3** - Design system dans Compose
- **Navigation Compose** - Navigation 100% déclarative
- **State Management** - États réactifs avec `remember`, `mutableStateOf`
- **Compose Runtime** - Gestion d'état réactive

### Avantages de l'Approche 100% Compose
- **Interface déclarative** - Code plus lisible et maintenable
- **Pas de XML** - Tout l'UI défini en Kotlin
- **Réactivité native** - États automatiquement synchronisés
- **Theming moderne** - Material Design 3 intégré
- **Performance optimisée** - Recomposition intelligente
- **Type Safety** - Vérification des types à la compilation

## 📋 Champs de Saisie

### Informations Personnelles
- **Nom et prénoms** *(obligatoire)*
- **Numéro de téléphone** *(obligatoire, 8-12 chiffres)*

### Mesures Corporelles (17 mesures)
- Épaule, Poitrine, Longueur taille, Tour ventrale
- Hanche, Longueur corsage, Ceinture, Longueur jupe
- Longueur robe, Longueur manche, Taille de manche
- Longueur pantalon, Longueur robe courte, Tour de cuisse
- Longueur de genoux, Tour de genoux, Bas
- **Autres mesures** *(champ libre)*

### Suivi de Commande
- **Date de commande** *(obligatoire, format JJ/MM/AAAA)*
- **Date de livraison** *(obligatoire, doit être ≥ date de commande)*

### Informations de Paiement
- **Somme totale** *(obligatoire, montant positif)*
- **Avance** *(obligatoire, montant positif)*
- **Reste** *(calculé automatiquement)*

## 🔒 Validations Implémentées (100% Compose)

1. **Nom et prénoms** : Non vide, minimum 2 caractères
2. **Numéro de téléphone** : Format numérique 8-12 chiffres
3. **Dates** : Format valide et cohérence (livraison ≥ commande)
4. **Montants** : Valeurs numériques positives
5. **Calcul automatique** : Reste = Somme totale - Avance
6. **Validation en temps réel** avec `remember` et états dérivés

## 🎨 Design 100% Jetpack Compose

### Interface Utilisateur Moderne
- **Material Design 3** implémenté entièrement en Compose
- **ElevatedCards** avec `RoundedCornerShape` pour un look moderne
- **Couleurs personnalisées** définies programmatiquement
- **Typography** définie en Compose (pas de XML)
- **États visuels** avec `Color()` et modifications dynamiques
- **Animations implicites** avec les transitions Compose

### Expérience Utilisateur Avancée
- **Navigation fluide** avec `NavHost` et `composable`
- **Feedback visuel immédiat** avec états réactifs
- **Messages d'erreur contextuels** affichés dynamiquement
- **Recherche instantanée** avec `derivedStateOf`
- **Confirmations modernes** avec `AlertDialog` stylisé
- **Loading states** et gestion d'erreurs élégante

### Responsive Design Compose
- **Formulaires adaptatifs** avec `Modifier.weight()`
- **Scrolling vertical** avec `LazyColumn` et `verticalScroll()`
- **Disposition flexible** avec `Row`, `Column`, `Box`
- **Espacement cohérent** avec `Arrangement.spacedBy()`

## 🌍 Internationalisation Compose

L'application intègre du **texte bilingue** directement dans les composants :
- Labels des champs avec `Text()`
- Messages de validation dynamiques
- Titres et descriptions en `TopAppBar`
- Messages de confirmation dans les `AlertDialog`

## 📁 Structure du Projet 100% Compose

```
julio.Koffi.daklu.bumaye_app/
├── model/
│   └── Client.kt                 # Modèle de données
├── repository/
│   └── ClientRepository.kt       # Gestion mémoire avec MutableStateList
├── ui/
│   ├── components/              # Composants réutilisables
│   │   ├── ClientCard.kt        # @Composable Card client
│   │   └── ClientForm.kt        # @Composable Formulaire
│   ├── navigation/
│   │   └── BumayeNavigation.kt  # NavHost 100% Compose
│   ├── screens/                 # Écrans @Composable
│   │   ├── AccueilScreen.kt     # @Composable Liste clients
│   │   ├── AjouterClientScreen.kt # @Composable Ajout
│   │   ├── DetailsClientScreen.kt # @Composable Détails
│   │   └── ModifierClientScreen.kt # @Composable Modification
│   └── theme/
│       └── Theme.kt             # MaterialTheme 100% Compose
├── utils/
│   └── ValidationUtils.kt       # Validation logique métier
└── MainActivity.kt              # ComponentActivity avec setContent
```

## 🔧 Composants Compose Utilisés

### Core Compose
- **`@Composable`** - Toutes les fonctions UI
- **`remember { mutableStateOf() }`** - Gestion d'état local
- **`derivedStateOf { }`** - États calculés
- **`LazyColumn`** - Listes performantes
- **`Scaffold`** - Structure d'écran

### Material Design 3 Compose
- **`MaterialTheme`** - Thème global
- **`ElevatedCard`** - Cartes avec élévation
- **`OutlinedTextField`** - Champs de saisie
- **`TopAppBar`** - Barre de navigation
- **`FloatingActionButton`** - Bouton d'action
- **`AlertDialog`** - Dialogues modaux
- **`Button`** - Boutons avec états

### Layout Compose
- **`Column`** - Disposition verticale
- **`Row`** - Disposition horizontale
- **`Box`** - Conteneur flexible
- **`Spacer`** - Espacement
- **`Surface`** - Surfaces avec forme et couleur

## 🚀 Installation et Utilisation

### Prérequis
- Android Studio Giraffe ou plus récent
- JDK 8 ou supérieur
- Android SDK API 24+ (Android 7.0)
- **Support Jetpack Compose** activé

### Configuration Gradle
```kotlin
buildFeatures {
    compose = true
    viewBinding = false  // Désactivé - pas de XML
    dataBinding = false  // Désactivé - pas de XML
}

composeOptions {
    kotlinCompilerExtensionVersion = "1.5.8"
}
```

### Installation
1. **Cloner/télécharger** le code source
2. **Modifier** le package name : `votre.prenom.nom.bumaye_app`
3. **Ouvrir** dans Android Studio
4. **Sync Gradle** - vérifier les dépendances Compose
5. **Build & Run** - 100% Compose, pas de XML à compiler

### Utilisation
1. **Écran d'accueil Compose** - `AccueilScreen()`
2. **FAB "+"** - Navigation vers `AjouterClientScreen()`
3. **Tap sur ClientCard** - Navigation vers `DetailsClientScreen()`
4. **Icons TopAppBar** - Actions Modifier/Supprimer
5. **TextField recherche** - Filtrage temps réel

## 💾 Gestion des Données Compose

```kotlin
// Repository avec MutableStateList pour Compose
object ClientRepository {
    private val _v_clients = mutableStateListOf<Client>()
    val _v_clients_list: List<Client> = _v_clients // Exposé pour Compose
}

// Usage dans @Composable
@Composable
fun AccueilScreen() {
    val _v_clients = remember { 
        derivedStateOf { 
            ClientRepository._v_rechercherClients(_v_searchQuery) 
        }
    }.value // Recomposition automatique
}
```

## 🧪 Avantages du 100% Compose

### Performance
- **Recomposition intelligente** - Seuls les composants modifiés se redessinent
- **Pas de findViewById** - Accès direct aux états
- **Compilation optimisée** - Bytecode Compose plus efficace

### Développement
- **Type Safety** - Erreurs détectées à la compilation
- **Code unifié** - Pas de switch XML ↔ Kotlin
- **Preview temps réel** - `@Preview` pour tester les composants
- **Debugging amélioré** - Stack traces plus claires

### Maintenance
- **Moins de fichiers** - Pas de layouts XML à maintenir
- **Logique centralisée** - État et UI au même endroit
- **Refactoring facile** - IDE support complet pour Compose

## 📸 Captures d'Écran à Fournir

1. **AccueilScreen** - Liste avec ClientCards
2. **ClientForm** - Formulaire avec ElevatedCards
3. **DetailsClientScreen** - Affichage détails avec Material3
4. **Recherche** - TextField avec résultats filtrés
5. **AlertDialog** - Confirmations stylées
6. **États d'erreur** - Validation temps réel

## 💡 Fonctionnalités Bonus Compose

### Déjà Implémentées
- **Recherche temps réel** avec `derivedStateOf`
- **Validation dynamique** des champs
- **Thème Material 3** complet
- **Navigation fluide** sans fragments
- **États loading/error** élégants

### Extensions Possibles
```kotlin
// Animations
implementation("androidx.compose.animation:animation:$compose_version")

// Permissions (caméra)
implementation("com.google.accompanist:accompanist-permissions:$accompanist_version")

// Date Picker
implementation("io.github.vanpra.compose-material-dialogs:datetime:$dialog_version")
```

## 🎓 Code Éducatif

Le code respecte les **meilleures pratiques Compose** :

```kotlin
// ✅ État hissé (Lifted State)
@Composable
fun ClientForm(onSaveClient: (Client) -> Unit) {
    var _v_nom by remember { mutableStateOf("") }
    // État local géré ici, callback pour communication
}

// ✅ Composants réutilisables
@Composable 
fun DetailRow(label: String, value: String) {
    // Composant simple, réutilisable, testable
}

// ✅ Side effects appropriés
@Composable
fun AccueilScreen() {
    val _v_clients = remember { 
        derivedStateOf { /* calcul dérivé */ }
    }.value // Pas de side effect dans le body
}
```

## 📄 Résumé Technique

**✅ 100% Jetpack Compose** - Aucun XML utilisé  
**✅ Material Design 3** - Thème moderne  
**✅ Navigation Compose** - Pas de fragments  
**✅ États réactifs** - `remember`, `mutableStateOf`  
**✅ Validation temps réel** - UX fluide  
**✅ Code type-safe** - Kotlin + Compose  
**✅ Architecture claire** - MVVM simplifié

---

*Développé en 100% Jetpack Compose pour Maison BUMAYE - 高级定制时装屋*

**Note importante :** Cette version utilise exclusivement Jetpack Compose. Aucun fichier XML n'est utilisé pour l'interface utilisateur, respectant ainsi votre demande spécifique.

## 📋 Champs de Saisie

### Informations Personnelles
- **Nom et prénoms** *(obligatoire)*
- **Numéro de téléphone** *(obligatoire, 8-12 chiffres)*

### Mesures Corporelles (17 mesures)
- Épaule, Poitrine, Longueur taille, Tour ventrale
- Hanche, Longueur corsage, Ceinture, Longueur jupe
- Longueur robe, Longueur manche, Taille de manche
- Longueur pantalon, Longueur robe courte, Tour de cuisse
- Longueur de genoux, Tour de genoux, Bas
- **Autres mesures** *(champ libre)*

### Suivi de Commande
- **Date de commande** *(obligatoire, format JJ/MM/AAAA)*
- **Date de livraison** *(obligatoire, doit être ≥ date de commande)*

### Informations de Paiement
- **Somme totale** *(obligatoire, montant positif)*
- **Avance** *(obligatoire, montant positif)*
- **Reste** *(calculé automatiquement)*

## 🔒 Validations Implémentées

1. **Nom et prénoms** : Non vide, minimum 2 caractères
2. **Numéro de téléphone** : Format numérique 8-12 chiffres
3. **Dates** : Format valide et cohérence (livraison ≥ commande)
4. **Montants** : Valeurs numériques positives
5. **Calcul automatique** : Reste = Somme totale - Avance

## 🎨 Choix de Design

### Interface Utilisateur
- **Material Design 3** pour une expérience moderne et cohérente
- **Cards** pour organiser les informations de manière claire
- **Couleurs** : Palette professionnelle avec des accents colorés
- **Typography** : Police lisible avec hiérarchie claire
- **Icons** : Iconographie Material pour une navigation intuitive

### Expérience Utilisateur
- **Navigation fluide** entre les écrans
- **Feedback visuel** pour les actions utilisateur
- **Messages d'erreur** contextuels et multilingues
- **Recherche instantanée** pour trouver rapidement un client
- **Confirmations** pour les actions critiques (suppression)

### Responsive Design
- **Formulaires adaptatifs** qui s'ajustent à la taille d'écran
- **Scrolling vertical** pour les longs formulaires
- **Disposition en colonnes** pour optimiser l'espace

## 🌍 Internationalisation

L'application intègre du **texte bilingue** (Français/Chinois) dans :
- Les labels des champs de formulaire
- Les messages de validation
- Les titres et descriptions
- Les messages de confirmation

Cette approche respecte les exigences du projet tout en améliorant l'accessibilité.

## 📁 Structure du Projet

```
votre.prenom.nom.bumaye_app/
├── model/
│   └── Client.kt                 # Modèle de données client
├── repository/
│   └── ClientRepository.kt       # Gestion des données en mémoire
├── ui/
│   ├── components/
│   │   ├── ClientCard.kt         # Composant carte client
│   │   └── ClientForm.kt         # Formulaire réutilisable
│   ├── navigation/
│   │   └── BumayeNavigation.kt   # Configuration navigation
│   ├── screens/
│   │   ├── AccueilScreen.kt      # Écran liste clients
│   │   ├── AjouterClientScreen.kt # Écran ajout client
│   │   ├── DetailsClientScreen.kt # Écran détails client
│   │   └── ModifierClientScreen.kt # Écran modification
│   └── theme/
│       └── Theme.kt              # Thème de l'application
├── utils/
│   └── ValidationUtils.kt        # Utilitaires de validation
└── MainActivity.kt               # Activité principale
```

## 🚀 Installation et Utilisation

### Prérequis
- Android Studio Arctic Fox ou plus récent
- JDK 8 ou supérieur
- Android SDK API 24+ (Android 7.0)

### Installation
1. **Cloner ou télécharger** le code source
2. **Ouvrir** le projet dans Android Studio
3. **Modifier** le package name : `votre.prenom.nom.bumaye_app`
4. **Synchroniser** les dépendances Gradle
5. **Compiler et installer** sur un appareil/émulateur Android

### Utilisation
1. **Écran d'accueil** : Visualiser la liste des clients
2. **Bouton "+"** : Ajouter un nouveau client
3. **Cliquer sur une carte** : Voir les détails d'un client
4. **Icône crayon** : Modifier les informations
5. **Icône poubelle** : Supprimer un client
6. **Barre de recherche** : Rechercher par nom ou téléphone

## 💾 Gestion des Données

Les données sont **stockées en mémoire** pendant l'exécution de l'application :
- Utilisation d'une `MutableStateList` pour la réactivité
- Persistance uniquement durant la session
- Réinitialisation à chaque redémarrage de l'app

## 🧪 Tests et Validation

### Tests de Validation
- Validation des champs obligatoires
- Format du numéro de téléphone
- Cohérence des dates
- Calculs automatiques

### Interface Utilisateur
- Navigation entre tous les écrans
- Affichage correct des données
- Responsive design sur différentes tailles d'écran

## 📸 Captures d'Écran à Fournir

1. **Écran d'accueil** avec liste des clients
2. **Formulaire d'ajout** d'un nouveau client
3. **Écran de détails** d'un client
4. **Formulaire de modification**
5. **Fonction de recherche** en action
6. **Messages de validation** d'erreur

## 👨‍💻 Développement

### Bonnes Pratiques Respectées
- **Séparation des responsabilités** (UI, logique, données)
- **Code lisible** avec commentaires bilingues
- **Validation robuste** des données utilisateur
- **Gestion d'erreurs** appropriée


*Développé pour Maison BUMAYE