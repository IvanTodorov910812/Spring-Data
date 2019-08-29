package Problem06_RemoveVillain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Engine implements Runnable {
    private Connection connection;

    public Engine(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            this.removeViallain();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
     *Problem 6. Remove Villain
     * */
    private void removeViallain() throws SQLException {
        Scanner scanner=new Scanner(System.in);
        int idVillain=Integer.parseInt(scanner.nextLine());
        String queryCountMinions="SELECT v.id,v.name,COUNT(mv.minion_id) AS count_minion " +
                "FROM minions_villains AS mv " +
                "RIGHT JOIN villains AS v ON v.id=mv.villain_id " +
                "WHERE v.id=? GROUP BY v.id";
        PreparedStatement psCountMinion=this.connection.prepareStatement(queryCountMinions);
        psCountMinion.setInt(1,idVillain);
        ResultSet rsqueryCountMinion=psCountMinion.executeQuery();
        int countMinion=0;
        String queryDeleteVillainFromTableVillins="DELETE FROM villains WHERE id=?";
        String queryDeleteVillainFromTableMinionsVillins="DELETE FROM minions_villains WHERE villain_id=?";
        if( rsqueryCountMinion.next()){
            String nameVillain=rsqueryCountMinion.getString(2);
            countMinion=rsqueryCountMinion.getInt("count_minion");
            if(countMinion==0){
                PreparedStatement psDeleteVillain=this.connection.prepareStatement(queryDeleteVillainFromTableVillins);
                psDeleteVillain.setInt(1,idVillain);
                psDeleteVillain.execute();
            }else{
                PreparedStatement psDeleteVillain=this.connection.prepareStatement(queryDeleteVillainFromTableMinionsVillins);
                psDeleteVillain.setInt(1,idVillain);
                psDeleteVillain.execute();
            }

            System.out.printf("%s was deleted\n",nameVillain);
            System.out.printf("%d minions released\n",countMinion);

        }else{
            System.out.println("No such villain was found");
        }
    }

}
