Introduction
------------

In this assignment you are asked to model the process of trucks arriving at a container terminal 
gate, moving to a handling location where they can either receive or deliver a container from a 
stack crane, and driving back to the exit gate to exit the terminal. The modelling of this system 
should be done in Java, and the result to be delivered is the Java application and source code
as well as a short report describing the approach and answering the main research question.

Problem formulation
-------------------
Trucks arrive at the terminal in the pattern described in the attached file TruckActivity.xlsx. A 
DLVR or RECV move is indicated from the point of view of the terminal: DLVR means the truck 
comes to the terminal to pick up a container, and RECV indicates the terminal receives a 
container, so the truck brings one in.

The entry gate consists of several lanes where an arriving truck can be processed. Each gate 
lane has a separate queue associated with it. At each lane, the processing time of a truck in 
minutes is described by the random variable X1, where X1 ~ Gamma(9, 3), where 9 is the shape 
parameter α and 3 is the rate parameter β, and E(X1)= α/ β. 

The truck then proceeds to one of 10 stack modules. Driving from the gate to the stacks or vice 
versa takes 3 minutes. The stack modules can be represented as a system of 10 servers, having 
one single queue. Each server is able to handle one truck at a time. Any truck can be served by 
any of the servers. The service time X2 ~ Gamma(4, 2).

After service at the stack module, the truck drives to the exit gate. Handling time at the exit 
gate X3 ~ Gamma(3, 3). As with the entry gate, each gate lane has a separate queue associated 
with it.

The terminal operator has indicated that the maximum queue length at any point during the 
day should not exceed 30 trucks.

Assignment
----------

How many lanes at the entry and exit gate would you advise the terminal to use?