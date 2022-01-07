package personal.opensrcerer;

import personal.opensrcerer.db.entities.Guild;
import personal.opensrcerer.db.hibernate.HibernateClient;
import personal.opensrcerer.launch.SupersonicLaunchWrapper;

import static java.util.Collections.emptySet;

// A Discord Bot that allows playing music from Subsonic Servers.

public class SupersonicApplication {
    public static void main(String[] args) throws Exception {
        SupersonicLaunchWrapper.run();
        System.out.println("Saved a guild");
        HibernateClient.INSTANCE.doInHibernate(session ->
                session.save(new Guild("93384728", emptySet()))
        );
    }
}