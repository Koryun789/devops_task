package ai.podcastle.gradleTest;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/")
    public String test() {
        return "Hi, this should be 0.1.0";
    }

    @GetMapping("/load/{time}")
    public String load(@PathVariable long time) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < time * 1000) {
            long a = 1;
            for (long i = 1; i < 5000000000L; i++) {
                a += i;
            }
            System.out.println(a);
        }

        System.out.println("done");
        return Optional.ofNullable(System.getenv("HOSTNAME")).orElse("unavailable");
    }

    @GetMapping("/health")
    public ResponseEntity health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
