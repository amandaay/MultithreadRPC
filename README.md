# Multi-threaded Key-Value Store using RPC

## Start with a split of 2 different terminals:
One for server and one for client.

### In both terminals
`cd src`

#### Server Terminal
1. Compile with `javac KeyValueStoreServer.java`
3. `java KeyValueStoreServer <Port Number>` 
4. Keep in mind that server will keep running unless user terminates with `cmd + c` or `ctrl + c`

#### Client Terminal
1. Compile with `javac KeyValueStoreClient.java`
2. `java KeyValueStoreClient <IP> <Port Number>`
3. Use the customized command `SHUTDOWN` to terminate the client side. In situation where less than 5 operators are executed, it will prompt you to ensure shutdown is requested.

## Sample Output
<img width="1176" alt="Screenshot 2023-10-21 at 1 46 05 PM" src="https://github.com/amandaay/MultithreadRPC/assets/58647320/a7d8757b-690f-4e8f-bbcd-5d75bdabe760">
<img width="1402" alt="Screenshot 2023-10-21 at 1 46 13 PM" src="https://github.com/amandaay/MultithreadRPC/assets/58647320/c2ae329c-e571-45fd-835e-41a5c28a3e87">

### Note: Executive Summary is attached separately
`ExecutiveSummary.md`
