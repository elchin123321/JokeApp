package com.ei.android.jokeapp.example
class TestModel(resourceManager: ResourceManager) {
    private var callback: JokeCallback? = null

    private var count = 0

    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)

     fun getJoke() {
        Thread {
            Thread.sleep(1000)
            when(count){
                0->callback?.provide(BaseJokeUIModel("testText","punchline"))
                1->callback?.provide(FavoriteJokeUIModel("favoriteJoke","favorite punchline"))
                2->callback?.provide(FailedJokeUIModel(serviceUnavailable.getMessage()))
            }
            count++
        }.start()
    }

    fun init(callback: JokeCallback) {
        this.callback = callback
    }

    fun clear() {
        callback = null
    }

    fun changeJokeStatus(jokeCallback: JokeCallback) {

    }

    fun chooseDataSource(favorites: Boolean) {
    }
}
