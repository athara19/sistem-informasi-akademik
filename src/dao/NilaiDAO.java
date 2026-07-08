package dao;

import database.KoneksiDB;
import model.Nilai;
import model.NilaiView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NilaiDAO {

    public List<NilaiView> getDataNilai(){

        List<NilaiView> list=new ArrayList<>();

        try{

            Connection conn=KoneksiDB.getConnection();

            String sql="""
            SELECT

            k.nim,

            m.nama,

            k.kode_mk,

            mk.nama_mk,

            IFNULL(n.nilai,0) nilai

            FROM tbl_krs k

            JOIN tbl_mahasiswa m
            ON k.nim=m.nim

            JOIN tbl_matakuliah mk
            ON k.kode_mk=mk.kode_mk

            LEFT JOIN tbl_nilai n
            ON n.nim=k.nim
            AND n.kode_mk=k.kode_mk
            """;

            Statement st=conn.createStatement();

            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){

                NilaiView v=new NilaiView();

                v.setNim(rs.getString("nim"));
                v.setNama(rs.getString("nama"));
                v.setKodeMk(rs.getString("kode_mk"));
                v.setNamaMk(rs.getString("nama_mk"));
                v.setNilai(rs.getDouble("nilai"));

                list.add(v);

            }

        }catch(Exception e){

            e.printStackTrace();

        }

        return list;

    }
    public boolean simpanNilai(String nim, String kodeMk, double nilai){

        try{

            Connection conn = KoneksiDB.getConnection();

            String cekSql =
                    "SELECT * FROM tbl_nilai WHERE nim=? AND kode_mk=?";

            PreparedStatement cek =
                    conn.prepareStatement(cekSql);

            cek.setString(1, nim);
            cek.setString(2, kodeMk);

            ResultSet rs = cek.executeQuery();

            if(rs.next()){

                String update =
                        "UPDATE tbl_nilai SET nilai=? WHERE nim=? AND kode_mk=?";

                PreparedStatement ps =
                        conn.prepareStatement(update);

                ps.setDouble(1,nilai);
                ps.setString(2,nim);
                ps.setString(3,kodeMk);

                ps.executeUpdate();

            }else{

                String insert =
                        "INSERT INTO tbl_nilai(nim,kode_mk,nilai) VALUES(?,?,?)";

                PreparedStatement ps =
                        conn.prepareStatement(insert);

                ps.setString(1,nim);
                ps.setString(2,kodeMk);
                ps.setDouble(3,nilai);

                ps.executeUpdate();

            }

            return true;

        }catch(Exception e){

            e.printStackTrace();

            return false;

        }

    }


}