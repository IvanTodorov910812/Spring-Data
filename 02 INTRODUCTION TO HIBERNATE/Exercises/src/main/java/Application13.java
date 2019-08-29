import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Application13 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
/**
 * SELECT d.name,max(e.salary) as salary FROM employees as e
 * JOIN departments as d ON e.department_id=d.department_id
 * group by e.department_id
 * having salary<30000 or salary>70000
 */
        List<Object[]> employees = em.createQuery("SELECT d.name, max(e.salary) AS max_salary FROM Employee AS e " +
                "JOIN e.department as d GROUP BY e.department HAVING  max(e.salary)<30000 or  max(e.salary)>70000", Object[].class)
               .getResultList();

        for (Object[] employee : employees) {
            System.out.printf("%s - ($%.2f)\n",employee[0],
                    (BigDecimal)employee[1]);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
