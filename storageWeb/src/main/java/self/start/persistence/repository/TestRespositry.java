package self.start.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import self.start.persistence.bean.AuthUser;
import self.start.persistence.bean.User;

import java.util.List;
import java.util.Set;

/**
 * create by liuyong4 2018/5/28 下午2:06
 **/
@Repository
public interface TestRespositry extends QueryDslPredicateExecutor<AuthUser>, JpaRepository<AuthUser, Long> {

    @Query("select authUser from AuthUser as authUser join fetch authUser.role")
    public Set<AuthUser> custom();
}
