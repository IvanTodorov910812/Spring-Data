package Problem04_AddMinion_app;

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
            this.addMinion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     *Problem 4. Add Minion
     * */
    private void addMinion() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        List<String> inputMinion= Arrays.stream(scanner.nextLine().split("[:\\s+]")).filter(x->!x.equals(""))
                .collect(Collectors.toList());
        String nameMinion=inputMinion.get(1);
        int ageMinion=Integer.parseInt(inputMinion.get(2));
        String townMinion=inputMinion.get(3);
        List<String> inputVillain=Arrays.stream(scanner.nextLine().split("[:\\s+]")).filter(x->!x.equals(""))
                .collect(Collectors.toList());
        String nameVillain = inputVillain.get(1);
       List<String> results=new ArrayList();
        String queryTown="SELECT t.id,t.name,t.country FROM towns AS t";
        String queryInsertTown="INSERT INTO towns(name,country) VALUES (?,null)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(queryTown);
        ResultSet rs = preparedStatement.executeQuery();
        boolean isHaveThisTown=false;
        while(rs.next()){
			/**
			* Ако има в рс(Резултсета) градът и той е в базата променливата на труе
			*/
            if(rs.getString(2).equals(townMinion)){
                isHaveThisTown=true;
            }
        }
			/**
			* (Ако го няма в рс(Резултсета) градът съответно се вкарва
			*/
        if(!isHaveThisTown){
            PreparedStatement preparedStatementInsertTown = this.connection.prepareStatement(queryInsertTown);
            preparedStatementInsertTown.setString(1,townMinion);
            int resultSetInsertTown =preparedStatementInsertTown.executeUpdate();
            results.add(String.format("Town %s was added to the database.",townMinion));
        }
        String queryInsertMinion="INSERT INTO minions (name,age,town_id) VALUES (?,?,?)";
        PreparedStatement preparedStatementInsertMinion = this.connection.prepareStatement(queryInsertMinion);
        preparedStatementInsertMinion.setString(1,nameMinion);
        preparedStatementInsertMinion.setInt(2,ageMinion);

        String queryTakeIdTown="SELECT t.id FROM towns AS t WHERE t.name=?";
        PreparedStatement psTakeIdTown=this.connection.prepareStatement(queryTakeIdTown);
        psTakeIdTown.setString(1,townMinion);
        ResultSet rsTakeIdTown=psTakeIdTown.executeQuery();
        rsTakeIdTown.next();
        int idTown=rsTakeIdTown.getInt(1);

        preparedStatementInsertMinion.setInt(3,idTown);
        int resultSetInsertMinion =preparedStatementInsertMinion.executeUpdate();

        String querySelectVillain="SELECT v.NAME from VILLAINS AS v";
        PreparedStatement preparedStatementSelectVillain=this.connection.prepareStatement(querySelectVillain);
        boolean isHaveThisVillain=false;
        ResultSet resultSetSelstVillain=preparedStatementSelectVillain.executeQuery();
        while(resultSetSelstVillain.next()){
            if(resultSetSelstVillain.getString("name").equals(nameMinion)) {
                isHaveThisVillain = true;
            }
        }
        if(!isHaveThisVillain){
            String insertVillain="INSERT INTO villains(name,evilness_factor) VALUES(?,'evil')";
            PreparedStatement preparedStatementInsertVillain=this.connection.prepareStatement(insertVillain);
            preparedStatementInsertVillain.setString(1,nameVillain);
            int resultSetInsertVillain=preparedStatementInsertVillain.executeUpdate();
        }
        String queryTakeIdMinion="SELECT m.id,m.name FROM minions AS m WHERE m.name=?";
        String queryTakeIdVinion="SELECT v.id,v.name FROM villains AS v WHERE v.name=?";
        PreparedStatement psTakeIdMinion=this.connection.prepareStatement(queryTakeIdMinion);
        psTakeIdMinion.setString(1,nameMinion);
        ResultSet rsTakeIdMinion=psTakeIdMinion.executeQuery();
        rsTakeIdMinion.next();
       int idMinion=rsTakeIdMinion.getInt(1) ;
        PreparedStatement psTakeIdVillain=this.connection.prepareStatement(queryTakeIdVinion);
        psTakeIdVillain.setString(1,nameVillain);
        ResultSet rsTakeIdVillain=psTakeIdVillain.executeQuery();
        rsTakeIdVillain.next();
        int idVillain=rsTakeIdVillain.getInt(1) ;
        String insertMinionAsServant="INSERT INTO minions_villains(minion_id,villain_id) VALUES(?,?)";
        PreparedStatement preparedStatementInsertMinionAsServant=this.connection.prepareStatement(insertMinionAsServant);
       preparedStatementInsertMinionAsServant.setInt(1,idMinion);
        preparedStatementInsertMinionAsServant.setInt(2,idVillain);
        results.add(String.format("Successfully added %s to be minion of %s",nameMinion,nameVillain));
        for (String result : results) {
            System.out.println(result);
        }

    }

}
