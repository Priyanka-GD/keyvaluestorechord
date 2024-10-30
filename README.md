#### Adding a New Process:

A new process (ProcessAttr) is created and ready to be added to the list of processes.
##### Adjusting Ranges:

The adjustRange method is implemented to handle the addition of the new process.
It checks for overlaps between the new process and existing processes in the list.
If an overlap is detected, the existing processes' ranges are adjusted:
The end range of overlapping processes is shifted to accommodate the new process if they are adjacent.
The start range of overlapping processes is updated if the new process overlaps at the end.

##### Updating Successor and Predecessor Links:

Upon adding a new process, links between processes are updated to maintain the relationships:
Each process will have a reference to its predecessor (the previous process) and successor (the next process).
The links are adjusted accordingly after range adjustments to ensure the correct order and relationships.