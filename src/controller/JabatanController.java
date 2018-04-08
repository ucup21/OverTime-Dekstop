/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.JabatanDAO;
import entities.Jabatan;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class JabatanController {

    private final JabatanDAO jdao;

    public JabatanController() {
        this.jdao = new JabatanDAO();
    }

    public void bindingTable(JTable table, String[] header, List<Object> datas) {
        DefaultTableModel model = new DefaultTableModel(header, 0);
        int i = 1;
        for (Object data : datas) {
            Jabatan jabatan = (Jabatan) data;
            Object[] data1 = {
                i++,
                jabatan.getKdJabatan(),
                jabatan.getNamaJabatan()
            };
            model.addRow(data1);
        }
        table.setModel(model);
    }

    public void bindingAll(JTable table, String[] header) {
        bindingTable(table, header, jdao.getAll());
    }
    
    public void bindingSearch(JTable table, String[] header, String category, String cari) {
       bindingTable(table, header, jdao.search(category, cari));
    }
    
    public boolean save(String kdJabatan, String namaJabatan, boolean isSave) {
        Jabatan jabatan = new Jabatan();
        jabatan.setKdJabatan(kdJabatan);
        jabatan.setNamaJabatan(namaJabatan);
        if (isSave) {
            return jdao.insert(jabatan);
        }
        return jdao.insert(jabatan);
    }
    
     public boolean delete(String kdJabatan) {
        return jdao.delete(kdJabatan);
    }

}
