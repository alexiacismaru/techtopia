package be.kdg.prog6.boundedcontextB.adapters.out;

import be.kdg.prog6.boundedcontextB.domain.Queue;
import be.kdg.prog6.boundedcontextB.ports.out.QueueProjectionOutPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QueueProjectionDbOutAdapter implements QueueProjectionOutPort {
    private QueueProjectionJpaRepository repository;
    @Override
    public void updateQueue(Queue queue) {
        QueueProjectionJpaEntity queueJpaEntity = new QueueProjectionJpaEntity(queue.getId());
        queueJpaEntity.setTotalPeopleInQueue(queue.getAmountOfPeople());
        queueJpaEntity.setWaitingTime(queue.getWaitingTime());
        repository.save(queueJpaEntity);
    }
}
