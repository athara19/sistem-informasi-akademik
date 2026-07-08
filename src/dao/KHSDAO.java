package dao;

import database.KoneksiDB;
import model.KHS;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KHSDAO {

    public List<KHS> getKHS(String nim){

        List<KHS> list = new ArrayList<>();

        try{

            Connection conn = KoneksiDB.getConnection();

            String sql = """
                    SELECT
                    mk.kode_mk,
                    mk.nama_mk,
                    mk.sks,
                    n.nilai
                    FROM tbl_nilai n
                    JOIN tbl_matakuliah mk
                    ON n.kode_mk = mk.kode_mk
                    WHERE n.nim=?
                    """;

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1,nim);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                KHS k = new KHS();

                k.setKodeMk(rs.getString("kode_mk"));
                k.setNamaMk(rs.getString("nama_mk"));
                k.setSks(rs.getInt("sks"));
                k.setNilai(rs.getDouble("nilai"));

                list.add(k);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return list;

    }

}