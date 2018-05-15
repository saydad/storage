package self.start.web.example;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
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
import self.start.service.HttpTestService;
import self.start.service.RabbitSender;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

    @PostMapping("/msg")
    public void test(@RequestBody Map<String, String> param) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        countDownLatch.await();
                        rabbitSender.sendMsg(param);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        countDownLatch.countDown();
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

    @GetMapping("/error")
    public String cause500() {
        try {
            TimeUnit.MILLISECONDS.sleep(15000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return "error";
    }

}
