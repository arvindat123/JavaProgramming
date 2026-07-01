# Comprehensive Guide to Locks and ReentrantLocks in Java

## 1. Introduction to Locks in Java

Locks are synchronization mechanisms that control access to shared resources in multithreaded environments. Java provides both intrinsic locks (synchronized) and explicit locks (ReentrantLock and other Lock implementations).

```java
public class LockIntroduction {
    /*
     * Types of Locks in Java:
     * 
     * 1. Intrinsic Locks (synchronized)
     *    - Implicit locking mechanism
     *    - Automatic lock acquisition/release
     *    - Cannot be interrupted
     *    - No timeout support
     *    - Basic functionality
     * 
     * 2. Explicit Locks (java.util.concurrent.locks)
     *    - ReentrantLock
     *    - ReadWriteLock
     *    - StampedLock
     *    - More flexible and feature-rich
     *    - Manual lock/unlock
     */
    
    // Intrinsic lock example
    private static class IntrinsicLockExample {
        private int counter = 0;
        
        // synchronized method - acquires lock on 'this'
        public synchronized void increment() {
            counter++;
        }
        
        // synchronized block - more granular locking
        public void decrement() {
            synchronized(this) {
                counter--;
            }
        }
        
        public synchronized int getCounter() {
            return counter;
        }
    }
    
    // ReentrantLock example
    private static class ReentrantLockExample {
        private final ReentrantLock lock = new ReentrantLock();
        private int counter = 0;
        
        public void increment() {
            lock.lock();
            try {
                counter++;
            } finally {
                lock.unlock(); // Always unlock in finally block
            }
        }
        
        public int getCounter() {
            lock.lock();
            try {
                return counter;
            } finally {
                lock.unlock();
            }
        }
    }
}
```

---

## 2. Understanding ReentrantLock in Detail

### What is ReentrantLock?

ReentrantLock is a concrete implementation of the Lock interface that provides more extensive locking operations than intrinsic synchronization.

**Key Concept: "Reentrant"** means the same thread can acquire the same lock multiple times without causing a deadlock.

```java
public class ReentrantLockDeepDive {
    /*
     * ReentrantLock Features:
     * 1. Reentrancy - Thread can acquire lock multiple times
     * 2. Fairness - Can be fair (FIFO) or unfair
     * 3. Interruptibility - Can interrupt waiting threads
     * 4. Timeout - Try to acquire lock with timeout
     * 5. Condition variables - Multiple condition queues
     * 6. Lock inspection - Can check lock status
     * 7. Backwards compatibility with synchronized
     */
    
    // Reentrancy demonstration
    private static class ReentrancyDemo {
        private final ReentrantLock lock = new ReentrantLock();
        
        public void outerMethod() {
            lock.lock();
            try {
                System.out.println("Outer method acquired lock");
                System.out.println("Hold count: " + lock.getHoldCount());
                innerMethod(); // Same thread re-enters the lock
            } finally {
                lock.unlock();
                System.out.println("Outer method released lock");
            }
        }
        
        public void innerMethod() {
            lock.lock(); // This is allowed (reentrant)
            try {
                System.out.println("Inner method acquired lock again");
                System.out.println("Hold count: " + lock.getHoldCount());
            } finally {
                lock.unlock();
                System.out.println("Inner method released lock");
            }
        }
    }
    
    // Fairness demonstration
    private static class FairnessDemo {
        // Fair lock - threads acquire in FIFO order
        private final ReentrantLock fairLock = new ReentrantLock(true);
        
        // Unfair lock - threads can jump ahead
        private final ReentrantLock unfairLock = new ReentrantLock(false);
        
        public void demonstrateFairness() throws InterruptedException {
            System.out.println("=== Fair Lock Demo ===");
            testLock(fairLock);
            
            Thread.sleep(1000);
            
            System.out.println("\n=== Unfair Lock Demo ===");
            testLock(unfairLock);
        }
        
        private void testLock(ReentrantLock lock) throws InterruptedException {
            for (int i = 0; i < 5; i++) {
                final int threadId = i;
                new Thread(() -> {
                    for (int j = 0; j < 2; j++) {
                        lock.lock();
                        try {
                            System.out.println("Thread " + threadId + 
                                             " acquired lock (attempt " + j + ")");
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            lock.unlock();
                            System.out.println("Thread " + threadId + " released lock");
                        }
                    }
                }).start();
                Thread.sleep(10); // Stagger thread start
            }
            Thread.sleep(3000); // Wait for completion
        }
    }
}
```

---

## 3. Advanced ReentrantLock Operations

### 3.1 tryLock() with Timeout

```java
public class TryLockExamples {
    
    private static class Resource {
        private final ReentrantLock lock = new ReentrantLock();
        private int data = 0;
        
        // Non-blocking tryLock
        public boolean tryUpdate(int newValue) {
            if (lock.tryLock()) {
                try {
                    data = newValue;
                    System.out.println("Successfully updated to: " + newValue);
                    return true;
                } finally {
                    lock.unlock();
                }
            }
            System.out.println("Lock was busy, update failed");
            return false;
        }
        
        // tryLock with timeout
        public boolean tryUpdateWithTimeout(int newValue, long timeout, TimeUnit unit) 
                throws InterruptedException {
            if (lock.tryLock(timeout, unit)) {
                try {
                    data = newValue;
                    System.out.println("Successfully updated after waiting: " + newValue);
                    return true;
                } finally {
                    lock.unlock();
                }
            }
            System.out.println("Timeout reached, update failed");
            return false;
        }
    }
    
    // Real-world example: Database connection pool
    private static class ConnectionPool {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition connectionAvailable = lock.newCondition();
        private final List<Connection> connections = new ArrayList<>();
        private final int maxConnections;
        
        public ConnectionPool(int maxConnections) {
            this.maxConnections = maxConnections;
        }
        
        public Connection acquireConnection(long timeout, TimeUnit unit) 
                throws InterruptedException {
            lock.lock();
            try {
                while (connections.isEmpty()) {
                    if (connections.size() >= maxConnections) {
                        // Wait for connection to be released
                        if (!connectionAvailable.await(timeout, unit)) {
                            return null; // Timeout
                        }
                    } else {
                        // Create new connection
                        Connection conn = createConnection();
                        connections.add(conn);
                        return conn;
                    }
                }
                return connections.remove(connections.size() - 1);
            } finally {
                lock.unlock();
            }
        }
        
        public void releaseConnection(Connection conn) {
            lock.lock();
            try {
                connections.add(conn);
                connectionAvailable.signal();
            } finally {
                lock.unlock();
            }
        }
        
        private Connection createConnection() {
            // Simulate connection creation
            return new Connection();
        }
    }
    
    private static class Connection {
        // Simulated connection
    }
}
```

### 3.2 Lock Interruption

```java
public class LockInterruptionExamples {
    
    private static class InterruptibleResource {
        private final ReentrantLock lock = new ReentrantLock();
        
        public void processWithInterruption() throws InterruptedException {
            // lockInterruptibly() allows the thread to be interrupted
            // while waiting for the lock
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + " acquired lock");
                // Simulate long-running operation
                Thread.sleep(5000);
            } finally {
                lock.unlock();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        InterruptibleResource resource = new InterruptibleResource();
        
        // Thread 1 holds the lock
        Thread t1 = new Thread(() -> {
            try {
                resource.processWithInterruption();
            } catch (InterruptedException e) {
                System.out.println("Thread 1 was interrupted");
                Thread.currentThread().interrupt();
            }
        });
        
        // Thread 2 will wait and can be interrupted
        Thread t2 = new Thread(() -> {
            try {
                resource.processWithInterruption();
            } catch (InterruptedException e) {
                System.out.println("Thread 2 was interrupted while waiting for lock");
                Thread.currentThread().interrupt();
            }
        });
        
        t1.start();
        Thread.sleep(100); // Ensure t1 acquires lock first
        t2.start();
        
        Thread.sleep(500);
        System.out.println("Interrupting Thread 2");
        t2.interrupt(); // This will interrupt t2's wait for lock
        
        t1.join();
        t2.join();
    }
}
```

---

## 4. Comparing Synchronized vs ReentrantLock

```java
public class SynchronizedVsReentrantLock {
    /*
     * Comparison Table:
     * 
     * Feature              | synchronized          | ReentrantLock
     * ---------------------|----------------------|-------------------
     * Lock acquisition     | Automatic            | Manual
     * Lock release         | Automatic            | Manual (finally)
     * Reentrant            | Yes                  | Yes
     * Fairness             | No                   | Configurable
     * Try lock (non-blocking)| No                | Yes
     * Try lock with timeout | No                  | Yes
     * Interruptible wait   | No                   | Yes
     * Condition variables  | Only wait/notify     | Multiple conditions
     * Lock inspection      | No                   | Yes (isLocked, getHoldCount)
     * Performance          | Optimized by JVM     | Slightly slower
     * Readability          | More concise         | More verbose
     */
    
    // Performance comparison
    private static class PerformanceTest {
        private static final int THREADS = 10;
        private static final int OPERATIONS = 1_000_000;
        
        // Using synchronized
        private static class SynchronizedCounter {
            private int count = 0;
            
            public synchronized void increment() {
                count++;
            }
            
            public synchronized int getCount() {
                return count;
            }
        }
        
        // Using ReentrantLock
        private static class ReentrantCounter {
            private final ReentrantLock lock = new ReentrantLock();
            private int count = 0;
            
            public void increment() {
                lock.lock();
                try {
                    count++;
                } finally {
                    lock.unlock();
                }
            }
            
            public int getCount() {
                lock.lock();
                try {
                    return count;
                } finally {
                    lock.unlock();
                }
            }
        }
        
        public static void testSynchronized() throws InterruptedException {
            SynchronizedCounter counter = new SynchronizedCounter();
            Thread[] threads = new Thread[THREADS];
            
            long start = System.nanoTime();
            for (int i = 0; i < THREADS; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < OPERATIONS / THREADS; j++) {
                        counter.increment();
                    }
                });
                threads[i].start();
            }
            
            for (Thread t : threads) {
                t.join();
            }
            long time = System.nanoTime() - start;
            
            System.out.println("Synchronized counter: " + counter.getCount() + 
                             ", Time: " + time / 1_000_000 + "ms");
        }
        
        public static void testReentrantLock() throws InterruptedException {
            ReentrantCounter counter = new ReentrantCounter();
            Thread[] threads = new Thread[THREADS];
            
            long start = System.nanoTime();
            for (int i = 0; i < THREADS; i++) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < OPERATIONS / THREADS; j++) {
                        counter.increment();
                    }
                });
                threads[i].start();
            }
            
            for (Thread t : threads) {
                t.join();
            }
            long time = System.nanoTime() - start;
            
            System.out.println("ReentrantLock counter: " + counter.getCount() + 
                             ", Time: " + time / 1_000_000 + "ms");
        }
    }
}
```

---

## 5. Advanced ReentrantLock Features

### 5.1 Condition Variables

```java
public class ConditionVariables {
    /*
     * Condition variables provide more flexibility than wait/notify:
     * - Multiple conditions per lock
     * - More precise signaling
     * - Interruptible waits
     * - Timeout support
     */
    
    private static class BoundedBuffer<T> {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();
        private final T[] buffer;
        private int putPos = 0;
        private int takePos = 0;
        private int count = 0;
        
        @SuppressWarnings("unchecked")
        public BoundedBuffer(int capacity) {
            buffer = (T[]) new Object[capacity];
        }
        
        public void put(T item) throws InterruptedException {
            lock.lock();
            try {
                while (count == buffer.length) {
                    System.out.println("Buffer full, waiting to put: " + item);
                    notFull.await();
                }
                buffer[putPos] = item;
                putPos = (putPos + 1) % buffer.length;
                count++;
                System.out.println("Put: " + item + ", Count: " + count);
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }
        
        public T take() throws InterruptedException {
            lock.lock();
            try {
                while (count == 0) {
                    System.out.println("Buffer empty, waiting to take");
                    notEmpty.await();
                }
                T item = buffer[takePos];
                buffer[takePos] = null;
                takePos = (takePos + 1) % buffer.length;
                count--;
                System.out.println("Take: " + item + ", Count: " + count);
                notFull.signal();
                return item;
            } finally {
                lock.unlock();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(5);
        
        // Producer
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    buffer.put(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Consumer
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    Integer value = buffer.take();
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        producer.start();
        consumer.start();
        
        producer.join();
        consumer.join();
    }
}
```

### 5.2 Lock Inspection Methods

```java
public class LockInspection {
    
    public static void inspectLock(ReentrantLock lock) {
        System.out.println("Lock Information:");
        System.out.println("  Is locked: " + lock.isLocked());
        System.out.println("  Is held by current thread: " + lock.isHeldByCurrentThread());
        System.out.println("  Hold count: " + lock.getHoldCount());
        System.out.println("  Queue length: " + lock.getQueueLength());
        System.out.println("  Has queued threads: " + lock.hasQueuedThreads());
        System.out.println("  Is fair: " + lock.isFair());
        System.out.println("  Has waiters (notEmpty): " + 
                          lock.hasWaiters(lock.newCondition()));
    }
    
    // Debugging deadlocks
    private static class DeadlockDetector {
        private final ReentrantLock lock1 = new ReentrantLock();
        private final ReentrantLock lock2 = new ReentrantLock();
        
        public void method1() {
            lock1.lock();
            try {
                System.out.println("Method1: Acquired lock1");
                Thread.sleep(100);
                
                // Try to acquire lock2
                if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Method1: Acquired lock2");
                    } finally {
                        lock2.unlock();
                    }
                } else {
                    System.out.println("Method1: Could not acquire lock2");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
            }
        }
        
        public void method2() {
            lock2.lock();
            try {
                System.out.println("Method2: Acquired lock2");
                Thread.sleep(100);
                
                // Try to acquire lock1
                if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println("Method2: Acquired lock1");
                    } finally {
                        lock1.unlock();
                    }
                } else {
                    System.out.println("Method2: Could not acquire lock1");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock2.unlock();
            }
        }
    }
}
```

---

## 6. Best Practices and Common Pitfalls

```java
public class LockBestPractices {
    
    /*
     * Best Practices:
     * 
     * 1. Always unlock in finally block
     * 2. Use tryLock for non-blocking operations
     * 3. Prefer synchronized for simple cases
     * 4. Use ReentrantLock when you need advanced features
     * 5. Avoid holding locks while calling external code
     * 6. Use condition variables instead of wait/notify
     * 7. Be careful with fairness (performance impact)
     * 8. Consider performance overhead of ReentrantLock
     * 9. Use lock() methods consistently
     * 10. Use lock interruption when appropriate
     */
    
    // Good Practice: Lock in finally
    private static class GoodLockExample {
        private final ReentrantLock lock = new ReentrantLock();
        private int value = 0;
        
        public void updateValue(int newValue) {
            lock.lock();
            try {
                // Critical section
                value = newValue;
                // Any code here is safe
            } finally {
                // Always unlock
                lock.unlock();
            }
        }
    }
    
    // Bad Practice: Not unlocking
    private static class BadLockExample {
        private final ReentrantLock lock = new ReentrantLock();
        private int value = 0;
        
        public void updateValue(int newValue) {
            lock.lock();
            // Critical section
            value = newValue;
            // What if an exception occurs here?
            // Lock will never be released!
            if (newValue < 0) {
                throw new IllegalArgumentException("Value must be positive");
            }
            lock.unlock(); // This may never be reached
        }
    }
    
    // Common Pitfall: ReentrantLock with Thread.stop()
    private static class ThreadStopPitfall {
        // DON'T DO THIS!
        public void dangerous() {
            ReentrantLock lock = new ReentrantLock();
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    // Long running operation
                    while (true) {
                        // Do work
                    }
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
            
            // This will leave the lock in an inconsistent state
            thread.stop(); // Deprecated and dangerous
        }
        
        // DO THIS INSTEAD!
        public void safe() {
            ReentrantLock lock = new ReentrantLock();
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    lock.lock();
                    try {
                        // Do work in small increments
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    } finally {
                        lock.unlock();
                    }
                }
            });
            thread.start();
            
            // Clean interruption
            thread.interrupt();
        }
    }
}
```

---

## 7. Comprehensive Example: Bank Account with ReentrantLock

```java
public class BankAccountExample {
    
    private static class BankAccount {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition sufficientFunds = lock.newCondition();
        private double balance;
        private final String accountNumber;
        private final List<Transaction> transactions = new ArrayList<>();
        
        public BankAccount(String accountNumber, double initialBalance) {
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
        }
        
        public void deposit(double amount) {
            lock.lock();
            try {
                balance += amount;
                transactions.add(new Transaction("DEPOSIT", amount, balance));
                System.out.println(Thread.currentThread().getName() + 
                                 " deposited: " + amount + 
                                 ", New balance: " + balance);
                sufficientFunds.signalAll();
            } finally {
                lock.unlock();
            }
        }
        
        public boolean withdraw(double amount, long timeout, TimeUnit unit) 
                throws InterruptedException {
            lock.lock();
            try {
                while (balance < amount) {
                    System.out.println(Thread.currentThread().getName() + 
                                     " waiting for funds. Current balance: " + balance);
                    if (!sufficientFunds.await(timeout, unit)) {
                        System.out.println(Thread.currentThread().getName() + 
                                         " timeout waiting for funds");
                        return false;
                    }
                }
                balance -= amount;
                transactions.add(new Transaction("WITHDRAW", amount, balance));
                System.out.println(Thread.currentThread().getName() + 
                                 " withdrew: " + amount + 
                                 ", New balance: " + balance);
                return true;
            } finally {
                lock.unlock();
            }
        }
        
        public boolean transfer(BankAccount target, double amount, long timeout, TimeUnit unit) 
                throws InterruptedException {
            // Prevent deadlock by ordering locks
            BankAccount first = this.hashCode() < target.hashCode() ? this : target;
            BankAccount second = this.hashCode() < target.hashCode() ? target : this;
            
            first.lock.lock();
            try {
                if (first != second) {
                    if (!second.lock.tryLock(timeout, unit)) {
                        return false;
                    }
                    try {
                        return doTransfer(target, amount);
                    } finally {
                        second.lock.unlock();
                    }
                } else {
                    return doTransfer(target, amount);
                }
            } finally {
                first.lock.unlock();
            }
        }
        
        private boolean doTransfer(BankAccount target, double amount) {
            if (balance < amount) {
                return false;
            }
            balance -= amount;
            target.balance += amount;
            
            // Record transactions
            transactions.add(new Transaction("TRANSFER_OUT", amount, balance));
            target.transactions.add(new Transaction("TRANSFER_IN", amount, target.balance));
            
            System.out.println(Thread.currentThread().getName() + 
                             " transferred: " + amount + 
                             " from " + accountNumber + 
                             " to " + target.accountNumber);
            return true;
        }
        
        public void printStatement() {
            lock.lock();
            try {
                System.out.println("=== Account Statement: " + accountNumber + " ===");
                System.out.println("Current Balance: " + balance);
                System.out.println("Transactions:");
                for (Transaction t : transactions) {
                    System.out.println("  " + t);
                }
            } finally {
                lock.unlock();
            }
        }
        
        public double getBalance() {
            lock.lock();
            try {
                return balance;
            } finally {
                lock.unlock();
            }
        }
        
        private static class Transaction {
            private final String type;
            private final double amount;
            private final double balance;
            private final long timestamp;
            
            public Transaction(String type, double amount, double balance) {
                this.type = type;
                this.amount = amount;
                this.balance = balance;
                this.timestamp = System.currentTimeMillis();
            }
            
            @Override
            public String toString() {
                return String.format("[%s] %s: %+.2f, Balance: %.2f", 
                                   new Date(timestamp), type, amount, balance);
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        BankAccount account1 = new BankAccount("ACC-001", 1000.0);
        BankAccount account2 = new BankAccount("ACC-002", 500.0);
        
        ExecutorService executor = Executors.newFixedThreadPool(10);
        
        // Concurrent deposits
        for (int i = 0; i < 5; i++) {
            final int id = i;
            executor.submit(() -> {
                account1.deposit(100 + id * 50);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Concurrent withdrawals with timeout
        for (int i = 0; i < 5; i++) {
            final int id = i;
            executor.submit(() -> {
                try {
                    boolean success = account1.withdraw(200, 2, TimeUnit.SECONDS);
                    System.out.println("Withdraw " + id + " success: " + success);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Concurrent transfers
        for (int i = 0; i < 3; i++) {
            final int id = i;
            executor.submit(() -> {
                try {
                    boolean success = account1.transfer(account2, 150, 1, TimeUnit.SECONDS);
                    System.out.println("Transfer " + id + " success: " + success);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        
        // Print statements
        account1.printStatement();
        account2.printStatement();
        
        System.out.println("\nFinal balances:");
        System.out.println("Account 1: " + account1.getBalance());
        System.out.println("Account 2: " + account2.getBalance());
    }
}
```

---

## 8. Summary: When to Use What

```java
public class LockSelectionGuide {
    /*
     * Decision Tree:
     * 
     * 1. Is it a simple case with low contention?
     *    → Use synchronized
     * 
     * 2. Do you need advanced features?
     *    a. Need to try lock without blocking? → ReentrantLock.tryLock()
     *    b. Need timeout on lock acquisition? → ReentrantLock.tryLock(timeout)
     *    c. Need interruptible lock? → ReentrantLock.lockInterruptibly()
     *    d. Need multiple condition queues? → ReentrantLock.newCondition()
     *    e. Need fair lock ordering? → ReentrantLock(true)
     * 
     * 3. Need read-write separation?
     *    → Use ReadWriteLock
     * 
     * 4. Need optimistic locking?
     *    → Use StampedLock
     */
    
    // Comparison chart
    /*
     * Feature              | synchronized | ReentrantLock | ReadWriteLock | StampedLock
     * ---------------------|--------------|---------------|---------------|-------------
     * Lock acquisition     | Automatic    | Manual        | Manual        | Manual
     * Lock release         | Automatic    | Manual        | Manual        | Manual
     * Reentrant            | Yes          | Yes           | Yes           | No
     * Fairness             | No           | Yes           | Yes           | No
     * tryLock              | No           | Yes           | Yes           | No
     * Lock timeout         | No           | Yes           | Yes           | No
     * Interruptible        | No           | Yes           | Yes           | No
     * Multiple conditions  | No           | Yes           | Yes (read)    | No
     * Read/Write separate  | No           | No            | Yes           | Yes
     * Optimistic read      | No           | No            | No            | Yes
     * Performance          | Best         | Good          | Good          | Best
     * Complexity           | Low          | Medium        | Medium        | High
     * Use when             | Simple cases| Advanced needs| Read-heavy    | Mostly reads
     */
}
```

---

## Summary

**ReentrantLock** is a powerful and flexible locking mechanism in Java that offers features beyond intrinsic synchronization:

1. **Reentrancy**: The same thread can acquire the lock multiple times
2. **Fairness**: Configurable FIFO ordering
3. **tryLock**: Non-blocking lock acquisition with timeout
4. **Interruptibility**: Can interrupt waiting threads
5. **Condition Variables**: Multiple condition queues for finer control
6. **Lock Inspection**: Debug capabilities

**When to use ReentrantLock vs synchronized:**
- Use **synchronized** for simple cases with low contention
- Use **ReentrantLock** when you need advanced features like timeout, interruptibility, or condition variables
- Use **ReadWriteLock** for read-heavy scenarios
- Use **StampedLock** for high-performance optimistic locking

**Best Practices:**
- Always unlock in finally blocks
- Use tryLock to avoid deadlocks
- Consider fairness implications on performance
- Use condition variables instead of wait/notify
- Be cautious with lock inspection in production