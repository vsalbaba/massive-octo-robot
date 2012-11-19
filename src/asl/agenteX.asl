// Agent agenteX in project envtest

/* Initial beliefs and rules */
engage_distance(200).

/* Initial goals */

!radarsweep.
!drive.
/* Plans */

+!drive <- heading(300).

+!radarsweep <- radarsweep; .wait(500); !radarsweep.

+location(X, Y)[source(percept)] <- .print("position!").