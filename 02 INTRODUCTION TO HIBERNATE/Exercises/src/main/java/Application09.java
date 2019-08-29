import entities.Address;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application09 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        List<Project> project=em.createQuery("FROM Project ORDER BY startDate DESC",Project.class).
               setMaxResults(10).getResultList();
        for (Project project1 : project.stream().sorted((x,y)->x.getName().compareTo(y.getName())).collect(Collectors.toList())) {
            System.out.printf("Project name: %s\nProject Description: %s\nProject Start Date:%s\n" +
                    "Project End Date:%s\n",project1.getName(),project1.getDescription()
            ,project1.getStartDate(),project1.getEndDate());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
