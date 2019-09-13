# Android-OOP Sprint Challenge

## Instructions

**Please read this entire README to make sure you understand what is expected of you before you begin.**

You've learned a bunch of things so far, and this Sprint Challenge will use a lot of what you've already learned while mixing in some of the OOP Concepts we've learned this week.

Let's remember to follow best practices such as using a  good, consistent code style, commiting often, and writing good commit messages. Those things will be part of your life as an Android developer, so let's start practicing now. We'll be checking this out and it can factor into your score (in addition to meeting the challenge requirements).

Fork this repository and clone your fork to your computer. Create your Android Studio project in this cloned fork repository folder, then commit and create a pull request. Commit appropriately as you work. When finished, push your final project to GitHub and comment on the pull request to indicate that your project is complete.

You have *3 hours*, and you should work *independently* — looking things up (search, notes) is all fair game. And questions about *process* / *logistics* (i.e. if you have a hard time opening/saving to GitHub) are fair game too.

Good luck!

## Requirements

Let's build a Master/Detail project that uses MVVM, Inheritance, Interfaces, and Generics.

We're going to use the Age of Empires II API (You might be experiencing of Sci-Fi and games overload, but good API's that don't require authentication and/or an API key are hard to come by!). It's located at:

[https://age-of-empires-2-api.herokuapp.com/docs]

Here's an outline of the steps with some hints:

1. Create a new project using the Master/Detail template. We used this in class, AND3's Abstraction, Interfaces, and Polymorphism lesson). The template should create three classes: ItemDetailActivity, ItemListActivity, and ItemDetailFragment. You'll also be given a package with some Dummy data. We're going to replace the dummy data in the next step.

2. Test out the different endpoints at [https://age-of-empires-2-api.herokuapp.com/docs]. Notice the JSON structure, in particular the attributes. Copy/paste a couple of JSON Objects representing each of the endpoints (Civilizations, Units, Structures, and Technologies). For now, you can store these objects in a scratch file until you are ready to convert it to objects from your model classes. You should take at least 2 objects of each type.

3. We want to use the MVVM or MVP style, so create packages for your model, view, and viewmodel/presenter. Name your packages appropriately, and put your generated classes in a place that makes sense for the architecture. If you choose MVP, remember that you'll probably need a reference to the View so that you can call functions on it when the data needs to be updated.

4. Build your model classes. The API we are using has endpoints for civilizations, units, structures, and technologies. We want to create a list containing all the different API objects, and we want to be able to display interesting data in our RecyclerView.
    - Examine the data you copied in step 2. to figure out how to extract attributes from each class to construct your class hierarchy. We want to be able to put objects of each type in the same list structure. You don't have to use every single attribute; it may be easier to ignore any attributes that are themselves JSON Objects or JSON Arrays (like "cost", for example), but that is up to you.
    - Create a class hierarchy to model the API Objects.
    - In your class hierarchy, create description function that will give us information specific to each type of object, but we don't want to have to check the type (in other words, we want to use polymorphism).
    - In addition to the attributes provided in the data set, add a Boolean variable that tracks some state - choose something that interests you, perhaps `isFavorite` or `isInUse` - about the API objects. Every type of object should have this state (where is the best place to put it?). You can provide a default value of false.
    - Go to your layout files and add a TextView or other UI element to display the description you have created (`item_list_content.xml` and `item_detail.xml` are good places). Also add UI to display and change the state you have added. You might use a Button, an ImageView, a CheckBox, or something else of your choosing, just make sure you can both change the value of the object and display the value after it has changed.

	
5. Create your ViewModel/Presenter.
    - You don't have to use Data Binding for this part, just create a ViewModel/Presenter that will hold your data.
    - Convert the data you collected in 2 into a list of API objects held in the ViewModel/Presenter
    - Replace all the DummyItem object references from the project template with the objects you have created.
	You'll have to create a instance of the ViewModel/Presenter in your ItemListActivity to do this.

6. In the ItemDetailFragment, you will create a way to let the attached Activity show a Toast message when the state has changed on the detail.
    - Inside your Fragment class, create an interface that has a single method. This method should pass back an API object.
    - Make the two Activities implement your Fragment interface. In the interface function for each Activity, show a Toast that
	displays information about the item that was changed and the Activity that is showing the Toast.
    - In your Fragment's onAttach method, store the Activity in a member variable so that you can call it when your UI element is 
	activated (onClickListener or otherwise).
	
    - In your Fragment's onDetach method, release the Activity by setting the member variable to null.

## Rubric
1. You have successfully created a Master/Detail Activity that displays the API Objects.
2. You have created a class hierarchy that allows you to put all API objects together in a list and to call methods on each object polymorphically.
3. You have used MVVM or MVP architecture.
    - You have created packages.
    - You have placed your Model, View, and ViewModel/Presenter in the appropriate packages.
    - You have successfully separated concerns. Business logic is in the right class(es).
4. You have created an interface in your Fragment.
    - You have an interface function that returns the item to the class that implements the interface.
    - You have attached and detached correctly in the Fragment.
    - You are showing a Toast in each Activity that shows information about the item and the Activity.

STRETCH GOALS (You don't have to do these in order. Choose whichever one(s) you like!)
1. Try to use Data Binding or LiveData in your ViewModel/Presenter. We haven't covered LiveData in class yet, so it may be very challenging.
2. Create a retrofit package and build a set of retrofit calls to get data from the server instead of the provided data. In your ViewModel/Presenter, set up calls to fetch the data.
3. Improve the layouts for displaying the objects (item_list_content.xml and activity_item_detail.xml) with colors, fonts, or images.