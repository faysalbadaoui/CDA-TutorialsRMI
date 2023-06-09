import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PrimeSumServiceImplDist extends UnicastRemoteObject implements PrimeSumServiceDist {
    private int latestSum;
    private boolean isCompleted;
    private List<Worker> workers;

    public PrimeSumServiceImplDist() throws RemoteException {
        super();
        this.workers = new ArrayList<>();
    }

    @Override
    public void registerWorker(Worker worker) throws RemoteException {
        workers.add(worker);
    }

    @Override
    public void unregisterWorker(Worker worker) throws RemoteException {
        workers.remove(worker);
    }

    @Override
    public void asyncPrimeSum(int m) throws RemoteException, InterruptedException {
        int numWorkers = workers.size();
        int range = m / numWorkers;
        this.latestSum = 0;
        this.isCompleted = false;
        for (int i = 0; i < numWorkers; i++) {
            int start = i * range + 1;
            int end = (i + 1) * range;
            Worker worker = workers.get(i);
            int subtaskSum = worker.processSubtask(start, end);
            this.latestSum += subtaskSum;
        }
        for (Worker worker : workers) {
            worker.notifyCompletion();
        }
        this.isCompleted = true;
        System.out.println("Result got: " + this.latestSum);
    }

    @Override
    public int getLatestSum() throws RemoteException {
        return this.latestSum;
    }

    @Override
    public boolean isCompleted() throws RemoteException {
        return this.isCompleted;
    }
}
