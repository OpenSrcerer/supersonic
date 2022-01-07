package personal.opensrcerer.db.hibernate

import org.hibernate.Session

fun interface HibernateSessionAction<T> {
    fun perform(session: Session): T
}