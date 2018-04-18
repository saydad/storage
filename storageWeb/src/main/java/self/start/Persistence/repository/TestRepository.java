package self.start.Persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import self.start.Persistence.bean.XxEntity;

import javax.persistence.Table;

/**
 * @author lyongy.liu on 下午 3:51.
 */
@Repository
@Table(name = "xx", schema = "test")
public interface TestRepository extends CrudRepository<XxEntity, Integer> {

    @Query("select x from XxEntity as x where x.id=:id")
    @Override
    XxEntity findOne(@Param("id") Integer aLong);
}
