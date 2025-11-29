package julio.koffi.daklu.bumayeapp

import android.app.Application
import julio.koffi.daklu.bumayeapp.data.ClientRepository

class BumayeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ClientRepository.initialize(this) // Initialisation du repository
    }
}
