import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Save {


    public static void sauvegarder(List<Profil> profil) {

        try (PrintWriter writer = new PrintWriter(new File("./Saves/profils.csv"))) {
            writer.write(Profil.readyToSave(profil));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void file() {
        String nomDossier = "./Saves";
        Path path1 = Paths.get(nomDossier);
        File dossier = new File(nomDossier);
        try {
            if (!dossier.exists()) {
                Files.createDirectory(path1);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

    public static ArrayList<String> chargerProfils() {
        file();
        ArrayList<String> res = new ArrayList<>();
        File file = new File("./Saves/Profils.csv");
        if (file.exists()) {
            String csvFile = "./Saves/Profils.csv";
            String result = "yo";

            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                while ((result = br.readLine()) != null) {
                    res.add(result);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;

    }

}
