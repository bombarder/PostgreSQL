package ua.com.juja.cqlcmd;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.DatabaseManager;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class DataBaseManagerTest {

    private DatabaseManager manager;

    @Before
    public void setup(){
        manager = new DatabaseManager();
        manager.connect("sqlcmd", "postgres", "2005980");
    }

    @Test
    public void testGetAllTablesNames(){
        String[] tableNames = manager.getTables();
        assertEquals("[users, test]" , Arrays.toString(tableNames));
    }
}
