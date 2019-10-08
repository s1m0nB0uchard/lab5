
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Profil {

    String user, passWord, save, nom, prenom, genre, age;

    public Profil(String _user, String _password) {
        user = _user;
        passWord = _password;

    }

    public String readyToSave() {
        List<String> liste = Arrays.asList(nom, prenom, user, passWord, genre, age);
        return save = liste.stream().collect(Collectors.joining(", "));
    }

    public Boolean exists(String _user, String _passWord) {
        Boolean bool = false;
        if (_user.equals(user) && _passWord.equals(passWord)) {bool = true;}
        return bool;
    }
    public String user(){
        return user;
    }


}
