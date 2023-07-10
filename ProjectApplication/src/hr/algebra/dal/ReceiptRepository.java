/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Receipt;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Karla
 */
public class ReceiptRepository implements Repository<Receipt> {
    
    private static final String ID_RECEIPT = "IDRacun";
    private static final String KUPACID = "KupacID";
    private static final String PRODAVACID = "ProdavacID";
    private static final String PARCIPELAID = "ParCipelaID";
    private static final String CIJENA = "cijena";
    private static final String POPUST = "popust";
    private static final String DATUMIZDAVANJA = "datumIzdavanja";

    private static final String CREATE_RECEIPT = "{ CALL createRacun (?,?,?,?,?,?,?) }";
    private static final String UPDATE_RECEIPT = "{ CALL updateRacun (?,?,?,?,?,?,?) }";
    private static final String DELETE_RECEIPT = "{ CALL deleteRacun (?) }";
    private static final String SELECT_RECEIPT = "{ CALL selectRacun (?) }";
    private static final String SELECT_RECEIPTS = "{ CALL selectRacuni }";

    @Override
    public int create(Receipt receipt) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_RECEIPT)) {

            stmt.setInt("@" + KUPACID, receipt.getKupacID());
            stmt.setInt("@" + PRODAVACID, receipt.getProdavacID());
            stmt.setInt("@" + PARCIPELAID, receipt.getParCipelaID());
            stmt.setInt("@" + CIJENA, receipt.getCijena());
            stmt.setInt("@" + POPUST, receipt.getPopust());
            stmt.setString("@" + DATUMIZDAVANJA, receipt.getDatumIzdavanja().format(Receipt.DATE_FORMATTER));
            stmt.registerOutParameter("@" + ID_RECEIPT, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + ID_RECEIPT);
        }
    }

    @Override
    public void update(Receipt data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_RECEIPT)) {

            stmt.setInt("@" + KUPACID, data.getKupacID());
            stmt.setInt("@" + PRODAVACID, data.getProdavacID());
            stmt.setInt("@" + PARCIPELAID, data.getParCipelaID());
            stmt.setInt("@" + CIJENA, data.getCijena());
            stmt.setInt("@" + POPUST, data.getPopust());
            stmt.setString("@" + DATUMIZDAVANJA, data.getDatumIzdavanja().format(Receipt.DATE_FORMATTER));
            
            stmt.setInt("@" + ID_RECEIPT, data.getId());

            stmt.executeUpdate();
        }
    }

        
    /**
     *
     * @param id
     * @throws Exception
     */
    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_RECEIPT)) {

            stmt.setInt("@" + ID_RECEIPT, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Receipt> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_RECEIPT)) {

            stmt.setInt("@" + ID_RECEIPT, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Receipt(
                            rs.getInt(ID_RECEIPT),
                            rs.getInt(KUPACID),
                            rs.getInt(PRODAVACID),
                            rs.getInt(PARCIPELAID),
                            rs.getInt(CIJENA),
                            rs.getInt(POPUST),
                            LocalDateTime.parse(rs.getString(DATUMIZDAVANJA).replace(" ", "T"), Receipt.DATE_FORMATTER)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Receipt> selectAll() throws Exception {
        List<Receipt> receipts = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_RECEIPTS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                receipts.add(new Receipt(
                        rs.getInt(ID_RECEIPT),
                        rs.getInt(KUPACID),
                        rs.getInt(PRODAVACID),
                        rs.getInt(PARCIPELAID),
                        rs.getInt(CIJENA),
                        rs.getInt(POPUST),
                        //DATUMM??
                        LocalDateTime.parse(rs.getString(DATUMIZDAVANJA).replace(" ", "T"), Receipt.DATE_FORMATTER)));
            }
        }
        return receipts;
    }
    
}
