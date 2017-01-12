# Guitar Chord Trainer

Welcome to the Guitar Chord Trainer. The point of this project is to help aid those who are learning to play guitar practice their chord changes. It will do this by adding a bit of randomness to the process.

This project is in a rather infantile stage the moment. I am currently mocking it up using Java's Swing toolkit, but I fully admit I do not know if the end result will be in java. I have kicked the tires at potentially making it a Django application or something along those lines. The reason for this uncertainty is that I could see a lot of people practicing guitar without a computer in front of them. So providing a means to use this on their phones, tablets and things of that nature would be beneficial to them.

However, here is some of the functionality currently implemented in this project.

* You can choose the number of chords you'd like to play
* You can choose the time between chords (so you can set a maximum amount of time to switch)
* By default you can select any of the open major or minor chords
* If you want to load some custom chords, you can specify a directory and the program will load them in and make them available for selection

Note: If you are loading custom chords, the file type must have the dimensions of 340x416. If they are bigger than that they will not be displayed properly. I am currently working on resizing them when they are loaded into the program.