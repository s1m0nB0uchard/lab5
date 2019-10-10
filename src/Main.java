import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage window){

        List<Profil> profils=Profil.undoString();
        Scene scene;
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        ConnectionScreen cs = new ConnectionScreen(profils,window);
        vBox.getChildren().add(cs);
        scene = new Scene(vBox,400,400);
        window.setScene(scene);
        window.show();







    }
}
