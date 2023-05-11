package comsmilias.example.databaseanalyzer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import comsmilias.example.databaseanalyzer.domain.model.TimeStamp
import comsmilias.example.databaseanalyzer.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultsFragmentViewModel @Inject constructor(
    firebaseRepository: FirebaseRepository
) : ViewModel() {

    val users: LiveData<List<TimeStamp>> = firebaseRepository.getFirebaseData()

    private val _result: MutableLiveData<String> = MutableLiveData()

    val result: LiveData<String>
        get() = _result


    fun getSmilePercentagePerVideo(): String {
        val map = HashMap<String, Int>()
        users.value?.forEach { timestamp ->
            if (map.containsKey(timestamp.title) && timestamp.data.contains("smiling")) {
                map[timestamp.title] = map[timestamp.title]!! + 1
            } else {
                map[timestamp.title] = 1
            }
        }
        val stringBuilder = StringBuilder()
        map.forEach { (key, value) ->
            stringBuilder.append(key)
                .append(": ")
                .append("$value/${getCountVideo(key)}\n\n")
        }
        return stringBuilder.toString()
    }

    fun setResult(result: String){
        _result.postValue(result)
    }

    private fun getCountVideo(name: String): Int {
        return users.value!!.count { it.title == name }
    }
}