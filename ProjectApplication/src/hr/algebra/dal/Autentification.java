/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Karla
 */
public class Autentification {
    
    private static final String LOGIN = "{ CALL LoginUser (?, ?) }";
    
    //rs -> ako je naso vrati optional user
    //ako krepa optional empty vrati nista
    //optional empty je wraper tako da ne radim s nullom
    public Optional<User> login(String username, String password) throws SQLException{
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(LOGIN)) {

            //get parametre izvlaci iz shoe.java
            stmt.setString("@username", username);
            stmt.setString("@password", password);
            
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    return Optional.of(new User(rs.getInt("IDKorisnik"), rs.getString("Username"), rs.getString("Password"), rs.getInt("RoleID")));
                }
            }
        }
        return Optional.empty();
    }
    
}
