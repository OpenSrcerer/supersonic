package personal.opensrcerer.db.hibernate

import org.hibernate.Session
import org.hibernate.Transaction

data class HibernateResult<T>(
    val result: T?,
    val session: Session
)