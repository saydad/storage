package self.start.web.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import self.start.Persistence.bean.XxEntity;
import self.start.Persistence.repository.RawRepository;
import self.start.Persistence.repository.TestRepository;

import javax.annotation.Resource;

/**
 * spring boot web例子
 * @author lyongy.liu on 下午 9:05.
 */
@RestController
public class WebExample {

    @Resource
    private TestRepository testRepository;
    @Resource
    private RawRepository rawRepository;

    @RequestMapping("/select")
    public XxEntity handle(@RequestParam("id") int id) {
        return testRepository.findOne(id);
    }

    @RequestMapping("/save")
    public String save(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("num") int num) {
        XxEntity item = new XxEntity();
        item.setId(id);
        item.setName(name);
        item.setNum(num);
        rawRepository.save(item);
        return "success";
    }

    public void test() {
        System.out.print("哈哈");
    }
}
