package Problem05_ChangeTownNamesCasing_app;

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
            this.changeTownNamesCasing();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     *Problem 5. Change Town Names Casing
     * */
    private void changeTownNamesCasing() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        String country=scanner.nextLine();
        String queryTownNameCasing="UPDATE towns AS t SET t.name=UPPER(t.name) WHERE t.country=? and t.name NOT LIKE UPPER(t.name)";
        PreparedStatement psTownNameCasing=this.connection.prepareStatement(queryTownNameCasing);
        psTownNameCasing.setString(1,country);
        int  countTownNameCasing=psTownNameCasing.executeUpdate();
        String querySelectTowns="SELECT t.name FROM towns AS t WHERE t.country=?";
        PreparedStatement psSelectTowns=this.connection.prepareStatement(querySelectTowns);
        psSelectTowns.setString(1,country);
        ResultSet rsSelectTowns=psSelectTowns.executeQuery();
        List<String> towns=new ArrayList<>();
        int countTowns=0;
        while(rsSelectTowns.next()){
            towns.add(rsSelectTowns.getString(1));
        }
        if(countTownNameCasing==0){
            System.out.println("No town names were affected.");
        }else{
            System.out.printf("%d town names were affected.\n",countTownNameCasing);
            System.out.println(towns);
        }



    }

}
