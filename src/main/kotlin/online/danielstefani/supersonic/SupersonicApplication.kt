package online.danielstefani.supersonic

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

@Module
@ComponentScan
class SupersonicApplication : KoinComponent {
    private val dependencyTest: DependencyTest by inject()

    fun yo() {
        dependencyTest.yo()
    }
}

fun main() {
    startKoin {
        modules(SupersonicApplication().module)
    }

    SupersonicApplication().yo()
}