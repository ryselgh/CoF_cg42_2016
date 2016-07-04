**Council of Four**
===================

This repository contains our implementation of the board game "[Council of Four](https://boardgamegeek.com/boardgame/173101/council-four)" distributed by CranioCreation. 
This file will help you to understand  how we built it.

We'll explain how to run it properly and some particular features.  

----------

![enter image description here](http://www.asaboardgamer.com/wp-content/uploads/2015/11/CouncilofFour1-e1446400291992.jpg)

-------------
Usage Info
-------------

### **Start Server**

>In order to play, you first need to start the server, to do that run 
`/server/src/main/java/com/server/controller/MainServer.java`
and choose what connection the server has to receive :
> - **Socket**
> - **RMI**

###**Start Client**

>Once the server runs you're be able to play a game. So start the client running
`/client/src/main/java/com/client/controller/MainClient.java`

First of all you have to choose an interface beetween
> - **GUI**
> - **CLI**

 Then choose the connection type adapting to the server one

Follow the instructions on screen and join the game!



ScreenShots
-----------------

![enter image description here](https://lh3.googleusercontent.com/-GRDivHdSE4Q/V3bFHoWjM0I/AAAAAAAAAMw/hrDidd9Udg4NZ1cqbEILq1RTxqj4fZGIwCLcB/s0/Senza+titolo.tiff "Senza titolo.tiff")
the main window 

![enter image description here](https://lh3.googleusercontent.com/-eiN_tu3s_A8/V3ovmrYYR1I/AAAAAAAAAOo/04yjA7afOYAb8LukYPspbTSoS4TDV8TQgCLcB/s0/CLI12.tiff "CLI12.tiff")![enter image description here](https://lh3.googleusercontent.com/-5D0Rhw0tSIg/V3ov1w-8SuI/AAAAAAAAAOw/CdmLjdpC2hYyRJhN-zEH9-COTQl2ce6jwCLcB/s0/CLI22.tiff "CLI22.tiff")






Features
----------------

We've decided to use three modules in our project:
> - **Server**
> - **Client**
> - **Communication**

All the logic is in server.
It has been stratified. We have three levels:
> - **Lobby**, that creates the game (or more) and manages it (them).
> - **GameHandler**, that manages a single game.
> - **ClientHandler**, dedicated to I/O with Client.


Client has no game logic.
Communication package is the core of our Communication protocol involved in Server/Client interaction.
Hiding game logic to client we ensure a protection layer to the game, making it harder to find exploitable flaws. 
It also makes it possible to have a lightweight client.


![enter image description here](https://lh3.googleusercontent.com/-nBhYIZYTSD4/V3bFvjvzMMI/AAAAAAAAAM4/1yai9_A2FW8wpcd-VlUVY8qNZBZns_MIQCLcB/s0/Untitled+Diagram.png "Untitled Diagram.png")

We've made large use of MVC pattern in threads communication.

Map
------------

Maps are defined using **XML** language.
You can choose between 8 maps ( the same of the board game) or import a custom one using its filepath.
The admin is the only one who can change the map in a room.
When the game starts the parser imports the selected map.


Lobby
-----------

**Lobby** is the advanced function we implemented in our project.
It can manage more than a game as you can see in the image.
Every **Room** is a game in which the Admin can set the game rules.
The admin is the one who creates the room; if he leaves, another player will take his place.

![enter image description here](https://lh3.googleusercontent.com/-_NiPMYHJdk4/V3f9vPAnLpI/AAAAAAAAAN0/d3iK_NAwYY0TuMcTyzO2S5fZSqa79gC2wCLcB/s0/GUI1.tiff "GUI1.tiff")





Credits
----------
Group members (**cg42**):

- Niccolò Pozzolini
- Saverio Ruggieri
- Francesco Vona