package app.salo.przelewetarte.data.repository

import android.util.Log
import app.salo.przelewetarte.common.Resource
import app.salo.przelewetarte.domain.repository.DatabaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val database: DatabaseReference,
    private val auth: FirebaseAuth
): DatabaseRepository {
    override suspend fun getLessons(): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }
}