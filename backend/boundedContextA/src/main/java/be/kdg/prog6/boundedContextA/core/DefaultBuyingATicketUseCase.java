package be.kdg.prog6.boundedContextA.core;

import be.kdg.prog6.boundedContextA.domain.BuyTicket;
import be.kdg.prog6.boundedContextA.ports.in.BuyTicketsAmountCommand;
import be.kdg.prog6.boundedContextA.ports.in.BuyingATicketUseCase;
import be.kdg.prog6.boundedContextA.ports.out.TicketCreatePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultBuyingATicketUseCase implements BuyingATicketUseCase {
    final BuyTicket buyTicket;
    private final List<TicketCreatePort> ticketCreatePorts;

    @Override
    public void buyTicket(BuyTicketsAmountCommand buyTicketsAmountCommand) {
        var ticket = buyTicket.buyTicket(buyTicketsAmountCommand.action(), buyTicketsAmountCommand.start(), buyTicketsAmountCommand.end(), buyTicketsAmountCommand.price(), buyTicketsAmountCommand.owner());
        ticketCreatePorts.stream().forEach(ticketCreatedPort -> ticketCreatedPort.createTicket(ticket));
    }
}
