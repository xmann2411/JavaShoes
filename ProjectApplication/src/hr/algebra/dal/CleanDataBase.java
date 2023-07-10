/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Karla
 */
public class CleanDataBase {

    private static final String DIR = "assets";
    private static final String CLEAR = "{ CALL clearDB }";

    //rs -> ako je naso vrati optional user
    //ako krepa optional empty vrati nista
    //optional empty je wraper tako da ne radim s nullom
    public void clean() throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CLEAR)) {
            stmt.execute();
        }
        cleanAssets();
    }

    private void cleanAssets() {
        File dir = new File(DIR);
        for (File file : dir.listFiles()) {
            if(!file.isDirectory()) {
                file.delete();
            }
        }
    }
}
