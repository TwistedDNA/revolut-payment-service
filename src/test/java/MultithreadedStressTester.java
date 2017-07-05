import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Lx on 7/5/2017.
 */
public class MultithreadedStressTester {
    private int threadCount;

    public MultithreadedStressTester(int threadCount) {
        this.threadCount = threadCount;
    }

    public void stress(final Runnable action) throws InterruptedException {
        spawnThreads(action).await();
    }

    private CountDownLatch spawnThreads(final Runnable action) {
        final CountDownLatch finished = new CountDownLatch(threadCount);
        Executor executor = new ForkJoinPool();
        for (int i = 0; i < threadCount; i++) {
            executor.execute(() -> {
                try {
                    repeat(action);
                } finally {
                    finished.countDown();
                }
            });
        }
        return finished;
    }

    private void repeat(Runnable action) {
        for (int i = 0; i < threadCount; i++) {
            action.run();
        }
    }
}
