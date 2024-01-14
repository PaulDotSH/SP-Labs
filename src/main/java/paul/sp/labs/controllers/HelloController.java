package paul.sp.labs.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import paul.sp.labs.examples.ClientComponent;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class HelloController {

    private final ClientComponent clientComponent;
    private final ClientComponent clientComponent2;


    @GetMapping("/")
    public String ClientComponentHello() {
        return String.format("ClientComponent = %s%n%s", clientComponent, clientComponent2);
    }

}