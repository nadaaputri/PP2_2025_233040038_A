package modul10_model;

import modul10.KoneksiDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaModel {

    public List<Mahasiswa> getAll() {
        List<Mahasiswa> list = new ArrayList<>();
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM mahasiswa");

            while (res.next()) {
                list.add(new Mahasiswa(
                        res.getString("nim"),
                        res.getString("nama"),
                        res.getString("jurusan")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(Mahasiswa mhs) throws SQLException {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, mhs.getNama());
        pst.setString(2, mhs.getNim());
        pst.setString(3, mhs.getJurusan());
        pst.execute();
    }

    public void update(Mahasiswa mhs) throws SQLException {
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, mhs.getNama());
        pst.setString(2, mhs.getJurusan());
        pst.setString(3, mhs.getNim());
        pst.executeUpdate();
    }

    public void delete(String nim) throws SQLException {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        pst.execute();
    }

    public boolean checkNimExists(String nim) {
        try {
            String sql = "SELECT count(*) FROM mahasiswa WHERE nim = ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nim);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                return res.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Mahasiswa> search(String keyword) {
        List<Mahasiswa> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ? OR nim LIKE ?";
            Connection conn = KoneksiDB.configDB();
            PreparedStatement pst = conn.prepareStatement(sql);
            String key = "%" + keyword + "%";
            pst.setString(1, key);
            pst.setString(2, key);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                list.add(new Mahasiswa(
                        res.getString("nim"),
                        res.getString("nama"),
                        res.getString("jurusan")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
