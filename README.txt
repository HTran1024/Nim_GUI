Name:	Huy Tran
Course:	CPSC 223J
Date:	October 28, 2016
Email:	huytran2012@csu.fullerton.edu
File:	Nim_GUI.java

Nim_GUI:
	This file contains what is needed to run the Nim game in GUI form. The file contains three classes: PebbleRow,
Nim_GUI, and Nim_BrainLVL_0. The PebbleRow class generates random numbers and stores them in an array called rowOfPebbles
and grabs the total pebble amount as well to determine when to end the game. Nim_GUI builds the frame or window for the
game. It prints a greeting then tells a short instruction of the game. In the center is the board where a randomly generated
amount of rows and pebble buttons are created. It also has a quit button at te bottom of the layout. The Nim_BrainLVL_0 acts
as the brain or computer of the game. It randomly picks a row (that is not empty) and randomly picks an amount of pebbles
from that row. The quit button is set to simply quit when the button is pressed and the pebble buttons are set to not only
disable onced pressed but also the buttons on the right. Once all the buttons are disabled, the program will recognize this
through totalPebAmount, which tracks all the pebbles or buttons available, and will set the program to quit.

Other Notes:
	I wanted to create a message telling who won but ran into problems getting the message to turn up. Also, due to the
random nature of Nim_BrainLVL_0, the game can end abruptly if the brain suddenly picks the first pebble of the array but only
if there is one row left. If the brain ends up winning the game, it does not show those butttons being disabled.