package self.start.web.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    /*
     * 获取multifile.html页面
     */
    @RequestMapping("/xx")
    public String multifile(){
        return "file";
    }
}
