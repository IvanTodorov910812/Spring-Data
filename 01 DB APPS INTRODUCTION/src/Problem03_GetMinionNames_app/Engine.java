package Problem03_GetMinionNames_app;

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
            this.getMinionsName();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     *Problem 3. Get Minion Names
     * */
    private void getMinionsName() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        int id_villain=Integer.parseInt(scanner.nextLine());
        String query ="SELECT DISTINCT m.name,m.age,mv.villain_id,v.name\n" +
                "FROM minions as m JOIN minions_villains as mv ON m.id=mv.minion_id\n" +
                "RIGHT JOIN villains as v ON v.id=mv.villain_id\n"+
                " WHERE v.id=?";

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setInt(1,id_villain );
        ResultSet rs = preparedStatement.executeQuery();
        List<String> minions=new ArrayList();
        boolean isHaveVillains=false;
        String nameVillain="";
        while (rs.next()) {
            isHaveVillains=true;
            String nameMinions=rs.getString(1);
            nameVillain=rs.getString(4);
            if(nameMinions!=null){
                String row = String.format("%s %d", rs.getString(1), rs.getInt(2));
                minions.add(row);
            }

        }
        if(isHaveVillains){
            System.out.printf("Villain: %s\n",nameVillain);
        }else{
            System.out.printf("No villain with ID %d exists in the database.\n",id_villain);
        }

        int count=1;
        for (String minion : minions) {
            System.out.printf("%d.%s\n",count,minion);
            count++;
        }

    }

}
