import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SubsciptionScreen {

    Group warnings = new Group();

    public SubsciptionScreen(List<Profil> profils, Stage window) {

        Group group = new Group();
        VBox vb = new VBox(20);
        Label avertissement = new Label("");

        //prenom
        Label preNom = new Label("Prénom");
        preNom.setTranslateY(10);
        TextField first = new TextField();
        first.setPromptText("Prénom");
        first.setTranslateY(30);

        //nom
        Label nom = new Label("Nom");
        nom.setTranslateY(60);
        TextField last = new TextField();
        last.setPromptText("Nom");
        last.setTranslateY(80);

        //nom d'utilisateur
        Label userName = new Label("Nom d'utilisateur");
        userName.setTranslateY(110);
        TextField user = new TextField();
        user.setPromptText("Nom d'utilisateur");
        user.setTranslateY(130);

        //mot de passe
        Label mdp = new Label("Mot de passe");
        mdp.setTranslateY(160);
        TextField passWord = new TextField();
        passWord.setPromptText("Mot de passe");
        passWord.setTranslateY(180);

        //confirmer mot de passe
        Label confirm = new Label("Confirmer mot de passe");
        confirm.setTranslateY(210);
        TextField checkPass = new TextField();
        checkPass.setPromptText("Confirmer mot de passe");
        checkPass.setTranslateY(230);

        //sexe
        Label sexe = new Label("Genre");
        sexe.setTranslateY(260);
        RadioButton homme = new RadioButton("Homme");
        RadioButton femme = new RadioButton("Femme");
        RadioButton autre = new RadioButton("Autre");
        ToggleGroup tg = new ToggleGroup();
        homme.setToggleGroup(tg);
        femme.setToggleGroup(tg);
        autre.setToggleGroup(tg);
        HBox hb = new HBox(15);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(homme, femme, autre);
        hb.setTranslateY(280);

        //age
        Label age = new Label("Âge");
        age.setTranslateY(310);
        Spinner<Integer> spinner = new Spinner(0, 100, 0);
        spinner.setTranslateY(330);

        //conditions d'utilisation
        CheckBox accepter = new CheckBox();
        accepter.setTranslateY(370);
        Label condition = new Label("J'accepte les conditions d'utilisations");
        condition.setTranslateY(370);
        condition.setTranslateX(20);



        //inscription
        Button sinscrire = new Button("S'inscrire!");
        sinscrire.setTranslateY(400);
        sinscrire.setOnAction((n) -> {
            List<TextField> listeTest = Arrays.asList(first, last, user, passWord);
            Group tests = test(listeTest, passWord.getText(), checkPass.getText(), cocher(homme,femme,autre),spinner.getValue(),accepter.isSelected());
            boolean test = tests.getChildren().isEmpty();

            if (!test) {
                vb.getChildren().remove(1);
                vb.getChildren().addAll(tests);
            }
            else {
                profils.add(new Profil(user.getText(), passWord.getText(), selectionner(tg,cocher(homme,femme,autre)), Integer.toString(spinner.getValue()), first.getText(), last.getText()));
                Save.sauvegarder(profils);
                retourConnexion(profils, window);
            }
        });

        //effacer
        Button clear = new Button("Effacer");
        clear.setTranslateY(400);
        clear.setTranslateX(80);
        clear.setOnAction((n) -> {
            List<TextField> text = Arrays.asList(first, last, user, passWord, checkPass);
            for (TextField remplissage : text) {
                remplissage.setText("");
            }
            tg.getSelectedToggle().setSelected(false);

        });

        //retour
        Button retour = new Button("Retour");
        retour.setTranslateY(400);
        retour.setTranslateX(145);
        retour.setOnAction((n) -> retourConnexion(profils, window));

        //affichage
        group.getChildren().addAll(preNom, first, nom, last, userName, user, mdp, passWord, confirm, checkPass, sinscrire, clear, retour, hb, sexe, spinner, age,accepter,condition);
        vb.getChildren().addAll(group, avertissement);
        vb.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(vb, 400, 600);
        window.setScene(scene);
        window.show();
    }

    public Group test(List<TextField> liste, String passeWord, String confirmation, boolean rb, int age, boolean accepter) {
        warnings.getChildren().clear();
        warnings = Tests.test(liste, passeWord, confirmation, rb, age,accepter);
        return warnings;
    }

    public void retourConnexion(List<Profil> profils, Stage window) {
        VBox vB = new VBox();
        vB.setAlignment(Pos.CENTER);
        vB.getChildren().addAll(new ConnectionScreen(profils, window));
        Scene scene = new Scene(vB, 400, 500);
        window.setScene(scene);
    }

    public String selectionner(ToggleGroup tg, boolean cocher) {
        RadioButton selectedRadioButton = new RadioButton();
        if (cocher){ selectedRadioButton = (RadioButton) tg.getSelectedToggle();}
        return selectedRadioButton.getText();
    }
    public Boolean cocher(RadioButton rb1, RadioButton rb2 , RadioButton rb3){
        Boolean cocher = false;
        if (rb1.isSelected() || rb2.isSelected() || rb3.isSelected()){cocher=true;}
        return cocher;
    }



}
