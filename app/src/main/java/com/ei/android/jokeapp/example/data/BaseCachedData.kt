package com.ei.android.jokeapp.example.data

class BaseCachedData: CachedData {
    private var cached: ChangeCommonItem = ChangeCommonItem.Empty()
    override fun save(data: CommonDataModel) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
        return cached.change(changeStatus)
    }
}