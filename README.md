# JobSequence
The Java code takes in input Job values in the following format:

a => b

For this scenario, certain input assumptions are made. Which are as follows:
1) All the dependencies are considered after the '=>' symbol.
2) Only the character directly after the '=>' would be considered as a Job dependency. for example, if an input occurs such as follows, 'a => b =>c' then the code will create a Node with Value 'a' having a dependency for 'b' while 'c' would not be considered. This is due to the fact that the instructions for the coding test do not mention anything about a conjoined dependency string.

The input values can be typed in directly or can be read through a text file.

Although some constrainst are placed to ensure that the user inputs the correct file name and instructions are provided to remind the user the format of the Jobs accepted and the their dependencies, more emphasis was placed on the task of ensuring Job dependent sequence is maintained, as compared to making sure that the user always inputs a single character for a job value.

Each Job is then placed in a Linked List Node. Following this, a seperate method is called, which attaches the dependencies of each of the nodes. The reason why the dependencies are attached seperately for this case is because the linkedList method ended up creating duplicate nodes for Jobs and the dependencies when executing Node insertion and dependency attachment together.
By seperating the two processes, the Job Nodes are linked together with existing nodes, instead of creating duplicates. This also means that if a dependency exists for a Job that was never inserted in the linked List from the start then such a dependency would be ignored. 
e.g:
a => s
b => c
c => a

result: a c b
here s is ignored in the final result sequence as 's' was never inserted as a job and was only taken in as a dependency.

