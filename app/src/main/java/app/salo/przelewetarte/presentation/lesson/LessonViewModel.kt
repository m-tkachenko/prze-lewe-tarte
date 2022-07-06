package app.salo.przelewetarte.presentation.lesson

import androidx.lifecycle.ViewModel
import app.salo.przelewetarte.common.Materials
import app.salo.przelewetarte.domain.use_case.UserAuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(

): ViewModel() {
    private val materials = Materials()
    val images = materials.images
    val beginnings = materials.beginning
    val tasks = materials.task
}
