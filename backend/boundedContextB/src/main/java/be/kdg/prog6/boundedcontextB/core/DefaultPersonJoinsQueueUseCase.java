package be.kdg.prog6.boundedcontextB.core;

import be.kdg.prog6.boundedcontextB.domain.Queue;
import be.kdg.prog6.boundedcontextB.ports.in.PersonJoinsQueueUseCase;
import be.kdg.prog6.boundedcontextB.ports.out.QueueProjectionOutPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultPersonJoinsQueueUseCase implements PersonJoinsQueueUseCase {
    private QueueProjectionOutPort queueProjectionOutPort;
    @Override
    public void personJoinsQueue() {
        Queue queue = Queue.getInstance();
        queue.setAmountOfPeople(queue.getAmountOfPeople() + 1);
        queueProjectionOutPort.updateQueue(queue);
    }
}
