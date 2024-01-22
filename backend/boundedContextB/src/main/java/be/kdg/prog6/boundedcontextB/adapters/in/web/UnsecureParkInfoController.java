package be.kdg.prog6.boundedcontextB.adapters.in.web;

import be.kdg.prog6.boundedcontextB.messages.UnsecuredMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsecured")
public class UnsecureParkInfoController {
    @GetMapping
    public UnsecuredMessage getUnsecured(){
        return new UnsecuredMessage();
    }
}
