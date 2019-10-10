import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.List;

public class Tests {


    public static Group test(List<TextField> liste, String passeWord, String confirmation, boolean rb, int age,boolean accepter){
        Group warnings = new Group();
        int i = 0;
        for (TextField text : liste) {
            if (text.getText().equals("")) {
               warnings.getChildren().add(ajouterErreur("Veillez remplir correctement votre " + text.getPromptText().toLowerCase(),i));
                i++;
            }
        }
        if (!passeWord.equals(confirmation)) {
            warnings.getChildren().add(ajouterErreur("Les mots de passe ne correspondent pas, veuillez corriger cette erreur",i));
            i++;
        }
        if (!rb) {
            warnings.getChildren().add(ajouterErreur("Veuillez selectionner un genre",i));
            i++;
        }
        if (age==0) {
            warnings.getChildren().add(ajouterErreur("Veuillez selectionner votre age",i));
            i++;
        }
        if (!accepter) {
            warnings.getChildren().add(ajouterErreur("Vous devez accepter les conditions pour jouer",i));
            i++;
        }

        return warnings;
    }
    public static Group ajouterErreur(String nomErreur, int i){
        Group warnings = new Group();
        Label erreur = new Label(nomErreur);
        erreur.setTranslateY(10 + i * 15);
        erreur.setTextFill(Color.RED);
        warnings.getChildren().add(erreur);
        return warnings;
    }
}
