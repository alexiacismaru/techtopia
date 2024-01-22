package be.kdg.prog6.boundedContextA.adapters.in;

import be.kdg.prog6.boundedContextA.ports.in.BuyTicketsAmountCommand;
import be.kdg.prog6.boundedContextA.ports.in.BuyingATicketUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TicketsController {
    private final BuyingATicketUseCase buyingATicketUseCase;

    public TicketsController(BuyingATicketUseCase buyingATicketUseCase) {
        this.buyingATicketUseCase = buyingATicketUseCase;
    }

    @PostMapping("/ticket")
    public void receiveMoney(@RequestBody BuyTicketsAmountCommand command) {
        try {
            buyingATicketUseCase.buyTicket(command);
        } catch (IllegalArgumentException e) {
            System.out.println("An IllegalArgumentException occurred: " + e.getMessage());
        }
    }
}
