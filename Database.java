import java.io.*;
import java.util.ArrayList;

public class Database {
    private String file;
    static User currentUser;

    public Database() {
        file = "Data.exe";
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try (ObjectInputStream objStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Object obj = objStream.readObject();
                if (obj instanceof User) {
                    users.add((User) obj);
                }
            }
        } catch (EOFException e) {
            //
        } catch (Exception e) {
            e.getStackTrace();
        }
        return users;
    }

    public User getUser(String ID) {
        try (ObjectInputStream objStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Object obj = objStream.readObject();
                if (obj instanceof User) {
                    if(((User) obj).getID().equals(ID)){
                        return ((User) obj);
                    }
                   
                }
            }
        } catch (EOFException e) {
            //
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public ArrayList<Team> getTeams() {
        ArrayList<Team> teams = new ArrayList<>();

        try (ObjectInputStream objStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Object obj = objStream.readObject();
                if (obj instanceof Team) {
                    Team team = (Team) obj;
                    teams.add(team);
                }
            }
        } catch (EOFException e) {
            //
        } catch (Exception e) {
            e.getStackTrace();
        }
        return teams;
    }

    public ArrayList<Tournament> getTournaments() {
        ArrayList<Tournament> tournaments = new ArrayList<>();
        try (ObjectInputStream objStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Object obj = objStream.readObject();
                if (obj instanceof Tournament) {
                    Tournament tournament = (Tournament) obj;
                    tournaments.add(tournament);
                }
            }
        } catch (EOFException e) {
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tournaments;
    }

    public void write(ArrayList<Tournament> tournaments, ArrayList<Team> teams, ArrayList<User> users) {
        try {
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream objStream = new ObjectOutputStream(output);

            for (int i = 0; i < tournaments.size(); i++) {
                objStream.writeObject(tournaments.get(i));
            }
            for (int i = 0; i < teams.size(); i++) {
                objStream.writeObject(teams.get(i));
            }
            for (int i = 0; i < users.size(); i++) {
                objStream.writeObject(users.get(i));
            }

            output.close();
            objStream.close();

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}