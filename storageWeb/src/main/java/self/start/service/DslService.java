package self.start.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import self.start.persistence.bean.AuthUser;
import self.start.persistence.bean.QUser;
import self.start.persistence.bean.User;
import self.start.persistence.repository.TestRespositry;
import self.start.persistence.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

/**
 * create by liuyong4 2018/5/28 上午10:00
 **/
@Service
public class DslService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private TestRespositry testRespositry;

    @Resource
    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<User> findAll() {
        return queryFactory.selectFrom(QUser.user).fetch();
    }

    public boolean exists(String name) {
        return queryFactory.selectFrom(QUser.user).where(QUser.user.userName.eq(name)).fetchCount() > 0L;
    }

    public Set<AuthUser> findAllAuthUser() {
        Set<AuthUser> custom = testRespositry.custom();
        return custom;
    }
}
