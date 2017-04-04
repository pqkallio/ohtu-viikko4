package ohtu;

public class Submission {
    private String student_number;
    private int week;
    private int hours;
    private boolean[] exercisesMade;
    private int maxExercises;
    private Course course;
    
    public String getStudent_number() {
        return student_number;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    public int getHours() {
        return hours;
    }

    public int getWeek() {
        return week;
    }
    
    public void setMaxExercises(int maxExercises) {
        this.maxExercises = maxExercises;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setExercisesMade(boolean[] exercisesMade) {
        this.exercisesMade = exercisesMade;
    }
    
    public int doneExercises() {
        int doneExercisesN = 0;
        for (int i = 0; i < exercisesMade.length; i++) {
            if (exercisesMade[i]) {
                doneExercisesN++;
            }
        }
        return doneExercisesN;
    }

    @Override
    public String toString() {
        String weekString = "viikko " + week;
        String hourOrHours = hours == 1 ? "tunti" : "tuntia";
        String timeSpent = "aikaa kului " + hours + " " + hourOrHours;
        int doneExercisesN = 0;
        String doneExercisesString = "";
        for (int i = 1; i < exercisesMade.length; i++) {
            if (exercisesMade[i]) {
                doneExercisesN++;
                doneExercisesString += (i) + " ";
            }
        }
        String doneExercisesAmount = "tehtyjä tehtäviä yhteensä: " + 
                doneExercisesN + " (maksimi " + maxExercises + ")";
        String doneExercisesEach = "tehdyt tehtävät: " + doneExercisesString;
        return weekString + ": " + doneExercisesAmount + ", " + timeSpent + 
                ", " + doneExercisesEach;
    }
}