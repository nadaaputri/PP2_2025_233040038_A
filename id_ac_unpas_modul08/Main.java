package id_ac_unpas_modul08;

import id_ac_unpas_modul08_model.PersegiPanjangModel;
import id_ac_unpas_modul08_view.PersegiPanjangView;
import id_ac_unpas_modul08_controller.PersegiPanjangController;

public class Main {
    public static void main(String[] args) {
        // 1. Instansiasi Model
        PersegiPanjangModel model = new PersegiPanjangModel();

        // 2. Instansiasi View
        PersegiPanjangView view = new PersegiPanjangView();

        // 3. Instansiasi Controller (Hubungkan Model & View)
        PersegiPanjangController controller = new PersegiPanjangController(model, view);

        // 4. Tampilkan View
        view.setVisible(true);
    }
}
