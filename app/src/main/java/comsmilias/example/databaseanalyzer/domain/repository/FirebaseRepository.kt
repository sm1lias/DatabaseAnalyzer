package comsmilias.example.databaseanalyzer.domain.repository

import androidx.lifecycle.LiveData
import comsmilias.example.databaseanalyzer.domain.model.TimeStamp

interface FirebaseRepository {
    fun getFirebaseData(): LiveData<List<TimeStamp>>
}