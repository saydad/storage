package self.start.web.example;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import self.start.config.TestProperties;
import self.start.persistence.bean.XxEntity;
import self.start.persistence.repository.RawRepository;
import self.start.persistence.repository.TestRepository;
import self.start.service.HttpTestService;
import self.start.service.RabbitSender;

import javax.annotation.Resource;
import java.util.Map;

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
    @Resource
    private TestProperties testProperties;
    @Resource
    private RabbitSender rabbitSender;
    @Resource
    private HttpTestService httpTestService;

    @Value("${profile.test}")
    private String value;
    @Value("${extend.value}")
    private String extendValue;

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
        item.setAddress(Sets.newHashSet("AA", "BB"));
        rawRepository.save(item);
        return "success";
    }

    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
            return "empty";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);
        return "ok";
    }

    @PostMapping(path = "/error", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> cause500(@RequestBody Map<String, Object> param) {
        return param;
    }

    @GetMapping("/test")
    public String test() {
        return value + " " + extendValue;
    }
}
