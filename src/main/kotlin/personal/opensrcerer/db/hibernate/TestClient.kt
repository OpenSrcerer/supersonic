package personal.opensrcerer.db.hibernate

import org.hibernate.cfg.Configuration
import personal.opensrcerer.db.entities.Guild

class TestClient {
    companion object {
        fun doStuff() {
            val sessionFactory = Configuration()
                .configure()
                .addAnnotatedClass(Guild::class.java)
                .buildSessionFactory()

            var session = sessionFactory.currentSession
        }
    }
}