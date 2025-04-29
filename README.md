# My Own Closet

## Overview

**My Own Closet** is a Java Swing-based application designed to solve the everyday dilemma of choosing what to wear. By allowing users to view, register, and coordinate their own clothes digitally, the app helps streamline outfit selection and reduces the hassle of trying clothes on manually.

## Features

- **User Authentication**: Sign up and log in to manage your own personalized closet.
- **Clothing Registration**: Register clothing items by selecting their category (Top, Bottom, Outer, Shoes), type, and color.
- **Closet Overview**: View registered clothes by category or see all items at once, including a count of items in each category.
- **Outfit Coordination**: Simulate outfits by combining different clothing items with a single click.
- **Clothing Deletion**: Easily remove registered items from your closet.

## Technology Stack

- **Java Swing** for GUI development
- **File I/O** for saving user information and loading images
- **Multithreading** for dynamically counting clothing items by category
- **Collections (ArrayList, Map)** for managing clothes data and associated images
- **Polymorphism & Inheritance** to structure components cleanly

## Project Flow

1. **User Registration / Login**
2. **Clothing Management**
   - Register clothing items
   - View clothes by category or all at once
   - Delete selected items
3. **Coordinate Clothes**
   - Select clothes from each category
   - Simulate complete outfits visually

## Screenshots

- **Main Interface**: Displays different states based on login status.
- **Registration / Login Page**: Allows users to create accounts and log in.
- **Clothing Registration Page**: Choose category, type, and color.
- **Closet Page**: View and manage all registered clothing.
- **Coordination Page**: Create outfit combinations.

## Code Highlights

- **Dynamic Image Handling**: Images are dynamically loaded and resized based on selected clothing items.
- **Threaded Item Counting**: Separate threads update the number of clothes per category in real-time.
- **Custom Dialogs**: Deletion and error handling are managed through custom JDialogs.
- **Flexible Combination Logic**: Clothing items can be selected and visually combined through a grid layout.

## Future Improvements

- **Outfit Recommendation Feature**: Initially planned to implement a tagging system to recommend clothes, but deferred due to complexity.
- **Expanded Wardrobe**: Increase diversity of available clothes, including women's clothing and support for uploading custom photos.

## Credits

- Clothing images are sourced from **[Freepik](https://www.freepik.com/)** and colored using **Naver Webtoon AI Painter**.

## Personal Reflection

This was my first solo project after beginning my software double major. I always dreamed of building an app like this, but lacked the programming skills until now. Through learning Java and GUI programming this semester, I was finally able to realize my idea. Although there are still features I wish to add, this project has been a highly valuable and exciting experience.

---

> Built with passion by **Jeong Minjae (정민재)** | 2020312429
