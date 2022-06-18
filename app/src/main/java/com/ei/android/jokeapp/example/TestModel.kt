package com.ei.android.jokeapp.example
class TestModel(resourceManager: ResourceManager): Model {
    private var callback: ResultCallback? = null

    private var count = 0

    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)

    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            when(count){
                0->callback?.provide(BaseJoke("testText","punchline"))
                1->callback?.provide(FavoriteJoke("favoriteJoke","favorite punchline"))
                2->callback?.provide(FailedJoke(serviceUnavailable.getMessage()))
            }
            count++
        }.start()
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}
