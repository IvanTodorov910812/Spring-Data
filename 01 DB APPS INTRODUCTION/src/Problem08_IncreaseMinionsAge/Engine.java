package Problem08_IncreaseMinionsAge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine implements Runnable {
    private Connection connection;

    public Engine(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            this.increaseMinionAge();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     *Problem 8. Increase Minions Age
     * */
    private void increaseMinionAge() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        List<Integer> iDs= Arrays.stream(scanner.nextLine().split( "\\s+")).
                mapToInt(x->Integer.parseInt(x)).boxed().collect(Collectors.toList());
        String name="CONCAT(UPPER(SUBSTRING(m.name,1,1)),SUBSTRING(m.name,2,CHAR_LENGTH (m.name)))";
        for (int i = 0; i <iDs.size() ; i++) {
            String queryUpdateMinions="UPDATE minions AS m SET m.name="+name+", m.age=m.age+1 WHERE m.id=?";
            PreparedStatement psUpdateMinions=this.connection.prepareStatement(queryUpdateMinions);
           psUpdateMinions.setInt(1,iDs.get(i));
            int rsUpdateTowns=psUpdateMinions.executeUpdate();
        }
        String querySelectMinions="SELECT m.name,m.age FROM minions AS m ";
        PreparedStatement psSelectMinions =this.connection.prepareStatement(querySelectMinions);
        ResultSet rsSelectMinions=psSelectMinions.executeQuery();
        List<String> minions=new ArrayList<>();
        while(rsSelectMinions.next()){
            System.out.printf("%s %d\n",rsSelectMinions.getString(1),rsSelectMinions.getInt(2));
        }







    }

}
