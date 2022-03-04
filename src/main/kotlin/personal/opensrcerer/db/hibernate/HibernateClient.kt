package personal.opensrcerer.db.hibernate

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.cfg.Configuration
import reactor.core.publisher.Mono

internal object HibernateClient {
    private val sessionFactory: SessionFactory = Configuration()
        .configure()
        .buildSessionFactory()

    inline fun <reified T> query(action: HibernateSessionAction): Mono<HibernateResult<T?>> {
        val session = sessionFactory.currentSession
        val returnValue = action.perform(session) as T?
        return Mono.just(HibernateResult(returnValue, session))
    }

    inline fun <reified T> transaction(action: HibernateSessionAction): Mono<HibernateResult<T?>> {
        val session = sessionFactory.currentSession
        session.beginTransaction()
        val returnValue = action.perform(session) as T?
        return Mono.just(HibernateResult(returnValue, session))
    }
}
