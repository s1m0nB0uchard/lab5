import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ConnectionScreen extends Group {

    public ConnectionScreen(List<Profil> profils, Stage window) {


        //username
        Label userName = new Label("Nom d'utilisateur");
        TextField user = new TextField();
        user.setPromptText("Nom d'utilisateur");
        user.setTranslateY(20);
        //password
        Label passWord = new Label("Mot de passe");
        passWord.setTranslateY(50);
        PasswordField pass = new PasswordField();
        pass.setPromptText("Mot de passe");
        pass.setTranslateY(70);
        //se connecter
        Button seConnecter = new Button("Se connecter");
        seConnecter.setTranslateY(120);
        seConnecter.setOnAction((n) -> {
            String rep = user.getText();
            String nip = pass.getText();
            if (profils.size() != 0) {
                for (Profil profil : profils) {
                    if (profil.exists(rep,nip)) {
                        new Progress(window);
                    } else {
                        Label erreur = new Label("La connection a échouée");
                        erreur.setTextFill(Color.RED);
                        erreur.setTranslateY(165);
                        getChildren().add(erreur);
                        user.setText("");
                        pass.setText("");
                    }
                }
            } else {
                System.out.println("profils vide");
                Label erreur = new Label("La connection a échouée");
                erreur.setTextFill(Color.RED);
                erreur.setTranslateY(165);
                getChildren().add(erreur);
                user.setText("");
                pass.setText("");
            }
        });

        //S'inscrire
        Button subscribe = new Button("S'inscrire");
        subscribe.setTranslateY(120);
        subscribe.setTranslateX(100);
        subscribe.setOnAction((n) -> new SubsciptionScreen(profils, window));

        getChildren().addAll(userName, user, passWord, pass, seConnecter, subscribe);
    }


}
