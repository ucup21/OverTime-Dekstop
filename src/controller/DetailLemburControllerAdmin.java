/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DetailLemburDAO;
import dao.JenisLemburDAO;
import dao.PegawaiMiiDAO;
import entities.DetailLembur;
import entities.JenisLembur;
import entities.PegawaiMii;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class DetailLemburControllerAdmin {

    private DetailLemburDAO dAO;
    private JenisLemburDAO lemburDAO;
    private PegawaiMiiDAO pmdao;

    public DetailLemburControllerAdmin() {
        this.dAO = new DetailLemburDAO();
        this.pmdao = new PegawaiMiiDAO();
        this.lemburDAO = new JenisLemburDAO();
    }

    public boolean insert(String kdDetailLembur, String kdLembur, String nip, String tgl, boolean isSave) {
        DetailLembur detailLembur = new DetailLembur();
        detailLembur.setKdDetailLembur(kdDetailLembur);
        String[] kId = kdLembur.split(" ");
        detailLembur.setKdLembur((JenisLembur) lemburDAO.getById(kId[0]));
        String[] pNip = nip.split(" ");
        detailLembur.setNip((PegawaiMii) pmdao.getById(pNip[0]));
        detailLembur.setTglLembur((new java.sql.Date(new Long(tgl))));
        if (isSave) {
            return dAO.insert(detailLembur);
        }
        return dAO.update(detailLembur);
    }

    public boolean delete(String id) {
        return dAO.delete(id);
    }

    public List<String> bindingALL(JTable table,
            String[] header) {
        return bindingTables(table, header,
                dAO.getAll());
    }

    public List<String> bindingSearch(JTable table,
            String[] header, String category,
            String cari) {
        String search = cari;
        if (category.equalsIgnoreCase("kdLembur")) {
            JenisLembur jenisLembur = (JenisLembur) lemburDAO.search("lamaLembur", cari).get(0);
            search = jenisLembur.getKdLembur();
        } else if (category.equalsIgnoreCase("nip")) {
            List<Object> pegawai = pmdao.search("nama", cari);
            PegawaiMii pegawaiMii1 = (PegawaiMii) pegawai.get(0);
            search = pegawaiMii1.getNip().toString();
        }
        return bindingTables(table, header, dAO.search(category, search));
    }

    private List<String> bindingTables(JTable table, String[] header, List<Object> datas) {
        List<String> dataLembur = new ArrayList<>();
        DefaultTableModel model = new DefaultTableModel(header, 0);
        int i = 1;
        for (Object data : datas) {
            DetailLembur d = (DetailLembur) data;
            dataLembur.add(d.getKdLembur().getKdLembur() + " - " + d.getKdLembur().getLamaLembur() +
                    ";" + d.getNip().getNip() + " - " + d.getNip().getNama());

            Object[] data1 = {
                i++,
                d.getKdDetailLembur(),
                d.getKdLembur().getLamaLembur(),
                d.getNip().getNama(),
                d.getTglLembur()
            };
            model.addRow(data1);
        }
        table.setModel(model);
        return dataLembur;
    }

    public void loadLembur(JComboBox jComboBox) {
        jComboBox.addItem(" - ");
        lemburDAO.getAll().stream().map((object) -> (JenisLembur) object).forEachOrdered((jenisLembur) -> {
            jComboBox.addItem(jenisLembur.getKdLembur() + " - "
                    + jenisLembur.getLamaLembur());
        });
    }

    public void loadPegawai(JComboBox jComboBox) {
        jComboBox.addItem(" - ");
        pmdao.getAll().stream().map((object) -> (PegawaiMii) object).forEachOrdered((pegawai) -> {
            jComboBox.addItem(pegawai.getNip() + " - "
                    + pegawai.getNama());
        });
    }
}
