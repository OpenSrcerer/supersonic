package personal.opensrcerer.db.entities

import jakarta.persistence.*

@Entity(name = "Guild")
@Table(name = "guilds")
class Guild(
    @Id
    @Column(name = "guild_id", nullable = false)
    val id: String,

    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        mappedBy = "guild"
    )
    @JoinColumn(name = "guild_servers")
    val servers: Set<SubsonicServer>
)