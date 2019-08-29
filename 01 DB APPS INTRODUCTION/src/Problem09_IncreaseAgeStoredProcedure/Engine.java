package Problem09_IncreaseAgeStoredProcedure;

import java.sql.*;
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
            this.increaseAgeStoreProcedure();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     *Problem 9. Increase Age Stored Procedure
     * */
    private void increaseAgeStoreProcedure() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int idMinion = Integer.parseInt(scanner.nextLine());
//        String queryStoreProcedrure =
//                "CREATE PROCEDURE usp_get_older(id INT)\n" +
//                        "  BEGIN\n" +
//                        "    UPDATE minions AS m\n" +
//                        "    SET m.age=m.age+1\n" +
//                        "    WHERE m.id=id;\n" +
//                        "END\n";
        String queryCallStoredProcedure="CALL usp_get_older(?)";
        CallableStatement psMinions = this.connection.prepareCall(queryCallStoredProcedure);
        psMinions.setInt(1,idMinion);
        boolean rsSelectMinions = psMinions.execute();

        String querySelect="SELECT m.name,m.age FROM minions AS m\n"+
                "WHERE m.id=?";
        PreparedStatement psSelectMinion = this.connection.prepareStatement(querySelect);
        psSelectMinion.setInt(1,idMinion);
        ResultSet rsSelectMinion = psSelectMinion.executeQuery();
        rsSelectMinion.next();
        System.out.printf("%s %d\n",rsSelectMinion.getString("name"),rsSelectMinion.getInt(2));


    }

}
