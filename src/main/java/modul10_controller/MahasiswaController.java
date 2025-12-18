package modul10_controller;

import modul10_model.Mahasiswa;
import modul10_model.MahasiswaModel;
import modul10_view.MahasiswaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class MahasiswaController {
    private MahasiswaModel model;
    private MahasiswaView view;

    public MahasiswaController(MahasiswaModel model, MahasiswaView view) {
        this.model = model;
        this.view = view;

        // Initialize listeners
        this.view.addSimpanListener(new SimpanListener());
        this.view.addEditListener(new EditListener());
        this.view.addHapusListener(new HapusListener());
        this.view.addClearListener(new ClearListener());
        this.view.addCariListener(new CariListener());
        this.view.addTableMouseListener(new TableMouseListener());

        loadData();
    }

    private void loadData() {
        DefaultTableModel tableModel = view.getTableModel();
        tableModel.setRowCount(0);

        List<Mahasiswa> list = model.getAll();
        int no = 1;
        for (Mahasiswa mhs : list) {
            tableModel.addRow(new Object[] {
                    no++,
                    mhs.getNama(),
                    mhs.getNim(),
                    mhs.getJurusan()
            });
        }
    }

    private void kosongkanForm() {
        view.setNama("");
        view.setNim("");
        view.setJurusan("");
    }

    class SimpanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = view.getNama();
            String nim = view.getNim();
            String jurusan = view.getJurusan();

            if (nama.isEmpty() || nim.isEmpty()) {
                view.showMessage("Data tidak boleh kosong!");
                return;
            }

            if (model.checkNimExists(nim)) {
                view.showMessage("NIM " + nim + " sudah ada!");
                return;
            }

            try {
                model.insert(new Mahasiswa(nim, nama, jurusan));
                view.showMessage("Data berhasil disimpan");
                loadData();
                kosongkanForm();
            } catch (Exception ex) {
                view.showMessage("Gagal simpan: " + ex.getMessage());
            }
        }
    }

    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = view.getNama();
            String nim = view.getNim();
            String jurusan = view.getJurusan();

            try {
                model.update(new Mahasiswa(nim, nama, jurusan));
                view.showMessage("Data berhasil diubah");
                loadData();
                kosongkanForm();
            } catch (Exception ex) {
                view.showMessage("Gagal edit: " + ex.getMessage());
            }
        }
    }

    class HapusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nim = view.getNim();
            try {
                model.delete(nim);
                view.showMessage("Data berhasil dihapus");
                loadData();
                kosongkanForm();
            } catch (Exception ex) {
                view.showMessage("Gagal hapus: " + ex.getMessage());
            }
        }
    }

    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            kosongkanForm();
        }
    }

    class CariListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = view.getCariInput();
            DefaultTableModel tableModel = view.getTableModel();
            tableModel.setRowCount(0);

            List<Mahasiswa> list = model.search(keyword);
            int no = 1;
            for (Mahasiswa mhs : list) {
                tableModel.addRow(new Object[] {
                        no++,
                        mhs.getNama(),
                        mhs.getNim(),
                        mhs.getJurusan()
                });
            }
        }
    }

    class TableMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTable().getSelectedRow();
            DefaultTableModel tableModel = view.getTableModel();

            view.setNama(tableModel.getValueAt(row, 1).toString());
            view.setNim(tableModel.getValueAt(row, 2).toString());
            view.setJurusan(tableModel.getValueAt(row, 3).toString());
        }
    }
}
