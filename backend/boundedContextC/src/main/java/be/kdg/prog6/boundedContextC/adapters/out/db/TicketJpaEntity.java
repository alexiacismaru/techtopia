package be.kdg.prog6.boundedContextC.adapters.out.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "boundedContextC", name = "boundedContextC.tickets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketJpaEntity {
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID ticketUUID;
    private LocalDateTime start;
    private LocalDateTime end;
    private boolean isValid;
}
