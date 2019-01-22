# Watch It

This project was built to expirement/explore Clean Architecture principles in an Android Application.

Watch It is a client side Android Application that pulls current popular movies and tv shows from **The Movie Database**'s API.
Users can choose to favourite movies and/or tv shows which will save the movie locally on the phone.

## Dependencies Overview
1. **Kotlin Coroutines** for concurrency and for handling asynchronous events
2. **Dagger 2** for dependency injection
3. **ViewModel** for maintaining user state
4. **Room** for data persistence
5. **Navigation from Android Jetpack** for navigation in the BottomNavigationView
6. **Retrofit** and **Gson** for networking
7. **Glide** for image loading
8. **MockK** for unit testing (not implemented yet)

## Project Overview
I have split the application into three distinct layers. These three layers are their own individual modules and are outlined below.

### app module
This is an Android Library that components Android specific dependencies. This layer is dependent on both the **data** and 
**domain** modules outlined further below in this documentation.

I designed this application with an event based programming mindset. I represented all the different type of events that the
view can experience in a Kotlin **sealed class** outlined in the correpsonding contract interfaces. This helps abstract different, 
user events and makes the code easier to understand and read, as well as test.

Within the **app** module resides application components such as Activities, Fragments, and Adapters. Navigation is also 
achieved through Google's **Navigation** library included in Android Jetpack. Other important classes and components within 
the **app** module are:

1. **Presentation Logic**
  - These "Logic" classes are similar to the "Presenter" and "Controller" in traditional MVP and MVC patterns. The logic classes
  have reference to the view (in this case the fragment) and is able to call methods to update the UI. However, rather than 
  having the corresponding repositories be injected into the logic classes as seen in traditional architectures such as MVP,
  I injected specific **domain use cases**. These uses cases outline specific business rules in the application and more often
  than not a *suspend* function that needs to be launched and executed within the logic classes.
  
2. **Dagger**
  - This is self explanatory. This package contains the components and modules necessary to build our application dependencies 
  in all three layers (which is one of the reasons why the app module needs dependencies on the data and domain modules)
  
3. **ViewModel**
  - Rather than the traditional MVVM style that Google is adopting where there is presentation logic in the ViewModels themselves,
  I am using the ViewModels solely as a class that contains UI state - Nothing more, Nothing less. I have abstracted all the 
  presentation logic to the "Logic" classes which then delegates the UI state to the ViewModel.
  
### domain module
The **domain** module is a Java Library and has no Android dependencies in it. This means that this module can be theoretically 
reused within different platforms such as iOS or Windows application. The **domain** module contains these specific classes 
and components.

1. **Use Cases**
  - In the domain module, I attempt to outline every single use case that the user can perform within the application. In 
  the use case classes, I inject repository interfaces outlined in the **domain** layer that are then implemented inthe **data**
  layer which is constructed using dagger in the **app** layer. 
  
2. **Repositories**
  - The domain layer is responsible for creating repository interfaces for the use cases to execute on. These repositories 
  will be implemented somewhere in the data layer.
  
### data module
The **data** module is an Android Library that contains repository implementations, and handles API (Retrofit) and database
creations. For networking, I talk to an API service created using Retrofit, and use Room for data persistence.

## My takeaways from this project
This is my first Android Application built following Clean Architecture principles. In the past, I've worked with projects
built following MVVM and MVP, but really wanted to see the pros of building an application followin Clean principles.
Here are the pros and cons I've personally found with this architecture.

### Pros
1. **Separation of Concern/Decoupling various components**
Because of the three distinct modules, it makes it extremely easy to separate application logic from business logic, from 
data implemetation logic. This is a huge positive in my opinion as is decouples the Android specifics from the hard business
logic. This allows for easy refactoring in the future if necessary, but also leads me to my next point, **Unit Testing**.

2. **Unit Testing**
Although I did not write any Unit Tests for the app, I have had experience writing Unit Tests and following a TDD approach at 
my last coop as an Android Developer. While developing this app, I noticed how easy it was to unit test since Clean 
Architecture promotes so much code decoupling. For example, if I were to test the presentation logic, all I would have to do
is mock the response from a use case execution and verify calls on the view. If i wanted to test the domain layer,
I just had to verify that use cases were calling specific methods on the repositories. Also since I am talking to 
interfaces and contracts, it is extremely easy to mock objects and dependencies and test their functionality.

### Cons
1. **Makes codebase extremely large**
The project I made is quite a simple application. It's a simple CRUD app the fetches data from a network and displays them
in a Recyclerview. However with so much abstraction and decoupling, Clean Architecture can easily make the code quantity 
of your project to grow exponentially. One thing I did not like was how there were specific data models/entities for each layer.
This meant I often had repeated models in each layer, and had to generate mappers to map each mdoel from one layer to another!
This greatly increased my codebase and I felt like there was a lot of redundant code. 

2. **So many Use Cases**
- For a simple CRUD application like this, I had close to 15 Use Case classes. I could see in a large application that has 
many screens and a lot of different functionality to have hundreds of Use Cases. Although this promotes a huge separation of concern,
the codebase just becomes huge and was something that I was completely surprised by.

## TODO/Future Work
A majority of the application functionality is still incomplete. For example, I would like to add a movie details screen that
takes users to another screen if they click on a tile in the recyclerview. I would also like to add searching functionality
so that users can search for movies. However, the main goal of this project was to build an application following 
Clean Architecure, so I am more than satisfied with the fact that I created an applcation with the base functionalities following this new architecture.

Unit Testing. I would 1000% add Unit Tests in the future, and would even maybe approach a TDD approach where I test before I implement.
This is something I definitely plan on adding to the application in the near future.

## Screenshots
**MovieListFragment (left)** and **FavouritesFragment(right)** displaying favourite Tv Shows
<p float="left">
  <img src="Screenshots/Movie%20List%20Fragment.png" width="300" />
  <img src="Screenshots/Favourites%20Fragment.png" width="300" /> 
</p>
