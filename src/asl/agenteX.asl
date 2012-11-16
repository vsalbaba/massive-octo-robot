// Agent agenteX in project envtest

/* Initial beliefs and rules */


/* Initial goals */

!radarsweep.
!drive.
/* Plans */

+!drive <- heading(300).

+!radarsweep <- radarsweep; .wait(500); !radarsweep.

+location(X, Y)[source(percept)] <- .print("position!").