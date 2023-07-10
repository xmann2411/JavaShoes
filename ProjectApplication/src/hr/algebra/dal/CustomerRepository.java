/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Customer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Karla
 */
public class CustomerRepository implements Repository<Customer> {
    
    private static final String ID_CUSTOMER = "IDKupac";
    private static final String IME = "Ime";
    private static final String PREZIME = "Prezime";
    private static final String OIB = "OIB";
    private static final String EMAIL = "EMail";
    private static final String BROJTELEFONA = "BrojTelefona";
    private static final String NAZIVKARTICE = "NazivKartice";

    private static final String CREATE_CUSTOMER = "{ CALL createKupac (?,?,?,?,?,?,?) }";
    private static final String UPDATE_CUSTOMER = "{ CALL updateKupac (?,?,?,?,?,?,?) }";
    private static final String DELETE_CUSTOMER = "{ CALL deleteKupac (?) }";
    private static final String SELECT_CUSTOMER = "{ CALL selectKupac (?) }";
    private static final String SELECT_CUSTOMERS = "{ CALL selectKupci }";


    @Override
    public int create(Customer customer) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_CUSTOMER)) {

            //get parametre izvlaci iz shoe.java
            stmt.setString("@" + IME, customer.getIme());
            stmt.setString("@" + PREZIME, customer.getPrezime());
            stmt.setString("@" + OIB, customer.getOIB());
            stmt.setString("@" + EMAIL, customer.getEmail());
            stmt.setString("@" + BROJTELEFONA, customer.getBrojTelefona());
            stmt.setString("@" + NAZIVKARTICE, customer.getNazivKartice());
            stmt.registerOutParameter("@" + ID_CUSTOMER, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + ID_CUSTOMER);
        }
    }

    @Override
    public void update(Customer data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_CUSTOMER)) {

            stmt.setString("@" + IME, data.getIme());
            stmt.setString("@" + PREZIME, data.getPrezime());
            stmt.setString("@" + OIB, data.getOIB());
            stmt.setString("@" + EMAIL, data.getEmail());
            stmt.setString("@" + BROJTELEFONA, data.getBrojTelefona());
            stmt.setString("@" + NAZIVKARTICE, data.getNazivKartice());
            stmt.setInt("@" + ID_CUSTOMER, data.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_CUSTOMER)) {

            stmt.setInt("@" + ID_CUSTOMER, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Customer> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_CUSTOMER)) {

            stmt.setInt("@" + ID_CUSTOMER, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Customer(
                            rs.getInt(ID_CUSTOMER),
                            rs.getString(IME),
                            rs.getString(PREZIME),
                            rs.getString(OIB),
                            rs.getString(EMAIL),
                            rs.getString(BROJTELEFONA),
                            rs.getString(NAZIVKARTICE)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Customer> selectAll() throws Exception {
        List<Customer> customers = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_CUSTOMERS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt(ID_CUSTOMER),
                            rs.getString(IME),
                            rs.getString(PREZIME),
                            rs.getString(OIB),
                            rs.getString(EMAIL),
                            rs.getString(BROJTELEFONA),
                            rs.getString(NAZIVKARTICE)));
            }
        }
        return customers;
    }
    
    
}
