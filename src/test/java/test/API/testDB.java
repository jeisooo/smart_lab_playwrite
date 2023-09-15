package test.API;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.tools.sqlConnector;
public class testDB {
    sqlConnector connection = new sqlConnector();

    @Test
    @DisplayName("Test connection")
    public void testConnection(){
        connection.connectDb();
    }

    @Test
    @DisplayName("Test shares table")
    public void testShouldGiveSelectedShares(){
        connection.selectShares();
    }
}
