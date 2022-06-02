package app.salo.przelewetarte.domain.repository

import app.salo.przelewetarte.common.Resource
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun getLessons(): Flow<Resource<Boolean>>
}