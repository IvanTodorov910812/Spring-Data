import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Application12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pathern = scanner.nextLine();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Employee> employees = em.createQuery("FROM Employee WHERE firstName LIKE CONCAT(:pathern,'%')", Employee.class)
                .setParameter("pathern", pathern).getResultList();

        for (Employee employee : employees) {
            System.out.printf("%s %s - %s - ($%.2f)\n",employee.getFirstName(),
                    employee.getLastName(),employee.getDepartment(),employee.getSalary());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
