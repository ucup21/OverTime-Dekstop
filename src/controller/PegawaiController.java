/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.JabatanDAO;
import dao.PegawaiMiiDAO;
import entities.Jabatan;
import entities.PegawaiMii;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class PegawaiController {
    private final PegawaiMiiDAO miiDAO;
    private final JabatanDAO jdao;

    public PegawaiController() {
        this.miiDAO = new PegawaiMiiDAO();
        this.jdao = new JabatanDAO();
    }

    public void bindingTable(JTable table, String[] header, List<Object> datas) {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        int i = 1;
        for (Object data : datas) {
            PegawaiMii mii = (PegawaiMii) data;
            Object[] data1 = {
                i++,
                mii.getNip(),
                mii.getKdJabatan().getNamaJabatan(),
                mii.getNama(),
                mii.getJk(),
                mii.getAlamat(),
                mii.getTglLahir(),
                mii.getTmptLahir()
            };
            model.addRow(data1);
        }
        table.setModel(model);
    }

    public void bindingAll(JTable table, String[] header) {
        bindingTable(table, header, miiDAO.getAll());
    }
    
    public void bindingSearch(JTable table, String[] header, String category, String cari) {
       bindingTable(table, header, miiDAO.search(category, cari));
    }
    
    public boolean save(Integer nip, String kdJabatan, String nama, String jk,
            String alamat, Date tgllahir, String tmptLahir, boolean isSave) {
        PegawaiMii mii = new PegawaiMii();
        String[] jId = kdJabatan.split(" ");
        mii.setKdJabatan((Jabatan) jdao.getById(jId[0]));
        if (isSave) {
            return miiDAO.insert(mii);
        }
        return miiDAO.update(mii);
    }
    
     public boolean delete(String kdJabatan) {
        return miiDAO.delete(kdJabatan);
    }

}
