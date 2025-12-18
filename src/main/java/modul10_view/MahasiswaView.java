package modul10_view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class MahasiswaView extends JFrame {
    private JTextField txtNama, txtNIM, txtJurusan, txtCari;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    private JTable tableMahasiswa;
    private DefaultTableModel model;

    public MahasiswaView() {
        // Setup frame
        setTitle("Aplikasi CRUD Mahasiswa JDBC (MVC)");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. Panel form
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        // Panel tombol
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        // ---BAGIAN PENCARIAN ---
        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCari.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        txtCari = new JTextField(20);
        btnCari = new JButton("Cari");
        panelCari.add(new JLabel("Cari data:"));
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        JPanel panelKontrol = new JPanel(new GridLayout(2, 1));
        panelKontrol.add(panelTombol);
        panelKontrol.add(panelCari);
        panelAtas.add(panelKontrol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        // 2. Tabel data
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        add(scrollPane, BorderLayout.CENTER);
    }

    public String getNama() {
        return txtNama.getText();
    }

    public String getNim() {
        return txtNIM.getText();
    }

    public String getJurusan() {
        return txtJurusan.getText();
    }

    public String getCariInput() {
        return txtCari.getText();
    }

    public void setNama(String nama) {
        txtNama.setText(nama);
    }

    public void setNim(String nim) {
        txtNIM.setText(nim);
    }

    public void setJurusan(String jurusan) {
        txtJurusan.setText(jurusan);
    }

    public void setTableModel(DefaultTableModel model) {
        this.model = model;
        tableMahasiswa.setModel(model);
    }

    public DefaultTableModel getTableModel() {
        return model;
    }

    public void addSimpanListener(ActionListener listener) {
        btnSimpan.addActionListener(listener);
    }

    public void addEditListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void addHapusListener(ActionListener listener) {
        btnHapus.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }

    public void addCariListener(ActionListener listener) {
        btnCari.addActionListener(listener);
    }

    public void addTableMouseListener(MouseAdapter adapter) {
        tableMahasiswa.addMouseListener(adapter);
    }

    public JTable getTable() {
        return tableMahasiswa;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
