package workoutDiary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Gruppe extends ActiveDomainObject{

    private String gruppeNavn;
    private String beskrivelse;
    private int id;

    public Gruppe(String gruppeNavn, String beskrivelse){
        this.gruppeNavn = gruppeNavn;
        this.beskrivelse = beskrivelse;

    }

    @Override
    public String toString() {
        return "Gruppe{" +
                "gruppeNavn='" + gruppeNavn + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                ", id=" + id +
                '}';
    }

    public List<Gruppe> getAllGroups(Connection conn)  {

        try {
            List<Gruppe> groupList = new ArrayList<>();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Gruppe");
            while (rs.next()) {
                gruppeNavn = rs.getString("gruppeNavn");
                beskrivelse = rs.getString("beskrivelse");
                Gruppe gruppe = new Gruppe(gruppeNavn, beskrivelse);
                groupList.add(gruppe);
            }
            return groupList;
        } catch (Exception e) {
            System.out.println("db error during select of avtale= "+e);
            return null;
        }
    }

    @Override
    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(
                    "INSERT INTO `Gruppe` VALUES ("+id+","+"\""+gruppeNavn+"\""+","+"\""+beskrivelse+"\""+");");

        } catch (Exception e) {
            System.out.println("db error during insert of Gruppe="+e);
            return;
        }
    }

}
