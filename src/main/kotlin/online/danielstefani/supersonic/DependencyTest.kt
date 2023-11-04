package online.danielstefani.supersonic

import org.koin.core.annotation.Single

@Single
class DependencyTest(private val yoer: Yoer) {
    fun yo() {
        yoer.yo()
    }
}