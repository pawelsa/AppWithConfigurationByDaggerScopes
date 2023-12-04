### Problem definition

## Goal of this project

The goal of this project is to learn how to create scopes in XML app with Dagger/Hilt. It should create and keep
dependencies as long as the scope is active. It should showcase a usecase where the app needs to be
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

### Solution

The exemplary application shows solution for both XML and Compose. It is build with MVVM architecture.
As we are using two different UI solutions, they should not constrain us, for creating DI scopes.
A big role in this solution play ViewModels that are the main point of obtaining data, and handling user interactions.

Hilt provides us with ViewModelScope, that we can use to achieve our goal (almost). All dependencies that are created
within this scope, are disposed with the ViewModel that created them. This solution can work for some applications, but
it has a few drawbacks:

- we cannot share those dependencies, thus we cannot use the same state in multiple screens without use of memory
  solution
- when ViewModel will be destroyed the data will be lost
- each ViewModel will create its set of dependencies, which will result in filling up of memory

In our case we want to keep data in memory as long as user is logged in. A better solution for our problem is creation
of custom scope.

Creating a custom scope is not the easiest for the first time, but understanding basis of scopes and components in
Dagger is essential.

Steps for creating a Hilt scope and component:

- Create a scope annotation
  ```kotlin 
    @Scope
    @MustBeDocumented
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LoggedInUserScope
    ```

- Create a component with its builder. (Builder can be created in another file/outside of component interface)
  ```kotlin
  @LoggedInUserScope /// we need to define which scope the component is defined for
  @DefineComponent(parent = SingletonComponent::class) /// we need to define a parent component. This should take into consideration from which (other) component, our component will need dependencies. We cannot put our compontnt in between Hilt defined components i.e. ActivityComponent and FragmentComponent. It always creates a new "leaf".
  interface LoggedInUserComponent {

    @DefineComponent.Builder
    interface Builder {
        fun build(): LoggedInUserComponent
    }
  }
  ```

- Create a Module (like any other Hilt module)
  ```kotlin
    @Module
    @InstallIn(LoggedInUserComponent::class) /// we define that this module dependencies should be created for our component
    object MemoryModule {

        @Provides
        @LoggedInUserScope /// we define that one instance of given dependency should be created for our component, if we omit scope annotation, each call to obtain the given class will craete a new instance, and not reuse
        fun providesActiveMemoryRepository(): ActiveMemoryRepository = ActiveMemoryRepositoryImpl()

    }
  ```

Now starts the more confusing part of the solution. As the documentation states:
// todo wstawić fragment z dokumentacji o potrzebie użycia EntryPoints oraz o tym że jeśli chcemy zapamiętywać te
zmienne to musimy je gdzieś zapisywać

Let's start with EntryPoints (może jeszcze je tutaj opisać ?? ):

```kotlin
  @EntryPoint /// we need to define that it is an entry points
@InstallIn(LoggedInUserComponent::class) /// we need to define which component it is connected with
interface ActiveMemoryEntryPoint {

  fun getActiveMemoryRepository(): ActiveMemoryRepositoryImpl // define what class will be returned, it needs to be the implementation not an interface/abstract class

}
```

Before we will proceed to present a place where this entry point is used, we need to introduce our custom piece
of puzzle that handles lifecycle of the instances created in our component.
For that we will create a ComponentManager.

```kotlin
@Singleton /// our manager will be a singleton, so that we can always access it. This is the place that manages the lifecycle of our component. We create and destroy it here. It is not automatic as Hilt provided components. Hilt does not know how long we want to keep the component alive.
class LoggedInUserComponentManager @Inject constructor(
  private val componentBuilder: Provider<LoggedInUserComponent.Builder> /// we are provided with the builder created in earlier steps
) {

  /// while our app does not need the component's dependencies it will be null, to save memory
  /// at a point when the component will be requested for the first time, it will be saved here, for future usages, so that we do not create new instances of the component or its instances
  private var _loggedInUserComponent: LoggedInUserComponent? = null

  /// when we will need to obtain instances from our component, we will obtain it by creating it first, then saving it for future usages
  val loggedInUserComponent: LoggedInUserComponent
    get() {
      var loggedInUserComponent = _loggedInUserComponent
      if (loggedInUserComponent == null) {
        loggedInUserComponent = componentBuilder.get().build()
        _loggedInUserComponent = loggedInUserComponent
      }
      return loggedInUserComponent
    }

  /// at a point where user will logout, and the scope will be left we should remove the component, so that next time we will create new component and its instances.
  /// we need to remember to properly discard all the dependencies that were created in the current component, as we can have memory leaks or not working correctly application
  fun clearComponent() {
    _loggedInUserComponent = null
  }
}
```

Now we are ready for the last piece that ties everything together. It may be a bit confusing at first sight.
This module is like a bridge between our component, manager, entry points and the rest of the application.
You will find the explanation below

```kotlin
@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryEntryPointsBridgeModule {
  @Provides
  internal fun provideMemoryRepository(
    loggedInUserComponentManager: LoggedInUserComponentManager
  ): MemoryRepository {
    return EntryPoints
      .get(
        loggedInUserComponentManager.loggedInUserComponent,
        MemoryEntryPoint::class.java
      )
      .getMemoryRepository()
  }
}
```

The first question you may ask, why we use `@InstallIn(SingletonComponent::class)`? Won't it create a single instance
for the app?

The answer is few lines below.
In our method `provideMemoryRepository`, we use only `@Provides` without `@Singleton` or `@LoggedInUserScope`. /// todo
neither / nor
This information tells us that, every time we will request `MemoryRepository` it will come from here.

So, how many instances of memory repository will be created?

Only one. As we can see we use both a Manager and EntryPoint here.
Our Manager (that is a singleton) provides a component (if was not requested before, new one is created).
EntryPoint defines a method that helps us obtain an instance of `MemoryRepository`.
This means that as long as our manager will not recreate a component, we will be obtaining the same instance
of `MemoryRepository`.

How can/When we recreate our component?

This is up to us. In this sample app we want to remove the component when the user logs out, and create on login.
An example for removing

```kotlin
@ViewModelScoped
class LogoutUseCaseImpl @Inject constructor(private val loggedInUserComponentManager: LoggedInUserComponentManager) :
  LogoutUseCase {

  override fun execute(): Boolean {
    loggedInUserComponentManager.clearComponent()
    return true
  }
}
```

This way we created our custom component, that its lifecycle can be easily managed.

There is one important thing to remember. When the component will be removed, all instances created by it, will still
live,
as long as they will be referenced in our code.
We need to make sure that all the dependencies that use them, are also disposed, so at the point of running garbage
collector
they will be removed from the memory. In this application objects created in our scope are used by usecases, which are
@ViewModelScoped.
This means that at a point when viewmodels will be disposed, all usecases will be removed also. Which in result will
free our component instances.

// todo sprawdzić i opisać czy w jakiś scope'ach jak ViewModelScope można użyć @Singleton czy @ActivityScoped
// todo poczyścić kod z niepotrzebnego raczej modułu usecaseów
// todo usprawnić kod żeby był bardziej zgodny z dokumentacją, pokazać że nasze reposzytoria są reużywane
















