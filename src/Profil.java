
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Profil {

    String user, passWord, nom, prenom, genre, age;

    public Profil(String user, String password, String genre, String age, String nom, String prenom) {
        this.user = user;
        this.passWord = password;
        this.genre = genre;
        this.age = age;
        this.nom = nom;
        this.prenom = prenom;
    }

    public static String readyToSave(List<Profil> profils) {
        String phraseComplete = "";
        String phraseDuProfil = "";
        int i = 0;
        for (Profil profil : profils) {
            List<String> liste = Arrays.asList(profil.user, profil.passWord, profil.genre, profil.age, profil.nom, profil.prenom);
            phraseDuProfil = liste.stream().collect(Collectors.joining(","));

            if (i == 0) {
                phraseComplete = phraseDuProfil;
                i++;
            } else {
                phraseComplete = phraseComplete + "\n" + phraseDuProfil;
            }
        }
        return phraseComplete;
    }

    public static List<Profil> undoString() {
        List<Profil> liste = new ArrayList<>();
        if (Save.chargerProfils().size()!=0) {
            String phrase = "";
            for (String phrasePartielle : Save.chargerProfils()) {

                if (Save.chargerProfils().size() > 1) {
                    phrase = phrase + "\n" + phrasePartielle;
                } else {
                    phrase = phrasePartielle;
                }
            }
            System.out.println(phrase);

            String tabContenantProfils[] = phrase.split("\n");
            String tabInfo[];

            for (int i = 1; i < tabContenantProfils.length; i++) {
                tabInfo = tabContenantProfils[i].split(",");
                System.out.println(tabInfo[1]);
                liste.add(new Profil(tabInfo[0], tabInfo[1], tabInfo[2], tabInfo[3], tabInfo[4], tabInfo[5]));
            }
        }
        return liste;

    }

    public Boolean exists(String _user, String _passWord) {
        Boolean bool = false;
        if (_user.equals(user) && _passWord.equals(passWord)) {
            bool = true;
        }
        return bool;
    }

    public String getPassWord() {
        return passWord;
    }
}
