package id_ac_unpas_modul09;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Latihan3_4 extends JFrame {

    // Komponen UI
    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText, btnAddText;
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;

    public Latihan3_4() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inisialisasi Komponen
        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan Text");
        btnSaveBinary = new JButton("Simpan Config (Binary)");
        btnLoadBinary = new JButton("Muat Config (Binary)");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        btnAddText = new JButton("Tambah Text");
        buttonPanel.add(btnAddText);
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);

        // Layout
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Handling ---

        // 1. MEMBACA FILE TEKS (Text Stream)
        btnOpenText.addActionListener(e -> bukaFileTeks());

        // 2. MENULIS FILE TEKS (Text Stream)
        btnSaveText.addActionListener(e -> simpanFileTeks());

        // 3. MENULIS FILE BINARY (Byte Stream)
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());

        // 4. MEMBACA FILE BINARY (Byte Stream)
        btnLoadBinary.addActionListener(e -> muatConfigBinary());

        // Event untuk tombol tambah text
        btnAddText.addActionListener(e -> tambahTextKeFile());

        // Muat file otomatis saat aplikasi dijalankan
        muatLastNoteseOtomatis();
    }

    // Method untuk memuat file otomatis saat aplikasi dijalankan
    public void muatLastNoteseOtomatis() {
        File fileNotes = new File("last_notes.txt");
        if (fileNotes.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileNotes))) {
                String line;
                textArea.setText("");
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            }
        }
    }

    // Contoh: Membaca File Teks dengan Try-Catch-Finally Konvensional
    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null; // Deklarasi di luar try agar bisa diakses di finally

            try {
                // Membuka stream
                reader = new BufferedReader(new FileReader(file));
                textArea.setText(""); // Kosongkan area

                String line;
                // Baca baris demi baris
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                JOptionPane.showMessageDialog(this, "File berhasil dimuat!");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal membaca file: " + ex.getMessage());
            } finally {
                // Blok Finally: Selalu dijalankan untuk menutup resource
                try {
                    if (reader != null) {
                        reader.close(); // PENTING: Menutup stream
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Contoh: Menulis File Teks menggunakan Try-With-Resources (Lebih Modern)
    private void simpanFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Try-with-resources otomatis menutup stream tanpa blok finally manual
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan file: " + ex.getMessage());
            }
        }
    }

    // Method untuk menambah text
    private void tambahTextKeFile() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.newLine(); // Menambahkan baris baru setelah menulis
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Text berhasil ditambahkan ke file!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan text ke file: " + ex.getMessage());
            }
        }
    }

    // Contoh: Menulis Binary (Menyimpan ukuran font saat ini ke file .bin)
    private void simpanConfigBinary() {
        UserConfig config = new UserConfig();
        config.setUsername("User123");
        config.setFontsize(textArea.getFont().getSize());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("config.bin"))) {
            // Menyimpan objek UserConfig
            oos.writeObject(config);
            JOptionPane.showMessageDialog(this, "Object Config berhasil disimpan!\n" + config.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan binary: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Contoh: Membaca Binary (Mengambil ukuran font dari file .bin)
    private void muatConfigBinary() {

        // Gunakan ObjectInputStream
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("config.bin"))) {

            // 1. Baca Objek dan lakukan CASTING (Latihan 3)
            UserConfig config = (UserConfig) ois.readObject();

            // 2. Terapkan pengaturan yang dimuat ke Aplikasi GUI
            textArea.setFont(new Font("Monospaced", Font.PLAIN, config.getFontsize()));

            JOptionPane.showMessageDialog(this,
                    "Konfigurasi dimuat!\nUsername: " + config.getUsername() +
                            "\nFont Size: " + config.getFontsize());

        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Gagal membaca binary: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Latihan3_4().setVisible(true);
        });
    }
}
