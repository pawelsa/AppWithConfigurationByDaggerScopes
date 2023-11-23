## Goal of this project

The goal of this project is to learn how to create scopes in XML app with Dagger/Hilt. It should create and keep
dependencies when they are required and dispose them when not. It should showcase a usecase where the app needs to be
configured before the first use, and that the configuration can change while the app is running. Configuration should
target a part of code that is required for the app to work correctly, like an api address.

## Screens

Application should have at least 3 screens:

- configuration screen, where user setups api address
- login screen
- home screen

## Navigating between the screens

# There are two scenarios when opening the app:

- first run of the application and requires configuration, then proceeds to login
- 2+ run of the app, where user only needs to log in

# When user is logged in:

It is possible to log out, which clears all the user data kept in active memory (not database)

# When user is on login screen:

From the login screen, user can navigate to the configuration screen, and change the api address. The change of the
address should be accounted in the login screen, and use the new address