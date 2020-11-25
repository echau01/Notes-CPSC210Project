# Notes

## A note-taking program

For my project, I propose to design a note-taking program.
It will feature:

- Note-taking and saving under a title
- Categories (such as chem, physics etc) under which the notes are saved
- The ability to delete any unneeded notes if so desired.

As the program is a note-taking program, it is obviously
targeted towards fellow students, though it can be used
for more general purposes, such as writing down reminders.

There are a surprising lack of good note taking programs.
I've always wanted to make a note-taking program as a result.

## User Stories
- As a user, I want to be able to create annotated notes,
complete with a title, body and category.
- As a user, I want to be able to select and view my list of note
categories, and their associated notes.
- As a user, I want to be able to delete unneeded categories
and unneeded notes.
- As a user, I want to be to rename these notes and categories.
- As a user, I want to be able to save my notes and categories to file
- As a user, I want to be able to load my notes and categories from file

## Phase 4: Task 2
- A hashmap has been implemented in both CategoryContainer and Category.
- Type Hierarchy: ContainerGUI abstracts away code from CategoryContainerGUI and CategoryGUI, etc.

## Phase 4: Task 3
- If I had more time to work on the project, I would have rebuilt the entire thing from the ground up using the design 
  methods we learned, such as drawing a UML diagram beforehand, to reduce code duplication. As of now, my code has 
  severely bad duplication and no clear way of avoiding this. Had I done the proper planning beforehand, my code could 
  have ended up much cleaner. Coupling seems fair at the moment given the UML diagram, but the structure of my code 
  means a lot of duplication is going on.
