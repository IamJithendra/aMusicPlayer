package com.zee.amusicplayer.presentation.songs_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zee.amusicplayer.domain.model.SongItem
import com.zee.amusicplayer.domain.use_cases.GetAllSongsUseCase
import com.zee.amusicplayer.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsVieModel @Inject constructor(private val useCase: GetAllSongsUseCase) : ViewModel() {


    private val _allSongs = mutableStateOf(listOf<SongItem>())
    val allSongs: State<List<SongItem>> = _allSongs

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _allSongs.value = useCase.invoke()
            } catch (e: Exception) {
                log("e $e")
            }
        }

    }

}