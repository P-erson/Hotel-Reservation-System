# Hotel Reservation System

A comprehensive Object-Oriented Programming (OOP) project built in Java that simulates a complete hotel reservation management system.

## Overview

This Hotel Reservation System is designed to manage hotel operations including room management, guest reservations, staff administration, and billing. The system implements core OOP principles with multiple user roles and comprehensive error handling.

## Features

### User Roles
- **Admin**: Full system access including room and amenity management
- **Receptionist**: Handle guest check-ins, check-outs, and reservations
- **Guest**: Make reservations and view booking details
- **Staff**: Base role for employees

### Core Functionality

#### Room Management
- Create, read, update, and delete rooms
- Manage room types and pricing
- Track room availability
- Associate amenities with rooms

#### Reservation System
- Book rooms for specified date ranges
- View available rooms
- Manage guest reservations
- Track booking details and guest information

#### Guest Management
- Register new guests
- Track guest information and booking history
- Generate invoices for stays

#### Amenity Management
- Create and manage room amenities
- Associate amenities with specific rooms

#### Staff Management
- Manage admin and receptionist accounts
- Track working hours

### Error Handling
The system includes custom exceptions for robust error management:
- `InvalidUsernameException` - Invalid username format
- `InvalidPasswordException` - Invalid password
- `InvalidDateOfBirthException` - Invalid date of birth
- `InvalidPaymentException` - Payment validation errors
- `RoomNotAvailableException` - Room availability issues
- `RoomDoesNotExistException` - Non-existent room operations
- `GuestNotFoundException` - Guest lookup failures
- `AmenityDoesNotExistException` - Amenity lookup failures
- `InvalidBalance` and other business logic exceptions

## Project Structure

```
Hotel-Reservation-System/
└── src/
    ├── Admin.java              # Admin role and room management
    ├── Receptionist.java       # Receptionist role
    ├── Guest.java              # Guest profile and booking
    ├── Staff.java              # Base staff class
    ├── User.java               # Base user class
    ├── Room.java               # Room entity
    ├── RoomType.java           # Room type definition
    ├── Reservation.java        # Reservation management
    ├── Amenity.java            # Room amenity
    ├── Invoice.java            # Billing and invoices
    ├── HotelDatabase.java      # Data persistence layer
    ├── Main.java               # Entry point
    └── Exceptions/
        ├── InvalidUsernameException.java
        ├── InvalidPasswordException.java
        ├── RoomNotAvailableException.java
        ├── RoomDoesNotExistException.java
        ├── GuestNotFoundException.java
        ├── AmenityDoesNotExistException.java
        └── ... other custom exceptions

```


## Object-Oriented Design

This project demonstrates key OOP principles:

- **Inheritance**: User hierarchy (User → Staff → Admin/Receptionist, User → Guest)
- **Encapsulation**: Private fields with getter/setter methods
- **Polymorphism**: Different user roles with specialized behaviors
- **Abstraction**: Custom exceptions and abstract methods
- **Composition**: Rooms contain amenities, reservations reference guests and rooms

## Key Classes

### User Hierarchy
- `User`: Base class with authentication
- `Staff`: Employee base class with working hours
- `Admin`: Administrative privileges for system management
- `Receptionist`: Guest-facing operations
- `Guest`: Customer profile and reservation history

### Entity Classes
- `Room`: Hotel room with amenities and pricing
- `Reservation`: Booking details with dates and guest info
- `Invoice`: Billing information for reservations
- `Amenity`: Room features and services
- `HotelDatabase`: Central data management

## Error Handling

The system uses custom exceptions for business logic validation:

```java
try {
    // Operation that might fail
} catch (RoomNotAvailableException e) {
    // Handle room availability issues
} catch (InvalidPaymentException e) {
    // Handle payment errors
}
```

## License

This project is part of an academic OOP course.

## Authors

- Khaled Sorour   — 25P0227
- Mahmoud Khaled  — 25P0366
- Ali Sadek       — 25P0141
- Mark Ehab       — 25P0370
- Mahmoud Fayed   — 25P0231

Created as a Spring semester OOP project (Year 1)
