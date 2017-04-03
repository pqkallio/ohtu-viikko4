package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan
        String studentNr = "012843064";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        String[] weeks = bodyText.split("\"a1\":");
        for (int i = 0; i < subs.length; i++) {
            parseExercises(subs[i], weeks[i + 1]);
        }
        
//        System.out.println("Oliot:");

        System.out.println("opiskelijanumero: " + subs[0].getStudent_number() + "\n");
        int exercisesMadeInTotal = 0;
        int hoursSpentInTotal = 0;
        for (Submission submission : subs) {
            exercisesMadeInTotal += submission.doneExercises();
            hoursSpentInTotal += submission.getHours();
            System.out.println(" " + submission);
        }
        System.out.println("\nyhteensä: " + exercisesMadeInTotal + " tehtävää "
                            + hoursSpentInTotal + " tuntia");
    }

    private static void parseExercises(Submission sub, String week) {
        int maxExercises = 0;
        boolean[] exercisesDone = new boolean[21];
        String splitString = week.split(",\"created_at\"")[0];
        String[] exercises = splitString.split(",");
        for (int i = 0; i < exercises.length; i++) {
            String[] exerciseDone = exercises[i].split(":");
            String isDone = exerciseDone[exerciseDone.length - 1];
            if (!isDone.equals("null")) {
                maxExercises++;
                exercisesDone[i] = isDone.equals("true");
            } else {
                break;
            }
        }
        sub.setExercisesMade(exercisesDone);
        sub.setMaxExercises(maxExercises);
    }
}