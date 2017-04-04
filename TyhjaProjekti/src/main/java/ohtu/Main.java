package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
        String courseUrl = "https://ohtustats2017.herokuapp.com/courses/1.json";
        
        String submissionsText = Request.Get(url).execute().returnContent().asString();
        String courseText = Request.Get(courseUrl).execute().returnContent().asString();

        Submission[] subs = getSubmissions(submissionsText, courseText);

        printCourseInfo(subs);
    }

    private static void parseExercises(Submission sub, JsonObject submission, JsonObject course) {
        boolean[] exercisesDone = new boolean[21];
        for (int j = 1; j < 22; j++) {
            String key = "a" + j;
            if (submission.get(key).isJsonNull()) {
                break;
            }
            exercisesDone[j] = submission.get(key).getAsBoolean();
        }
        sub.setExercisesMade(exercisesDone);
        sub.setMaxExercises(course.get("week" + sub.getWeek()).getAsInt());
    }

    private static Submission[] getSubmissions(String submissionsText, String courseText) {
        Gson submissionMapper = new Gson();
        Submission[] subs = submissionMapper.fromJson(submissionsText, Submission[].class);
        
        Gson courseMapper = new Gson();
        Course course = courseMapper.fromJson(courseText, Course.class);
        
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(submissionsText).getAsJsonArray();
        JsonObject courseObject = parser.parse(courseText).getAsJsonObject();
        
        for (int i = 0; i < jsonArray.size(); i++) {
            Submission submission = subs[i];
            JsonObject submissionObject = jsonArray.get(i).getAsJsonObject();
            parseExercises(submission, submissionObject, courseObject);
            submission.setCourse(course);
        }
        
        return subs;
    }

    private static void printCourseInfo(Submission[] subs) {
        System.out.println(subs[0].getCourse() + "\n");
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
}