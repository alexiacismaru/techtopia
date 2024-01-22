package be.kdg.prog6.boundedContextA.adapters.out.db;

import be.kdg.prog6.boundedContextA.domain.TicketAction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema="boundedContextA",name = "boundedContextA.tickets")
@Getter
@Setter
@NoArgsConstructor
public class TicketBoughtJpaEntity {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    public TicketBoughtJpaEntity(UUID uuid) {
        this.uuid = uuid;
    }

    @JdbcTypeCode(Types.VARCHAR)
    private UUID owner;

    @Column
    private LocalDateTime start;
    @Column
    private LocalDateTime end;

    @Column
    private double price;
}
