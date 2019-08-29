import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Application03 {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String name=scanner.nextLine();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Employee> employee= em.createQuery("FROM Employee WHERE concat(firstName,' ',lastName)=:name", Employee.class)
                .setParameter("name",name).getResultList();
            if(employee.size()>0){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
