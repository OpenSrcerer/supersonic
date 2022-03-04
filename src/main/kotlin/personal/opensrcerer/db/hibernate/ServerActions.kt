package personal.opensrcerer.db.hibernate

import personal.opensrcerer.db.entities.Guild
import personal.opensrcerer.db.entities.SubsonicServer
import reactor.core.publisher.Mono
import java.util.UUID

object ServerActions {
    fun getGuild(guildId: String): Mono<HibernateResult<Guild?>> {
        return HibernateClient.transaction { session ->
            val sqlQuery = session.createNativeQuery("select * from guilds where guild_id = :guildId")
                .setParameter("guildId", guildId)
            return@transaction sqlQuery.uniqueResult()
        }
    }

    fun getServer(serverId: UUID): Mono<HibernateResult<SubsonicServer?>> {
        return HibernateClient.transaction { session ->
            val sqlQuery = session.createNativeQuery("select * from servers where server_id = cast(:serverId AS uuid)")
                .setParameter("serverId", serverId)
            return@transaction sqlQuery.uniqueResult()
        }
    }

    fun newServer(server: SubsonicServer): Mono<HibernateResult<SubsonicServer?>> {
        return HibernateClient.transaction { session -> session.save(server) }
    }

    fun deleteServer(serverId: UUID): Mono<HibernateResult<SubsonicServer?>> {
        return getServer(serverId)
            .flatMap<HibernateResult<SubsonicServer?>> { hibRes -> HibernateClient.transaction {
                    session -> session.delete(hibRes.result)
            } }
            .map { hibRes -> hibRes.session.transaction.commit(); return@map hibRes }
    }
}