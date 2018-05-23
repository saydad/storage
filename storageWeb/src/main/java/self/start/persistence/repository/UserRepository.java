package self.start.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import self.start.persistence.bean.User;

/**
 * create by liuyong4 2018/5/24 下午6:07
 **/
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserNameAndPassWord(String userName, String passWord);
}
