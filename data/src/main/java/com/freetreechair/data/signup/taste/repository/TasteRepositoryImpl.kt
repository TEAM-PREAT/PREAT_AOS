package com.freetreechair.data.signup.taste.repository

import com.freetreechair.data.util.preferences.PreferencesDataSource
import com.freetreechair.domain.signup.taste.repository.TasteRepository
import javax.inject.Inject

class TasteRepositoryImpl @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) : TasteRepository {
    override fun saveTastes(tastes: String) {
        preferencesDataSource.saveTastes(tastes)
    }
}
