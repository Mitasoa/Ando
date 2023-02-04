package mg.groupe26.enchere2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@SpringBootApplication
public class Enchere2Application {

    public static void main(String[] args) {
        SpringApplication.run(Enchere2Application.class, args);
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
