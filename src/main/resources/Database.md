The **ACID properties** in SQL are key principles that ensure reliable transactions in a database. ACID stands for **Atomicity, Consistency, Isolation,** and **Durability**. Let's go through each property with an example.

### 1. Atomicity
**Atomicity** means that a transaction is **all or nothing**: either the entire transaction is completed successfully, or none of it is applied to the database. If any part of the transaction fails, the database should remain unchanged.

#### Example:
Suppose you are transferring money between two accounts:
```sql
BEGIN TRANSACTION;

UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;  -- Deduct $100 from Account 1
UPDATE accounts SET balance = balance + 100 WHERE account_id = 2;  -- Add $100 to Account 2

COMMIT;
```
If the deduction from Account 1 succeeds but the addition to Account 2 fails, **Atomicity** will ensure that both actions are rolled back, leaving both accounts unchanged.

### 2. Consistency
**Consistency** ensures that a transaction brings the database from one valid state to another, maintaining data integrity. All rules, constraints, and triggers defined in the database must be preserved before and after the transaction.

#### Example:
Suppose you have a rule that account balances should never be negative. If a transaction attempts to withdraw more than an account's balance, it would be prevented from proceeding to ensure consistency:
```sql
BEGIN TRANSACTION;

UPDATE accounts SET balance = balance - 200 WHERE account_id = 1;  -- Attempt to withdraw $200 from Account 1 with only $150 balance

-- Fails due to insufficient funds; transaction is rolled back to keep database consistent

ROLLBACK;
```
Here, the transaction would fail because it violates the rule, keeping the database consistent.

### 3. Isolation
**Isolation** ensures that concurrently running transactions do not affect each other. Each transaction should operate independently, as if it’s the only transaction in the system, until it is completed. Different isolation levels (like Read Uncommitted, Read Committed, Repeatable Read, and Serializable) allow control over the degree of visibility between transactions.

#### Example:
Suppose two users are transferring money from the same account at the same time. Isolation ensures that the balance updates do not interfere with each other:
```sql
-- Transaction 1 (User A)
BEGIN TRANSACTION;
SELECT balance FROM accounts WHERE account_id = 1;  -- Reads balance as $500
UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;  -- Deducts $100
COMMIT;

-- Transaction 2 (User B)
BEGIN TRANSACTION;
SELECT balance FROM accounts WHERE account_id = 1;  -- Should also read $500 if isolation is not controlled
UPDATE accounts SET balance = balance - 200 WHERE account_id = 1;  -- Deducts $200
COMMIT;
```
With proper isolation, one transaction will wait for the other to finish, preventing conflicts and data corruption.

### 4. Durability
**Durability** guarantees that once a transaction is committed, the changes are permanently saved, even in the event of a system failure.

#### Example:
After a transaction commits:
```sql
BEGIN TRANSACTION;

UPDATE accounts SET balance = balance + 500 WHERE account_id = 3;  -- Add $500 to Account 3

COMMIT;
```
Once the transaction is committed, the addition of $500 is saved permanently. Even if the system crashes afterward, this change is safe and will remain in the database when it restarts.

### Summary of ACID Properties

| Property   | Description                                                                                         |
|------------|-----------------------------------------------------------------------------------------------------|
| Atomicity  | All operations in a transaction succeed or fail together.                                           |
| Consistency| Database must remain in a consistent state before and after the transaction.                        |
| Isolation  | Transactions run independently, without interference from others.                                   |
| Durability | Once a transaction is committed, changes are permanent.                                             |
