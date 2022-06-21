package com.ei.android.jokeapp.example
class TestModel(resourceManager: ResourceManager): Model {
    private var callback: JokeCallback? = null

    private var count = 0

    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)

    override fun getJoke() {
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

    override fun init(callback: JokeCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {

    }

    override fun chooseDataSource(favorites: Boolean) {
    }
}
