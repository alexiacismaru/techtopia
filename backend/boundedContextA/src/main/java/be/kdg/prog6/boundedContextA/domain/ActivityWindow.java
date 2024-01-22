package be.kdg.prog6.boundedContextA.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActivityWindow {
    private final List<TicketActivity> tickets = new ArrayList<>();
    public LocalDateTime getStartTimeStamp() {
        return tickets.stream()
                .min(Comparator.comparing(TicketActivity::pit))
                .orElseThrow(IllegalStateException::new).pit();
    }

    public LocalDateTime getEndTimeStamp() {
        return tickets.stream()
                .max(Comparator.comparing(TicketActivity::pit))
                .orElseThrow(IllegalStateException::new).pit();
    }

    public boolean add(TicketActivity activity){
        return tickets.add(activity);
    }
}

