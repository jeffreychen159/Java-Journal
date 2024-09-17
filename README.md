# 3500 PA05 Project Repo

![Java Journal](https://github.com/user-attachments/assets/bd85ecc3-1971-4390-82e5-68e509830f10)

# App Pitch
An interactive calender app with the ability to see and create events and tasks,
along with seeing diagnostic information for the week, changing the theme of the calender,
creating your own notes, and even creating your own custom theme. 
The app also allows for setting of limits to commitments,
and viewing tasks separately in the task queue!

# Solid Principles

Single Responsibility: We have a lot of code that follows this rule. In our controller, we have our main controller which works for our Bujo application. But for each popup, we had a controller which controlled each of the popups. This follows single responsibility as we have a single class that controls each popup window. Furthermore, for our model, we have a class for each item in our journal. For example, by separating the Event from the Tasks, we are able to have single responsibility for event items and task items.

Open-Closed Principle
Our program is open for extension because we are able to easily implement a new type of journal item into our application, we are able to use our JournalItem abstract method which has fields that represent basic information on a journal item. We won’t need class editing to the JournalItem class in order to implement a new type of journal item.

Liskov Substitution: While we don’t have interfaces to really solidify Liskov Substitution, we have a model of Liskov Substitution in action in our controller. In our code, we have a Bujo controller which is our main controller for the program. In this controller, while we don’t have subclasses, we have fields of these controllers which we use for popups. According to Liskov Substitution, if our popup controllers were removed and replaces with the main class, our code will still function. We just won’t have popups for our journal. 

Dependency Inversion 
Our program makes use of dependency injection by utilizing 
dependency injection to accept the required objects in the  
constructor to allow execution. This is shown in the use of passing a stage into serveal of our popup controller classes. This allows individual parts of our code to operate in isolation of eachother and avoing high coupling.

# Feature Extension

If we were to extend our program to add an additional feature, one example of a feature that we could implement is having Mind Changes. We could implement this by making it so that the TextFlows of a journal item would have an on-action of opening up a new popup that will prompt us with a bunch of text fields for editing specific details. In the class that is representing this popup, we will pass the journal item so that the fields of the journal item object can be edited. After taking in user input with text fields, there will be a submit button that sets the fields of the journal item object to the new ones that the user gave, and our refresh method should display the new changes made to the journal item.
