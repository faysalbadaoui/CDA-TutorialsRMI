import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrimeSumServiceImpl extends UnicastRemoteObject implements PrimeSumService {
    private int latestSum;
    private boolean isCompleted;
    private ExecutorService executor;

    public PrimeSumServiceImpl() throws RemoteException {
        super();
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public int syncPrimeSum(int m) {
        int sum = calculatePrimeSum(m);
        this.latestSum = sum;
        this.isCompleted = true;
        return sum;
    }

    @Override
    public void asyncPrimeSum(int m) throws InterruptedException {
        if (!executor.isShutdown()) {
            executor.shutdownNow();
        }
        executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            int sum = calculatePrimeSum(m);
            this.latestSum = sum;
            this.isCompleted = true;
        });
    }

    @Override
    public int getLatestSum() {
        return this.latestSum;
    }

    @Override
    public boolean isCompleted() {
        return this.isCompleted;
    }

    private int calculatePrimeSum(int m) {
        int sum = 0;
        for (int i = 2; i <= m; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}