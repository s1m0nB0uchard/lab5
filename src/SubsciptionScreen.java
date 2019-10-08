import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsciptionScreen {

    Group warnings = new Group();

    public SubsciptionScreen(List<Profil> profils, Stage window) {

        Group group = new Group();
        VBox vb = new VBox(20);
        Label avertissement = new Label("");


        Label preNom = new Label("Prénom");
        preNom.setTranslateY(10);
        TextField first = new TextField();
        first.setPromptText("Prénom");
        first.setTranslateY(30);

        Label nom = new Label("Nom");
        nom.setTranslateY(60);
        TextField last = new TextField();
        last.setPromptText("Nom");
        last.setTranslateY(80);

        Label userName = new Label("Nom d'utilisateur");
        userName.setTranslateY(110);
        TextField user = new TextField();
        user.setPromptText("Nom d'utilisateur");
        user.setTranslateY(130);


        Label mdp = new Label("Mot de passe");
        mdp.setTranslateY(160);
        TextField passWord = new TextField();
        passWord.setPromptText("Mot de passe");
        passWord.setTranslateY(180);


        Label confirm = new Label("Confirmer mot de passe");
        confirm.setTranslateY(210);
        TextField checkPass = new TextField();
        checkPass.setPromptText("Confirmer mot de passe");
        checkPass.setTranslateY(230);


        Button sinscrire = new Button("S'inscrire!");
        sinscrire.setTranslateY(290);
        sinscrire.setOnAction((n) -> {


            if (!passWord.getText().equals(checkPass.getText()) || !test(Arrays.asList(first, last, user, passWord), passWord.getText(), checkPass.getText()).getChildren().isEmpty()) {
                if (!test(Arrays.asList(first, last, user, passWord), passWord.getText(), checkPass.getText()).getChildren().isEmpty()) {
                    vb.getChildren().remove(1);
                    vb.getChildren().addAll(test(Arrays.asList(first, last, user, passWord), passWord.getText(), checkPass.getText()));
                }
            } else if (test(Arrays.asList(first, last, user, passWord), passWord.getText(), checkPass.getText()).getChildren().isEmpty() && passWord.getText().equals(checkPass.getText())) {
                profils.add(new Profil(user.getText(), passWord.getText()));
                retourConnexion(profils, window);
            }

        });

        Button clear = new Button("Effacer");
        clear.setTranslateY(290);
        clear.setTranslateX(80);
        clear.setOnAction((n) -> {
            List<TextField> text = Arrays.asList(first, last, user, passWord, checkPass);
            for (TextField remplissage : text) {
                remplissage.setText("");
            }

        });

        Button retour = new Button("Retour");
        retour.setTranslateY(290);
        retour.setTranslateX(145);
        retour.setOnAction((n) -> {
            retourConnexion(profils, window);
        });


        group.getChildren().addAll(preNom, first, nom, last, userName, user, mdp, passWord, confirm, checkPass, sinscrire, clear, retour);
        vb.getChildren().addAll(group, avertissement);
        vb.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(vb, 400, 600);
        window.setScene(scene);
        window.show();

    }

    public Group test(List<TextField> liste, String passeWord, String confirmation) {

        warnings.getChildren().clear();
        int i = 0;
        for (TextField text : liste) {
            if (text.getText().equals("")) {
                Label erreur = new Label("Veillez remplir correctement votre " + text.getPromptText().toLowerCase());
                erreur.setTranslateY(10 + i * 15);
                erreur.setTextFill(Color.RED);

                warnings.getChildren().addAll(erreur);
                i++;
            }
        }
        if (!passeWord.equals(confirmation)) {
            Label erreur = new Label("Les mots de passe ne correspondent pas, veuillez corriger cette erreur");
            erreur.setTranslateY(10 + i * 15);
            erreur.setTextFill(Color.RED);
            warnings.getChildren().addAll(erreur);
            i++;
        }
        return warnings;
    }

    public void retourConnexion(List<Profil> profils, Stage window) {
        VBox vB = new VBox();
        vB.setAlignment(Pos.CENTER);
        vB.getChildren().addAll(new ConnectionScreen(profils, window));
        Scene scene = new Scene(vB, 400, 400);
        window.setScene(scene);
    }
}
