package id_ac_unpas_modul06;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Latihan2 {
    public static void main(String[] args) {
        // 1. Buat Frame (Window)
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // 2. Atur layout frame menjadi GridLayout 3 baris, 2 kolom
        // Kita juga bisa menambahkan jarak (gap) antar sel
        frame.setLayout(new GridLayout(3, 2, 5, 5)); // 3 baris, 2 kolom, 5px h-gap, 5px v-gap

        // 3. Tambahkan 6 komponen (3 + 2)
        JLabel celciusLabel = new JLabel("Celcius:");
        JTextField celciusField = new JTextField();
        JLabel fahrenheitLabel = new JLabel("Fahrenheit:");
        JTextField fahrenheitField = new JTextField();
        JButton buttonKonversi = new JButton("Konversi");

        // Field fahrenheit tidak bisa diedit
        fahrenheitField.setEditable(false);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Mengambil teks dari celciusField
                    // mengubah teks ke double
                    double celcius = Double.parseDouble(celciusField.getText());
                    // ubah teks ke Fahrenheit
                    double fahrenheit = (celcius * 9 / 5) + 32;
                    fahrenheitField.setText(String.format("%.2f", fahrenheit));
                } catch (NumberFormatException ex) {
                    fahrenheitField.setText("Input Salah");
                }
            }
        };

        // Daftarkan ActionListener ke buttonKonversi
        buttonKonversi.addActionListener(listener);

        // tambahkan semua komponen ke frame
        frame.add(celciusLabel);
        frame.add(celciusField);
        frame.add(fahrenheitLabel);
        frame.add(fahrenheitField);
        frame.add(buttonKonversi);

        // 4. Tampilkan frame
        frame.setVisible(true);

    }
}
