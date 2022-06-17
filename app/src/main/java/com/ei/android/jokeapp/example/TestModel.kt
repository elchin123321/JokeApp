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
                0->callback?.provideSuccess(Joke("testText","punchline"))
                1->callback?.provideError(noConnection)
                2->callback?.provideError(serviceUnavailable)
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
