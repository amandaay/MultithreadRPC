# Executive Summary

## Assignment Overview

### The assignment overview shows how well you understand the assignment.

In this assignment, I had a comprehensive understanding of remote procedure call (RPC) and their role in client-server communication. Specifically, I observed how RPC streamlines the process, involving client can go through client stub, packing the message to be sent to the remote procedural object, then to the RPC runtime to the client OS. Then invokes the server via TCP transport then reaches the server OS then through RPC runtime unmarshals the message then through server skeleton to the server then marshals the message and sends back to the client. In this project, RMI in java handles all of the operation including multithreading. The focus was to transition from the socket-based connections to an RPC-based framework, offering more efficient and structured client-server interactions. First, I adopted an RPC system using Java RMI, then implement multithreading in the server to handle concurrent requests for PUT, GET, and DELETE. Overall, the project goal is to showcase the capabilities of a concurrent RPC-based system to manage a real-world scenario where numerous clients can engage with the server.

## Technical Impression

### The technical impression section helps to determine what parts of the assignment need clarification, improvement, etc., for the future.

While working on this assignment, I gained valuable insights on the intricacies of Remote Procedure Calls (RPC) and its role in the realm of distributed systems. By grasping the concept of RPC, I have proactively sought resources such as online tutorials to demystify its complexities, especially in the Java RMI framework. I have come to the realization that RMI inherently provide multithreading support. Consequently, I have shifted my focus from implementation of “Runnable” to ensuring the thread safety and synchronize access of those resources I have shared to my server. Notably, I encountered the deprecation of RMIC and its registry. So, I had to trust the RMI capacity to support my implementation.

In terms of clarification for the assignment, for students with limited prior exposure to distributed systems, additional background information on RMI or even a starter kit for the project, would have been immensely beneficial in avoiding potential confusion. In addition, the project has outlined a potential approach for using thread pools, but since RMI already handles multithreading, it was rather confusing that the assignment with a specific session dedicated for students to implement multithreading. A suggestion would be a specification of how RMI already handle multithreading and we would need to ensure thread safety and access shared resources from the beginning.

Overall, the project introduced a more structured and efficient approach to remote method invocation. This transition streamlined client-server interactions, making the communication process more organized. And the project underscores the significance of synchronization and mutual exclusion in the realm of multi-threaded system. While addition clarification on the assignment can be made, I still thought the project was a valuable learning experience and shed light in the world of RPC and multi-threading.
