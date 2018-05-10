package self.start.web.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import self.start.Persistence.bean.XxEntity;
import self.start.Persistence.repository.RawRepository;
import self.start.Persistence.repository.TestRepository;
import self.start.config.TestProperties;

import javax.annotation.Resource;
import java.util.List;
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

    @PostMapping("/param")
    public Map<String, List<String>> test(@RequestBody Map<String, List<String>> param) {
        return param;
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
}
