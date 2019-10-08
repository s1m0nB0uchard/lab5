import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Progress {


    public Progress(Stage window){
        ProgressIndicator indicateur=new ProgressIndicator();
        Label texte = new Label("Chargement du contenu...");
        VBox vb = new VBox(indicateur,texte);
        vb.setAlignment(Pos.CENTER);
        Scene prog = new Scene(vb,200,200);
        window.setScene(prog);
        window.show();
    }
}
