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
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Asus
 */
public class DetailLemburController {
    private DetailLemburDAO dAO;
    private JenisLemburDAO lemburDAO;
    private PegawaiMiiDAO pmdao;

    public DetailLemburController() {
        this.dAO = new DetailLemburDAO();
        this.pmdao = new PegawaiMiiDAO();
        this .lemburDAO = new JenisLemburDAO();
    }
    
    public boolean insert(String kdDetailLembur, String kdLembur, Long nip, String tgl, boolean isSave){
        DetailLembur detailLembur = new DetailLembur();
        detailLembur.setKdDetailLembur(kdDetailLembur);
        detailLembur.setKdLembur(new JenisLembur(kdLembur));
        detailLembur.setNip(new PegawaiMii(nip));
        detailLembur.setTglLembur(new java.sql.Date(new Long(tgl)));
        if (isSave)return dAO.insert(detailLembur);
        return dAO.update(detailLembur);
    }
    
    public boolean delete(String id){
        return dAO.delete(id);
    }
    
    public void bindingALL(JTable table, String[] header) {
        bindingTables(table, header, dAO.getAll());
    }
    
    public void bindingSearch(JTable table, String[] header, String category, String cari) {
        String search = cari;
        if (category.equalsIgnoreCase("kdLembur")) {
            JenisLembur jenisLembur = (JenisLembur) lemburDAO.search("lamaLembur", cari).get(0);
            search = jenisLembur.getKdLembur();
        } else if (category.equalsIgnoreCase("nip")) {
            List<Object> pegawai = pmdao.search("nama", cari);
            PegawaiMii pegawaiMii1 = (PegawaiMii) pegawai.get(0);
            search = pegawaiMii1.getNip().toString();
        }
        bindingTables(table, header, dAO.search(category, search));
    }

    private void bindingTables(JTable table, String[] header, List<Object> datas) {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        int i = 1;
        for (Object data : datas) {
            DetailLembur d = (DetailLembur) data;
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
    }
    
    
    
}
