package personal.opensrcerer.db.hibernate

import personal.opensrcerer.db.entities.SubsonicServer
import reactor.core.publisher.Mono
import java.util.UUID

object ServerActions {
    fun getServer(serverId: UUID): Mono<HibernateResult<SubsonicServer?>> {
        return HibernateClient.query { session ->
            val sqlQuery = session.createNativeQuery("select * from servers where server_id = {serverId}")
                .setParameter("{serverId}", serverId)
            return@query sqlQuery.uniqueResult()
        }
    }

    fun newServer(server: SubsonicServer): Mono<HibernateResult<SubsonicServer>> {
        return HibernateClient.transaction { session -> session.save(server) }
    }

    fun deleteServer(serverId: UUID): Mono<HibernateResult<SubsonicServer?>> {
        return getServer(serverId)
            .flatMap<HibernateResult<SubsonicServer?>?> { hibRes -> HibernateClient.transaction(
                { session -> session.delete(hibRes.result) },
                hibRes.session
            )}
            .map { hibRes -> hibRes.session.transaction.commit(); return@map hibRes }
    }
}