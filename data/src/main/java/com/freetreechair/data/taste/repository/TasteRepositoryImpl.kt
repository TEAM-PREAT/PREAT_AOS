package com.freetreechair.data.taste.repository

import com.freetreechair.data.preferences.PreferencesDataSource
import com.freetreechair.domain.taste.repository.TasteRepository
import javax.inject.Inject

class TasteRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) : TasteRepository {
    override fun saveTastes(tastes: String) {
        preferencesDataSource.saveTastes(tastes)
    }
}
