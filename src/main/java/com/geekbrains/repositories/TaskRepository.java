package com.geekbrains.repositories;

        import com.geekbrains.entities.Task;
        import org.hibernate.SessionFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Repository;

        import javax.persistence.EntityManager;
        import javax.transaction.Transactional;
        import java.util.List;

@Repository
@Transactional
public class TaskRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public TaskRepository() {
    }

    public boolean isTaskInDB(Task task)  {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();
        Task taskRes = em.find(Task.class, task.getId());
        em.getTransaction().commit();
        em.close();
        return taskRes!=null;
    }

    public Task getTaskById(Long id)  {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();
        Task taskRes = em.find(Task.class, id);
        em.getTransaction().commit();
        em.close();
        return taskRes;
    }

    public List<Task> getAllTasksFromDB(){
        EntityManager em = sessionFactory.createEntityManager();
        List<Task> res = em.createQuery("SELECT a from Task a", Task.class).getResultList();
        em.close();
        return res;
    }

    public void addTask(Task task) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(task);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteTaskFromDBById(Long id) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();
        for (Task task : em.createQuery("SELECT a from Task a WHERE a.id = '" + id + "'", Task.class).getResultList()) {
            em.remove(task);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void deleteTaskFromDBByTitle(String title) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();
        for (Task task : em.createQuery("SELECT a from Task a WHERE a.title = '" + title + "'", Task.class).getResultList()) {
            em.remove(task);
        }
        em.getTransaction().commit();
        em.close();
    }
}




