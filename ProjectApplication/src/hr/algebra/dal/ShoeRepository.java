/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Shoe;
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
public class ShoeRepository implements Repository<Shoe> {

    private static final String ID_SHOE = "IDParCipela";
    private static final String MARKA = "Marka";
    private static final String MODEL = "Model";
    private static final String BROJ = "Broj";
    private static final String BOJA = "Boja";
    private static final String OPISPROIZVODA = "OpisProizvoda";
    private static final String PICTURE_PATH = "PicturePath";

    private static final String CREATE_SHOE = "{ CALL createParCipela (?,?,?,?,?,?,?) }";
    private static final String UPDATE_SHOE = "{ CALL updateParCipela (?,?,?,?,?,?,?) }";
    private static final String DELETE_SHOE = "{ CALL deleteParCipela (?) }";
    private static final String SELECT_SHOE = "{ CALL selectParCipela (?) }";
    private static final String SELECT_SHOES = "{ CALL selectParoveCipela }";

    /**
     *
     * @param shoe
     * @return
     * @throws Exception
     */
    @Override
    public int create(Shoe shoe) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_SHOE)) {

            //get parametre izvlaci iz shoe.java
            stmt.setString("@" + MARKA, shoe.getMarka());
            stmt.setString("@" + MODEL, shoe.getModel());
            //?
            //kako integer izvuc
            stmt.setInt("@" + BROJ, shoe.getBroj());
            stmt.setString("@" + BOJA, shoe.getBoja());
            stmt.setString("@" + OPISPROIZVODA, shoe.getOpisProizvoda());
            stmt.setString("@" + PICTURE_PATH, shoe.getPicturePath());
            stmt.registerOutParameter("@" + ID_SHOE, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + ID_SHOE);
        }
    }

    @Override
    public void update(Shoe data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_SHOE)) {

            stmt.setString("@" + MARKA, data.getMarka());
            stmt.setString("@" + MODEL, data.getModel());
            //?
            //kako integer izvuc
            stmt.setInt("@" + BROJ, data.getBroj());
            stmt.setString("@" + BOJA, data.getBoja());
            stmt.setString("@" + OPISPROIZVODA, data.getOpisProizvoda());
            stmt.setString("@" + PICTURE_PATH, data.getPicturePath());
            stmt.setInt("@" + ID_SHOE, data.getId());

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
                CallableStatement stmt = con.prepareCall(DELETE_SHOE)) {

            stmt.setInt("@" + ID_SHOE, id);

            stmt.executeUpdate();
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Optional<Shoe> select(int id) throws Exception{
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_SHOE)) {

            stmt.setInt("@" + ID_SHOE, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Shoe(
                             rs.getInt(ID_SHOE),
                        rs.getString(MARKA),
                        rs.getString(MODEL),
                        rs.getInt(BROJ),
                        rs.getString(BOJA),
                        rs.getString(OPISPROIZVODA),
                        rs.getString(PICTURE_PATH)));
                }
            }
        }
        return Optional.empty();
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Shoe> selectAll() throws Exception{
        List<Shoe> shoes = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_SHOES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                shoes.add(new Shoe(
                        rs.getInt(ID_SHOE),
                        rs.getString(MARKA),
                        rs.getString(MODEL),
                        rs.getInt(BROJ),
                        rs.getString(BOJA),
                        rs.getString(OPISPROIZVODA),
                        rs.getString(PICTURE_PATH)
                        ));
            }
        }
        return shoes;
    }
    
}
