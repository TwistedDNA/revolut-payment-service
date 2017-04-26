import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;

/**
 * Created by Maksym_Mazur on 4/26/2017.
 */
public class testGround {

    public static void main(String[] args) {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:˜./test");
        ds.setUser("sa");
        ds.setPassword("sa");
        try (Connection conn = ds.getConnection()) {
            conn.createStatement().executeUpdate("CREATE TABLE ForgeRock\n"
                                                + "\t(`id` int, `productName` varchar(7), `description` varchar(55))\n"
                                                + ";");

            conn.createStatement().executeUpdate("INSERT INTO ForgeRock\n"
                                                 + "\t(`id`, `productName`, `description`)\n"
                                                 + "VALUES\n"
                                                 + "\t(1, 'OpenIDM', 'Platform for building enterprise provisioning solutions'),\n"
                                                 + "\t(2, 'OpenAM', 'Full-featured access management'),\n"
                                                 + "\t(3, 'OpenDJ', 'Robust LDAP server for Java')\n"
                                                 + ";\n");
            //conn.createStatement().executeQuery("select * from ForgeRock").getConnection
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {

        }
    }

}
