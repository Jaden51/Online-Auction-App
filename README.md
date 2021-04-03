# Online Auction System

## Overview
In today's age, people can buy, sell, pay for everything online. Why not be
able to auction items online. With *app name here*, people will be able to post
items they wish to auction off. When a user posts an item, they will have the option for
how long they wish to put the item up, the starting bid, and the minimum increment.
Other users may place bids and look up items currently up for auction. 

## Features
- Users can **post** items to the online auction
- Users can **place** bids and **buy** items
- Users can **search** up items they are looking for
- Users can see the current **status** of the auction
    - Current bid
    - Minimum increments
    - Time left and more

## Usage
This app will be used by everyday people looking to auction off items who don't
have the time to go to an auction house. 

## Interest
My grandfather was an auctioneer and going to his events were really entertaining,
and I am a huge fan of Storage Wars. In our now digital age, I felt like making
an application which makes auctions easy and accessible to everyone online.

## User Stories
In the context of a seller
- As a user, I want to be able to put an item up for auction (add the item to the auction store and user store)
- As a user, I want to be able to add all the required fields to my item up for auction
- As a user, I want to be able to check the status of my items up for sale
- As a user, I want to be able to save all my items which I posted to the global auction to a file
- As a user, I want to be able to reload all the items in my shop even after quitting the application (so I don't have to post them again) 

In the context of a buyer
- As a user, I want to be able to search and see the list of items to bid on
- As a user, I want to be able to place a bid on an item in the store
- As a user, I want to be able to buy an item if I reach the buyout price of that item

## Phase 4: Task 2
- I introduced a type hierarchy.
- The classes include Store (superclass), AuctionStore, UserStore, UploadItem (subclasses).
- The subclasses override two methods from the superclass, createComponents and updateLists.
- Each subclass has distinct functionality as they all relate to different menus in the app.

## Phase 4: Task 3
- The AuctionApp class has lots of fields relating to different tasks.
I would move some fields and methods to different classes to increase cohesion.
- Everytime a change is made in the app, all the classes must also update. I did this very
inefficiently and during refactoring I could use Javas Observer to update the required classes.
- The type hierarchy introduced between the different stores (menus) does not follow the LSP.
I would keep the abstract class Store to keep all the methods which stay the same throughout
and extract an interface for all the methods which have different implementations in the subclasses.   
