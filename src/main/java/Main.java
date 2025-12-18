import modul10_view.MahasiswaView;
import modul10_controller.MahasiswaController;
import modul10_model.MahasiswaModel;

public class Main {
    public static void main(String[] args) {
        // 1. Instansiasi Model
        MahasiswaModel model = new MahasiswaModel();

        // 2. Instansiasi View
        MahasiswaView view = new MahasiswaView();

        // 3. Instansiasi Controller (Hubungkan Model & View)
        MahasiswaController controller = new MahasiswaController(model, view);

        // 4. Tampilkan View
        view.setVisible(true);

    }
}
