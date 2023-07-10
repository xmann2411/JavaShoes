/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Seller;
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
public class SellerRepository implements Repository<Seller> {
    
    private static final String ID_SELLER = "IDProdavac";
    private static final String IME = "Ime";
    private static final String PREZIME = "Prezime";
    private static final String GODINESTAZA = "GodineStaza";
    private static final String PRODAVAONICAID = "ProdavaonicaID";

    private static final String CREATE_SELLER = "{ CALL createProdavac (?,?,?,?,?) }";
    private static final String UPDATE_SELLER = "{ CALL updateProdavac (?,?,?,?,?) }";
    private static final String DELETE_SELLER = "{ CALL deleteProdavac (?) }";
    private static final String SELECT_SELLER = "{ CALL selectProdavac (?) }";
    private static final String SELECT_SELLERS = "{ CALL selectProdavaci }";

    @Override
    public int create(Seller seller) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_SELLER)) {

            stmt.setString("@" + IME, seller.getIme());
            stmt.setString("@" + PREZIME, seller.getPrezime());
            stmt.setInt("@" + GODINESTAZA, seller.getGodineStaza());
            stmt.setInt("@" + PRODAVAONICAID, seller.getProdavaonicaID());
            stmt.registerOutParameter("@" + ID_SELLER, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + ID_SELLER);
        }
    }

    @Override
    public void update(Seller data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_SELLER)) {

            stmt.setString("@" + IME, data.getIme());
            stmt.setString("@" + PREZIME, data.getPrezime());
            stmt.setInt("@" + GODINESTAZA, data.getGodineStaza());
            stmt.setInt("@" + PRODAVAONICAID, data.getProdavaonicaID());
            stmt.setInt("@" + ID_SELLER, data.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_SELLER)) {

            stmt.setInt("@" + ID_SELLER, id);

            stmt.executeUpdate();
        }
    }
    
    @Override
    public Optional<Seller> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_SELLER)) {

            stmt.setInt("@" + ID_SELLER, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Seller(
                        rs.getInt(ID_SELLER),
                        rs.getString(IME),
                        rs.getString(PREZIME),
                        rs.getInt(GODINESTAZA),
                        rs.getInt(PRODAVAONICAID)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Seller> selectAll() throws Exception {
        List<Seller> sellers = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_SELLERS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                sellers.add(new Seller(
                        rs.getInt(ID_SELLER),
                        rs.getString(IME),
                        rs.getString(PREZIME),
                        rs.getInt(GODINESTAZA),
                        rs.getInt(PRODAVAONICAID)));
            }
        }
        return sellers;
    }
    
}
