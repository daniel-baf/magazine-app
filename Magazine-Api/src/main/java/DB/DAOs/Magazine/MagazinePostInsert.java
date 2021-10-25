/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB.DAOs.Magazine;

import DB.DBConnection;
import DB.Domain.Magazine.MagazinePost;
import DB.GeneralPaths;
import BackendUtilities.FileWriterCP;
import BackendUtilities.Parser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jefemayoneso
 */
public class MagazinePostInsert {

    private final String SQL_INSERT_POST = "INSERT INTO Post (name,date,pdf,magazine) VALUES (?, ?, ?, ?)";

    public int insert(MagazinePost post, String absolutePath) {
        int result = 0;
        try {
            // autocomit false
            DBConnection.getConnection().setAutoCommit(false);
            // configure
            PreparedStatement ps = DB.DBConnection.getConnection().prepareStatement(
                    SQL_INSERT_POST, PreparedStatement.RETURN_GENERATED_KEYS);
            post.setPdfNamePath(GeneralPaths.FILES_PDF_PATH.getMessage() + post.getMagazine());
            configPSInsert(ps, post);
            // save to DB and create file
            if (ps.executeUpdate() != 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) { // if inserted
                    post.setId(rs.getInt(1));
                    FileWriterCP fileWriter = new FileWriterCP();
                    if (fileWriter.write(post.getPdfPart(), post.getPdfNamePath(), absolutePath)) {
                        result = new MagazinePostUpdate().update(post);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error trying to regist a magazine post at [MagazinePostInsert] " + e.getMessage());
        } finally {
            try {
                if (result == 0) {
                    DBConnection.getConnection().rollback();
                }
                DBConnection.getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Error while rollabck at [MagazinePostInsert] " + ex.getMessage());
            }
        }
        return result;
    }

    private void configPSInsert(PreparedStatement ps, MagazinePost post) throws SQLException {
        ps.setString(1, post.getTitle());
        ps.setDate(2, new Parser().toDate(post.getDate()));
        ps.setString(3, GeneralPaths.FILES_PDF_PATH.getMessage());
        ps.setString(4, post.getMagazine());

    }
}
