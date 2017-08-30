# Vehicle Factory
* **Purpose** - to demonstrate the [Abstract Factory Design pattern](https://sourcemaking.com/design_patterns/abstract_factory)
* **Design Description** - Provide an interface for creating families of related or dependent objects without specifying their conrete classes.
	* Description Source - _Design Patterns: Gang of Four_
* **Objective**
	* to implement a `VehicleFactoryController` which uses a `VehicleFactory` to generate `Vehicle` objects.
	* to implement a `VehicleController` which is a _data store_ for `Vehicle`.

# Part 1 - Domain Implementation

* _Domain objects_ are the backbone for an application and contain the [business logic](https://en.wikipedia.org/wiki/Business_logic).
* Create a sub package of `io.zipcoder.tc_spring_vehiclefactory_application` named `domain`.


## Part 1.1 - Create class `Vehicle`

* Create an _abstract_ class `Vehicle` in the `domain` sub-package.
* `Vehicle` class signature is annotated with `@Entity`
* `Vehicle` has an `id` instance variable of type `Long`
	* `id` should be `annotated` with
		* `@Id`
			* denotes primary key of this entity
		* `@GeneratedValue`
			* configures the way of increment of the specified `column(field)`
		* `@Column(name = "VEHICLE_ID")`
			* specifies mapped column for a persistent property or field

* `Vehicle` has a `isHybrid` instance variable of type `Boolean`
	* `isHybrid` should be `annotated` with
		* `@Column(name = "VEHICLE_IS_HYBRID")`

* `Vehicle` has a `numberOfWheels` instance variable of type `Integer`
	* `numberOfWheels` should be `annotated` with
		* `@Column(name = "VEHICLE_NUMBER_OF_WHEELS")`

* `Vehicle` has a `model` instance variable of type `String`
	* `model` should be `annotated` with
		* `@Column(name = "VEHICLE_MODEL")`

* `Vehicle` has a `make` instance variable of type `String`
	* `make` should be `annotated` with
		* `@Column(name = "VEHICLE_MAKE")`

* Create a `getter` and `setter` for each of the respective instance variables.
* The class should have a [nullary constructor](https://en.wikipedia.org/wiki/Nullary_constructor) (this will be used by the Spring framework).
* The class should have a constructor which has parameters of `String`, `String`, `int`, `boolean`, and sets each of the instance variables respectively.

## Part 1.2 - Create enum `VehicleMake`
* Create an enum `VehicleMake` which houses at least two enumerations corresponding to vehicle-makes.
	* (For example, `HONDA`, and `YAMAHA`)
* The enum should have a `getRandom()` method which returns a random enumeration from the enum.


## Part 1.3 - Create class `Bike`

* Create a `Bike` class in the `domain.bike` sub-package.
* `Bike` class signature is annotated with `@Entity`
* `Bike` is a subclass of `Vehicle`
* `Bike` has an empty _nullable constructor_
* `Bike` has a constructor which has parameters of `String`, `String`, `boolean` and sets  `numberOfWheel` to a value of `2`.

## Part 1.4 - Create class `Car`

* Create a `Car` class in the `domain.car` sub-package.
* `Car` class signature is annotated with `@Entity`
* `Car` is a subclass of `Vehicle`
* `Car` has an empty _nullary constructor_
* `Car` has a constructor which has parameters of `String`, `String`, `boolean` and sets  `numberOfWheel` to a value of `4`.












# Part 2 - Repository Implementation

* _Repositories_ or [Data Access Objects (DAO)](https://en.wikipedia.org/wiki/Data_access_object), provide an abstraction for interacting with _datastores_.
* Typically DAOs include an interface that provides a set of finder methods such as `findById`, `findAll`, for retrieving data, and methods to persist and delete data.
* Create a sub-package of `io.zipcoder.tc_spring_vehiclefactory_application` named `repositories`.


## Part 2.1 - Create interface `VehicleRepository`

* Create an `VehicleRepository` interface in the `repositories` subpackage.
* `VehicleRepository` is a subclass of `CrudRepository<Vehicle, Long>`














# Part 3 - Controller Implementation

* _Controllers_ provides all of the necessary [endpoints](https://en.wikipedia.org/wiki/Web_API#Endpoints) to access and manipulate respective domain objects.
	*  REST resources are identified using URI endpoints.
* Create a sub package of `io.zipcoder.tc_spring_vehiclefactory_application` named `controller`.


## Part 3.1 - Create class `VehicleController`
* Create a `VehicleController` class in the `controller` sub package.
	* `VehicleController` signature should be `annotated` with
		* `@RestController`
		* `@RequestMapping(value = "/vehicles")`

* The class should declare a `vehicleRepository` instance variable of type `VehicleRepository`
	* `vehicleRepository` should be `annotated` with `@Inject`
* The class should define method `getAllVehicles()` method which is annotated with `@GetMapping`

* The class should define method `getVehicles(Long vehicleId)`
	* which is annotated with `@PostMapping(value="/{vehicleId}")`
	* method argument is annotated with `@PathVariable`















# Part 4 - Creating Factories

## Part 4.1 - Create class `AbstractRandomEntityFactory`
* Create an _abstract_ class `AbstractRandomEntityFactory<E>` in the `tc_spring_vehiclefactory_application.utilities` package
* The class should declare a method `abstract public E create()`
* The class should define a method `public final Stream<E> createStream(int numberOfVehicles)` which generates a `Stream` of `E` whose `count` is the respective `numberOfVehicles`.


## Part 4.2 - Create class `AbstractRandomVehicleFactory`
* Create an _abstract_ class `AbstractRandomVehicleFactory<E extends Vehicle>` in the `tc_spring_vehiclefactory_application.domain` package.
* The class should declare method `abstract public E createMake(String make)`
* The class should define method `public final E createMake(VehicleMake make)` which makes use of the abstract `createMake(String make)`.
* The class should define method `public E create()` which returns a vehicle whose `vehicleMake` is assigned to a random value from the `VehicleMake` enum







## Part 4.3 - Create class `AbstractRandomBikeFactory`
* Create an _abstract_ class `AbstractRandomBikeFactory` in the `tc_spring_vehiclefactory_application.domain.bike` package.
* The class should be a subclass of `AbstractRandomVehicleFactory<Bike>`.
* The class should define method `public Bike createMake(String make)` which returns a `new Bike` whose
	* `vehicleMake` is set to the respective `make` argument
	* `model` is set to a randomly generated string
	* `isHybrid` is set to a randomly generated boolean
* Tip: make use of `io.zipcoder.tc_spring_personfactory_application.utilties.RandomUtils`

## Part 4.4 - Create class `RandomYamahaBikeFactory`
* Create class `RandomYamahaBikeFactory` in the `tc_spring_vehiclefactory_application.domain.bike` package.
* The class should define method `public Bike create()` which returns a `new Bike` by calling the method `super.createMake(VehicleMake vehicleMake)`.













## Part 4.5 - Create class `AbstractRandomCarFactory`
* Create an _abstract_ class `AbstractRandomCarFactory` in the `tc_spring_vehiclefactory_application.domain.car` package.
* The class should be a subclass of `AbstractRandomVehicleFactory<Car>`.
* The class should define method `public Bike createMake(String make)` which returns a `new Car` whose
	* `vehicleMake` is set to the respective `make` argument
	* `model` is set to a randomly generated string
	* `isHybrid` is set to a randomly generated boolean

## Part 4.6 - Create class `RandomHondaCarFactory`
* Create class `RandomHondaCarFactory` in the `tc_spring_vehiclefactory_application.domain.car` package.
* The class should define method `public Car create()` which returns a `new Car` by calling

```java
super.createMake(VehicleMake vehicleMake)
```


## Part 4.7 - Create class `RandomVehicleFactoryCreator`
* Create class `RandomVehicleFactoryCreator` in the `tc_spring_vehiclefactory_application.domain` package.
* The class signature should be annotated with `@Service`.
* The class should define method `public RandomHondaCarFactory createHondaCarFactory()` which returns a `new RandomHondaCarFactory`.
* The class should define method `public RandomYamahaBikeCreator createHondaCarFactory()` which returns a `new RandomYamahaBikeCreator`.








# Part 5 - Create class `RandomVehicleFactoryLookup`
* Create class `RandomVehicleFactoryLookup` in the `tc_spring_vehiclefactory_application.utilities` package.
* The class should have an instance variable, `map`, of type `HashMap<String, AbstractRandomVehicleFactory>`.
* The class should have an instance variable, `rvfc`, of type `RandomVehiclFactoryCreator` which is annotated with `@Injected`
* The class should define a method `private addToMap(AbstractRandomVehicleFactory... factories)`, which maps to each of the respective factory objects from their [simple class name](https://www.tutorialspoint.com/java/lang/class_getsimplename.htm).
* The class should define a _nullary constructor_, which adds each of the concrete vehicle factories to `map`.

```java
addToMap(randomHondaCarFactory, randomYamahaBikeFactory);
```

* The class should define a method `public AbstractRandomVehicleFactory get(String simpleClassName)` which
	* returns the respective factory from `map`
	* throws `IllegalArgumentException` if the `map` has no respective `key`


# Part 6 - Create class `VehicleFactoryController` 
* Create class `VehicleFactoryController`
* The class signature should be annotated with
	* `@RestController`
	* `@RequestMapping(value = "/vehicleFactory")`
* The class should declare an instance variable `vehicleRepository` of type `VehicleRepository`.
	* `vehicleRepository` should be annotated with `@Inject`
* The class should declare an instance variable `factoryLookup` of type `RandomVehicleFactoryLookup`
	* `factoryLookup` should be annotated with `@Inject`
* The class should declare a method `public ResponseEntity<?> createRandomVehicle(String simpleClassName)`
	* `createRandomVehicle` should be annotated with `PostMapping`
	* arugment of `createRandomVehicle` should be annotated with `@RequestParam(value="vehicleMaker")`
	* `simpleClassName` should be used to query from `factoryLookup` for the respective factory and generate a respective `vehicle`.










# Part 7 - Test via Postman
* Yep...











# Part 8 - Create class `VehicleAssembler`
* Using the [builder lab](https://github.com/Zipcoder/TC-Spring-LicenseBuilder-Application) as an example, redefine the concrete factory classes to use _composite_ `VehicleAssembler` which behaves as a _builder_ for `Vehicle` objects.
