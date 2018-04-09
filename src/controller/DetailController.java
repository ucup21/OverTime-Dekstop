/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetailDAO;
import dao.JabatanDAO;
import dao.JenisLemburDAO;
import entities.Detail;
import entities.DetailPK;
import entities.Jabatan;
import entities.JenisLembur;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class DetailController {

    private final DetailDAO dao;
    private final JabatanDAO jdao;
    private final JenisLemburDAO jenisLemburDAO;
    DetailPK DetailPKController;

    public DetailController() {
        this.dao = new DetailDAO();
        this.jdao = new JabatanDAO();
        this.jenisLemburDAO = new JenisLemburDAO();
    }

    public void bindingTable(JTable table, String[] header, List<Object> datas) {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        int i = 1;
        for (Object data : datas) {
            Detail detail = (Detail) data;
            Object[] data1 = {
                i++,
                detail.getJabatan().getNamaJabatan(),
                detail.getJenisLembur().getLamaLembur(),
                detail.getTarif()

            };
            model.addRow(data1);
        }
        table.setModel(model);
    }

    public void bindingAll(JTable table, String[] header) {
        bindingTable(table, header, dao.getAll());
    }

    public void bindingSearch(JTable table, String[] header, String category, String search) {
        String cari = search;
        if (category.equalsIgnoreCase("kd_jabatan")) {
            Jabatan j = (Jabatan) jdao.search("namaJabatan", cari).get(0);
            cari = j.getKdJabatan();
        } else if (category.equalsIgnoreCase("kdLembur")) {
            JenisLembur jenisLembur = (JenisLembur) jenisLemburDAO.search("lamaLembur", cari).get(0);
            cari = jenisLembur.getKdLembur();
        }
        bindingTable(table, header, dao.search(category, cari));

    }

    public boolean save(String kdJabatan, String kdLembur, String tarif, boolean isSave) {
        Detail detail = new Detail();
//        detail.setJabatan(new Jabatan(kdJabatan));
//        detail.setJenisLembur(new JenisLembur(kdLembur));
        detail.setTarif(Long.valueOf(tarif));
        String[] jKd = kdJabatan.split(" ");
        detail.setJabatan((Jabatan) jdao.getById(jKd[0]));
        System.out.println(jKd[0]);
        String[] jnKd = kdLembur.split(" ");
        detail.setJenisLembur((JenisLembur) jenisLemburDAO.getById(jnKd[0]));
        detail.setDetailPK(new DetailPK(jKd[0], jnKd[0]));
        if (isSave) {
            return dao.insert(detail);
        }
        return dao.update(detail);

    }

    public boolean delete(String kdJabatan, String kdLembur) {
        DetailPK detailPK = new DetailPK();
        detailPK.setKdJabatan(kdJabatan);
        detailPK.setKdLembur(kdLembur);
        return dao.delete(detailPK);
    }

    public void loadJabatan(JComboBox jComboBox) {
        jdao.getAll().stream().map((object) -> (Jabatan) object).forEachOrdered((jab) -> {
            jComboBox.addItem(jab.getKdJabatan() + " - "
                    + jab.getNamaJabatan());
        });
    }
    public void loadLembur(JComboBox jComboBox) {
        jenisLemburDAO.getAll().stream().map((object) -> (JenisLembur) object).forEachOrdered((jen) -> {
            jComboBox.addItem(jen.getKdLembur()+ " - "
                    + jen.getLamaLembur());
        });
    }


}
