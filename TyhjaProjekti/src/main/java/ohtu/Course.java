
package ohtu;

public class Course {
    private String name;
    private String term;
    private int[] maxPoints;

    @Override
    public String toString() {
        return "Kurssi: " + this.name + ", " + this.term;
    }
}
