package template.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Data
@AllArgsConstructor
@Controller
@RequestMapping("/endpoint")
public class ReceiverController {

    @PostMapping("/first")
    public ResponseEntity<String> first(@RequestBody String message) {
        String msg = String.format("First endpoint received: %s", message);
        log.info(msg);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/second")
    public ResponseEntity<String> second(@RequestBody String message) {
        String msg = String.format("Second endpoint received: %s", message);
        log.info(msg);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
