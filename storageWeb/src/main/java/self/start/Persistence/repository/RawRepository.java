package self.start.Persistence.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import self.start.Persistence.bean.XxEntity;

import javax.annotation.Resource;

/**
 * @author lyongy.liu on 下午 4:32.
 */
@Repository
public class RawRepository {

    @Resource
    private SessionFactory sessionFactory;

    public void save(XxEntity item) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.getTransaction().begin();
        currentSession.save(item);
        currentSession.getTransaction().commit();
    }
}
