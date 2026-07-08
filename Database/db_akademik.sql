CREATE TABLE tbl_krs(

                        id_krs INT AUTO_INCREMENT PRIMARY KEY,

                        nim VARCHAR(20) NOT NULL,

                        kode_mk VARCHAR(20) NOT NULL,

                        FOREIGN KEY (nim)
                            REFERENCES tbl_mahasiswa(nim)
                            ON DELETE CASCADE,

                        FOREIGN KEY (kode_mk)
                            REFERENCES tbl_matakuliah(kode_mk)
                            ON DELETE CASCADE

);

CREATE TABLE tbl_nilai(

                          id_nilai INT AUTO_INCREMENT PRIMARY KEY,

                          id_krs INT NOT NULL,

                          nilai_angka DOUBLE,

                          nilai_huruf VARCHAR(2),

                          FOREIGN KEY(id_krs)
                              REFERENCES tbl_krs(id_krs)
                              ON DELETE CASCADE

);