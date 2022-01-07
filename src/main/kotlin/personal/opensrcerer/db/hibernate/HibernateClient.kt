package personal.opensrcerer.db.hibernate

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

object HibernateClient {
    val sessionFactory: SessionFactory

    init {
        sessionFactory = Configuration()
            .configure()
            .buildSessionFactory()
    }

    fun <T> doInHibernate(action: HibernateSessionAction<T>): T {
        val session = sessionFactory.currentSession
        val transaction = session.beginTransaction()
        val returnValue: T = action.perform(session)
        transaction.commit()
        return returnValue
    }

    fun getFactory(): SessionFactory {
        return this.sessionFactory
    }
}