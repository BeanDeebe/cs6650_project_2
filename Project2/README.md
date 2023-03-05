How to build server and client codes:
    First, run "java -jar ServerRMI.jar" from the command line.
    Then, run "java -jar ClientRMI.jar" from the command line.
    Note: if you try to run the client first without the server running, you will receive an error and be unable to do so.

Executive Summary:
    This assignment was, in my opinion, a big “connect the dots” assignment for me. Up to this point, I was curious how RPC and RMI would work given concurrent client requests. I understood with Project 1 just how we could do this with sockets… But what if we didn’t use TCP? This assignment helped to open my eyes to that way of doing things.
    The assignment required us to enable our server to handle multiple clients who would all be making requests at the same time. While we still kept the key-value store as the main data structure we’d be interacting with, the method in which we would be connecting client and server would be different. This time, we used Java RMI to remotely call methods on the server from the client.
    As far as the technical impression of the assignment, I found implementing an RMI interface to be the best way to go about enabling both client and server to have an idea of what operations could be performed remotely, without having to duplicate code. Setting the server up was straight forward enough, but the client class proved to be an interesting problem when trying to guarantee mutual exclusivity. In the end, I decided to use the ‘synchronized’ command on the stub to wrap the switch statement to ensure this.
    This assignment also introduced me to creating jar files, which I hadn’t ever done before. So, learning how to create manifest files for each jar with the appropriate parameters was a fun and interesting exercise as well.


