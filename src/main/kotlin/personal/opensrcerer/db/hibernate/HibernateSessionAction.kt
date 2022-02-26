package personal.opensrcerer.db.hibernate

import org.hibernate.Session

fun interface HibernateSessionAction {
    fun perform(session: Session): Any
}