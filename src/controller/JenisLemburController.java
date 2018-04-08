/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.JenisLemburDAO;
import entities.JenisLembur;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class JenisLemburController {
    private final JenisLemburDAO lemburDAO;

    public JenisLemburController() {
        this.lemburDAO = new JenisLemburDAO();
    }
    
    public boolean insert(String kdJenisLembur, Short lamaLembur, boolean isSave){
        JenisLembur jenisLembur = new JenisLembur();
        jenisLembur.setKdLembur(kdJenisLembur);
        jenisLembur.setLamaLembur(Short.valueOf(lamaLembur));
        if (isSave)return lemburDAO.insert(jenisLembur);
        return lemburDAO.update(jenisLembur);
    }
    
    public boolean delete(String id){
        return lemburDAO.delete(id);
    }
    
    public void bindingALL(JTable table, String[] header) {
        bindingTables(table, header, lemburDAO.getAll());
    }
    
    public void bindingSearch(JTable table, String[] header, String category, String cari) {
        bindingTables(table, header, lemburDAO.search(category, cari));
    }

    private void bindingTables(JTable table, String[] header, List<Object> datas) {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        int i = 1;
        for (Object data : datas) {
            JenisLembur d = (JenisLembur) data;
            Object[] data1 = {
                i++,
                d.getKdLembur(),
                d.getLamaLembur(),
            };
            model.addRow(data1);
        }
        table.setModel(model);
    }
}
