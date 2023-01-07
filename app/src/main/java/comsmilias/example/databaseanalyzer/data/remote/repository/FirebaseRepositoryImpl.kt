package comsmilias.example.databaseanalyzer.data.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import comsmilias.example.databaseanalyzer.data.remote.dto.TimeStampDto
import comsmilias.example.databaseanalyzer.data.remote.dto.toTimeStamp
import comsmilias.example.databaseanalyzer.domain.model.TimeStamp
import comsmilias.example.databaseanalyzer.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl : FirebaseRepository {

    private val myRef = Firebase.database.reference

    override fun getFirebaseData(): LiveData<List<TimeStamp>> {
        val data = MutableLiveData<List<TimeStamp>>(
            emptyList()
        )
        myRef.child("users").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val list: MutableList<TimeStampDto> = mutableListOf()
                snapshot.children.forEach {
                    it.children.forEach { data ->
                        val s = data.getValue(TimeStampDto::class.java)
                        s!!.timeCaptured = data.key.toString()
                        list += s
                    }
                }
                data.postValue(list.map { it.toTimeStamp() })
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
        return data
    }
}