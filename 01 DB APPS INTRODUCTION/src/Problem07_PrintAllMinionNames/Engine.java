package Problem07_PrintAllMinionNames;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Engine implements Runnable {
    private Connection connection;

    public Engine(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            this.printAllMinionNames();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     *Problem 7. Print All Minion Names
     * */
    private void printAllMinionNames() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        String querySelectTowns="SELECT m.name FROM minions AS m ";
        PreparedStatement psSelectTowns=this.connection.prepareStatement(querySelectTowns);
        ResultSet rsSelectTowns=psSelectTowns.executeQuery();
        List<String> towns=new ArrayList<>();
        while(rsSelectTowns.next()){
            towns.add(rsSelectTowns.getString(1));
        }
        if(towns.size()%2==0) {
            for (int i = 0; i < towns.size() / 2; i++) {
                System.out.println(towns.get(i));
                System.out.println(towns.get(towns.size() - i-1));
            }
        }else{
            for (int i = 0; i < towns.size() / 2; i++) {
                System.out.println(towns.get(i));
                System.out.println(towns.get(towns.size() - i-1));
            }
            System.out.println(towns.get(towns.size()/2));
        }




    }

}
