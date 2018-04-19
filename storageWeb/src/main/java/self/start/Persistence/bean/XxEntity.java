package self.start.Persistence.bean;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lyongy.liu on 下午 7:45.
 */
@Entity
@Table(name = "xx", schema = "test", indexes = {@Index(name = "name_idx", columnList = "name")})
public class XxEntity {
    private int id;
    private String name;
    private int num;
    private Date operationTime;

    @Id
    @GeneratedValue(generator = "mysql")
    @GenericGenerator(name = "mysql", strategy = "native")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "num", nullable = false)
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Basic
    @Column(name = "operation_time", nullable = false, unique = true)
    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XxEntity xxEntity = (XxEntity) o;
        return id == xxEntity.id &&
                num == xxEntity.num &&
                Objects.equal(name, xxEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, num);
    }
}
