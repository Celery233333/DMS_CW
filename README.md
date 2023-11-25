# DMS_Coursework

## Author
Cheng Qin
20216424

## Compile
run "GameApp.main()" to start the application

## Maintenance
| location | description | reason |
| ---- | ---- | ---- |
|whole project|translate from swing and awt to javafx|basic requirement|
|module-info|declare a jar file as a named Java module|basic requirement|
|whole project|add Maven|basic requirement|
|whole project|delete unused or redundant methods, variables and import packages|basic requirement|
|whole project|tranlate all the static variables to private with getters and setters or public|static is not a good way to code|
|Brick1-3, GameFrame|Rename these classes|basic requirement|
|Wall|split it to Wall, Impact and reset|make class has single responsibility|
|Wall|modify makeSingleTypeLevel and makeChessboardLevel to create half brick at the start and end of even line|fix original bug|
|Reset|modify wallReset method|when restarting the game, reset to level 0, and repair bricks from all levels|
|Brick|split the class to Brick and Crack|make the class has single responsibility|
|Ball1|merge it to Ball class|Ball1 is redundant|
|GameBoard|split it to GameView and GameController classes|make class has single responsibility, and obey the MVC pattern|
|GameView, GameController|split the pause part from them, and create PauseView and PauseController using fxml|make class has single responsibility, obey the MVC pattern|
|DebugPanel, DebugConsole|change to use fxml(DebugView,DebugController)|make class has single responsibility, obey the MVC pattern|
|PauseController DebugController, StartController|position the game, pause, debug, rank and setting menu according to the original window|make the game more reasonable|
|PauseController|modify exit to back to start menu intead of exiting program|make the game more reasonable|

## Extension
| location | description | reason |
| ---- | ---- | ---- |
|BrickFactory|add to call constructor of all kinds of bricks, hide the detail of creating bricks|obey the factory pattern|
|StartView, StartController|add start window to the game|implement the basic requirement, obey the MVC pattern|
|SettingView|add to modify the theme and volume, and show the instruction|implement the basic requirement|
|Rank, RankView, RankController|you can check the high score board in the start menu, and if the final score exceeds the top five, the user will be asked for a name, and if so, a new ranking will be recorded [here](project/src/main/resources/code/dms_coursework/highScore.txt)|implement the basic requirement, obey the MVC pattern|
|Ball, Paddle, all kinds of Bricks, StartController, PauseControlelr and GameApp|add image or background to all the elements and windows, you can check the image [here](project/src/main/resources/code/dms_coursework/image)|implement the basic requirement|
|Wall|modify the makeSingleTypeLevel to add two new levels|implement the basic requirement|
|GameController, Impact|add background sound, start sound, impact sound, brick breaking sound and record-breaking sound. Also, volume can be changed in setting menu, you can check the sound [here](project/src/main/resources/code/dms_coursework/sound)|additional implementation|
|Sprite|add a sprite to hold the panel, it will [stop](project/src/main/resources/code/dms_coursework/image/stop.png), [turn left](project/src/main/resources/code/dms_coursework/image/left.png), [turn right](project/src/main/resources/code/dms_coursework/image/right.png) and move with the paddle|additional implementation|
|AwardBrick, Wall, BrickFactory|add a new kind of [brick](project/src/main/resources/code/dms_coursework/image/award.gif), it is randomly generated and it is broken with a random score of 0-9|additional implementation|
|QustionBrick, BrickFactory, Wall, Impact|add a new kind of [brick](project/src/main/resources/code/dms_coursework/image/question.jpg), it is randomly generated and it is broken with a random effect include shorten and increase the length of paddle, get or lose the ball, increase or reduce the speed of player (the effect will reset when lose ball or pass current level)|additional implementation|
|TNTBrick, BrickFactory, Wall, Impact|add a new kind of [brick](project/src/main/resources/code/dms_coursework/image/tnt.png), it is randomly generated(will not generate at the start and end) and it is broken with an explosion. Explosion will destroy the adjacent two bricks with 0 score|additional implementation|
|GameView, StartController|add a frame to the game, and change the text to new text area|additional implementation|
|all Bricks, GameController|improve whole score system:different score with each bricks, reward scores accoding to remaining balls when passing current level|additional implmentation|

## Summary
### New classes
view: [5 fxml files](project/src/main/resources/code/dms_coursework/controller)<br>
controller: RankControlelr, Start Controller<br>
model: AwardBrick, TNTBrick, QuestionBrick, BrickFactory, Rank, Sprite<br>
GameApp

### modified classes
view: GameView<br>
model: Ball, Brick, CementBrick, SteelBrick, ClayBrick, Crack, Paddle, Wall, Impact, Reset<br>
controller: DebugController, PauseController, GameController