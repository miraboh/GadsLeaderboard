package com.myrabohuche.gadsleaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LearningViewModel @Inject constructor(
    private val repository: GadsRepository) : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private var viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    val foodDeferred = CompletableDeferred<Flow<State<List<LearningHours?>>>>()

    init {
        refreshDataFromRepository()
    }

    private val _dataState: MutableLiveData<State<List<LearningHours>>> = MutableLiveData()
    val dataState: LiveData<State<List<LearningHours>>>
        get() = _dataState

    @ExperimentalCoroutinesApi
    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                val learningHours = repository.getLearningH()

                foodDeferred.complete(learningHours)

            } catch (networkError: IOException) { }
        }
    }
    suspend fun load(): Flow<State<List<LearningHours?>>> =foodDeferred.await()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}