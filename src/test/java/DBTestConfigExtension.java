import com.barclays.ims.DbUtils;
import org.junit.jupiter.api.BeforeAll;


public class DBTestConfigExtension {
    @BeforeAll
    public void dbTestConfig() throws Exception {

         DbUtils dbUtils = new DbUtils();
         dbUtils.executeSQLFile("src\\test\\resources\\sql-schema.sql");
     }
}