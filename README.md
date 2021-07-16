# forismatic
A simple Spring application to interact with the forismatic web services

## Building the project
```aidl
gradlew clean build
```

## Running the project
Once the project is built successfully, an executable jar file named `quotely.jar` will be generated into the `build/libs` folder under the project folder.

The application can be run from the command line using the java command.

Example:
```aidl
java -jar path\to\the\jar\quotely.jar
```

The application accepts a single argument to specify the language for the quotes. This argument can either be English or Russian (case-insensitive).
Any other value for the argument will result in an exception.

Example:
```aidl
java -jar path\to\the\jar\quotely.jar English
```