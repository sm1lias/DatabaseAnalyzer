package comsmilias.example.databaseanalyzer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import comsmilias.example.databaseanalyzer.data.remote.dto.TimeStampDto
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import comsmilias.example.databaseanalyzer.domain.model.TimeStamp
import comsmilias.example.databaseanalyzer.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TableFragmentViewModel @Inject constructor(
    firebaseRepository: FirebaseRepository
    ) : ViewModel(){

    val users: LiveData<List<TimeStamp>> = firebaseRepository.getFirebaseData()


}