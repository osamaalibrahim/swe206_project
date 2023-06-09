import java.util.ArrayList;

public class Student extends User {
    private ArrayList<Team> teams = new ArrayList<>();

    public Student(String email, String ID, String name, String pass) {
        super(email, ID, name, pass);
        super.access = false;
        teams = new ArrayList<>();
    }

    public Student(String email, String ID, String name) {
        super(email, ID, name);
        super.access = false;
        teams = new ArrayList<>();
    }

    public boolean getAccess() {
        return super.access;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }

    public void showProfile() {
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getID());
        System.out.println("Email: " + getEmail());
        System.out.println("Teams: " + teams);
    }
}