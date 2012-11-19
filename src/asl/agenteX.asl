// Agent agenteX in project envtest

/* Initial beliefs and rules */
engage_distance(200).

/* Initial goals */

!radarsweep.
!drive.
/* Plans */


+!drive <- heading(300).

+heading(X) <- .print("heading!").

+!radarsweep <- radarsweep; .wait(1000); !radarsweep.

+location(X, Y)[source(percept)] <- .print("position!").