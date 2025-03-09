### Problem definition

## Goal of this project

The goal of this project is to learn how to create scopes in XML and compose app with Dagger/Hilt.
It should create and keep dependencies as long as the scope is active. It should showcase a usecase
where the app data is kept as long as the scope is alive.

## Screens

Application should have at least 3 screens:

- configuration screen, where user setups initial number
- home screen
- details screens

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

Now we will need to access them. As this is a custom component, we cannot expect Dagger to know how it should access it.
For that reason we need to use EntryPoints.
The documentation
says : `An entry point is the boundary where you can get Dagger-provided objects from code that cannot use Dagger to inject its dependencies.
It is the point where code first enters into the graph of objects managed by Dagger.
If you’re already familiar with Dagger components, an entry point is just an interface that the Hilt generated component will extend.`.

```kotlin
  @EntryPoint /// we need to define that it is an entry point
@InstallIn(LoggedInUserComponent::class) /// we need to define which component it is connected with
interface ActiveMemoryEntryPoint {

  fun getActiveMemoryRepository(): ActiveMemoryRepositoryImpl // define what class will be returned, it needs to be the implementation not an interface/abstract class

}
```

Before we will proceed to present a place where this entry point is used, we need to introduce our custom piece
of puzzle that handles lifecycle of the instances created in our component.
For that we will create a ComponentManager.

```kotlin
/// Our manager will be a singleton, so that we can always access it. This is the place that manages the lifecycle 
/// of our component. We create and destroy it here. It is not automatic as Hilt provided components. 
/// Hilt does not know how long we want to keep the component alive.
@Singleton
class LoggedInUserComponentManager @Inject constructor(
  private val componentBuilder: Provider<LoggedInUserComponent.Builder> /// we are provided with the builder created in earlier steps
) {

  /// When our app does not need the component's dependencies it will be null, to save memory.
  /// At a point when the component will be requested for the first time, it will be saved here, for future usages, 
  // so that we do not create new instances of the component or its instances
  private var _loggedInUserComponent: LoggedInUserComponent? = null

  /// here we are lazily creating an instance of component
  val loggedInUserComponent: LoggedInUserComponent
    get() {
      var loggedInUserComponent = _loggedInUserComponent
      if (loggedInUserComponent == null) {
        loggedInUserComponent = componentBuilder.get().build()
        _loggedInUserComponent = loggedInUserComponent
      }
      return loggedInUserComponent
    }

  /// At a point where user will logout, and the scope will be left we should remove the component, so that next time we will create new component and its instances.
  /// We need to remember to properly discard all the dependencies that were created in the current component, 
  /// as we can have memory leaks or not correctly working application
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
In our method `provideMemoryRepository`, we use only `@Provides` without `@Singleton` or `@LoggedInUserScope`.
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
live, as long as they will be referenced in our code. We need to make sure that all the dependencies that use them,
are also disposed, so at the point of running garbage collector they will be removed from the memory.
In this application objects created in our scope are used by usecases, which are @ViewModelScoped.
This means that at a point when viewmodels will be disposed, all usecases will be removed also. Which in result will
free our component instances.
