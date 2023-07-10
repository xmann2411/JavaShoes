/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Article;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class ArticleRepository implements Repository<Article> {

    private static final String ID_ARTICLE = "IDArticle";
    private static final String TITLE = "Title";
    private static final String LINK = "Link";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String PUBLISHED_DATE = "PublishedDate";

    private static final String CREATE_ARTICLE = "{ CALL createArticle (?,?,?,?,?,?) }";
    private static final String UPDATE_ARTICLE = "{ CALL updateArticle (?,?,?,?,?,?) }";
    private static final String DELETE_ARTICLE = "{ CALL deleteArticle (?) }";
    private static final String SELECT_ARTICLE = "{ CALL selectArticle (?) }";
    private static final String SELECT_ARTICLES = "{ CALL selectArticles }";

    @Override
    public int create(Article article) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ARTICLE)) {

            stmt.setString("@" + TITLE, article.getTitle());
            stmt.setString("@" + LINK, article.getLink());
            stmt.setString("@" + DESCRIPTION, article.getDescription());
            stmt.setString("@" + PICTURE_PATH, article.getPicturePath());
            stmt.setString("@" + PUBLISHED_DATE, article.getPublishedDate().format(Article.DATE_FORMATTER));
            stmt.registerOutParameter("@" + ID_ARTICLE, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt("@" + ID_ARTICLE);
        }
    }

    public void createAll(List<Article> articles) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ARTICLE)) {

            for (Article article : articles) {
                stmt.setString("@" + TITLE, article.getTitle());
                stmt.setString("@" + LINK, article.getLink());
                stmt.setString("@" + DESCRIPTION, article.getDescription());
                stmt.setString("@" + PICTURE_PATH, article.getPicturePath());
                stmt.setString("@" + PUBLISHED_DATE, article.getPublishedDate().format(Article.DATE_FORMATTER));
                stmt.registerOutParameter("@" + ID_ARTICLE, Types.INTEGER);

                stmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(Article data) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_ARTICLE)) {

            stmt.setString("@" + TITLE, data.getTitle());
            stmt.setString("@" + LINK, data.getLink());
            stmt.setString("@" + DESCRIPTION, data.getDescription());
            stmt.setString("@" + PICTURE_PATH, data.getPicturePath());
            stmt.setString("@" + PUBLISHED_DATE, data.getPublishedDate().format(Article.DATE_FORMATTER));
            stmt.setInt("@" + ID_ARTICLE, data.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ARTICLE)) {

            stmt.setInt("@" + ID_ARTICLE, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Article> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ARTICLE)) {

            stmt.setInt("@" + ID_ARTICLE, id);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Article(
                            rs.getInt(ID_ARTICLE),
                            rs.getString(TITLE),
                            rs.getString(LINK),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE).replace(" ", "T"), Article.DATE_FORMATTER)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Article> selectAll() throws Exception {
        List<Article> articles = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ARTICLES);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                articles.add(new Article(
                        rs.getInt(ID_ARTICLE),
                        rs.getString(TITLE),
                        rs.getString(LINK),
                        rs.getString(DESCRIPTION),
                        rs.getString(PICTURE_PATH),
                        LocalDateTime.parse(rs.getString(PUBLISHED_DATE).replace(" ", "T"), Article.DATE_FORMATTER)));
            }
        }
        return articles;
    }
}
