package personal.opensrcerer.db.entities

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.*

@Entity(name = "subsonicServer")
@Table(name = "servers")
class SubsonicServer(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    @Column(name = "server_id", updatable = false, nullable = false)
    val id: UUID,

    @Column(name = "server_host", nullable = false)
    val host: String,

    @Column(name = "server_port", nullable = false)
    val port: Int,

    @Column(name = "server_username", nullable = false)
    val username: String,

    @Column(name = "server_password", nullable = false)
    val password: String,

    @Column(name = "server_version")
    val version: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val guild: Guild
)