package be.kdg.prog6.boundedContextA.adapters.out.db;

import be.kdg.prog6.boundedContextA.domain.TicketAction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(schema="boundedContextB",name = "boundedContextB.activities")
@Getter
@Setter
public class TicketJpaActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private TicketAction action;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID ticket;

    private LocalDateTime pit;
    private double price;
}

