# Simplest forge app (basic example)

This project contains very simple forge application that is used as a base for several introduction tutorials.

## Prerequisites

Clone the current project and import it in Android Studio (version 2.2.1 at least).

## Application class

If you are starting from scratch and you want to create a forge app first thing that you need to do is to subclass [`UnitApplication`](https://github.com/ogrebgr/forge-android/blob/master/forge-android/src/main/java/com/bolyartech/forge/android/app_unit/UnitApplication.java) class which provides the functionality that makes [Forge units](https://github.com/ogrebgr/forge-android/wiki/Forge-unit) work.
Open `MyApp` and you will see:

```Java
public class MyApp extends UnitApplication {
}
```

Here we don't override any methods because our app is simple. In more sophisticated applications your application class will override `onCreate()` in order to initialize global application state and/or setup the dependency injection.


## Base activity class

Activities that are part of a [Forge unit](https://github.com/ogrebgr/forge-android/wiki/Forge-unit) must implement the [`UnitActivity`](https://github.com/ogrebgr/forge-android/blob/master/forge-android/src/main/java/com/bolyartech/forge/android/app_unit/UnitActivity.java) interface. It is good practice to define a common base activity class that implements `UnitActivity` because that way you will not have to provide implementation for each of your activities over and over again.


In this project we created [`UnitBaseActivity`](https://github.com/ogrebgr/forge-android-examples-basic/blob/master/app/src/main/java/com/bolyartech/forgeexamples/basic/UnitBaseActivity.java) which inherits `AppCompatActivity`:

```java
abstract public class UnitBaseActivity<T extends ResidentComponent> extends AppCompatActivity
        implements UnitActivity<T> {

    private UnitActivityDelegate<T> mDelegate = new UnitActivityDelegate<>();


    @Override
    public void setResident(@NonNull T resident) {
        mDelegate.setResident(resident);
    }


    @Override
    @NonNull
    public T getResident() {
        return mDelegate.getResident();
    }


    @Override
    @NonNull
    public T getRes() {
        return mDelegate.getRes();
    }
}
```

If in your app you are NOT using `appcompat-v7` library then you should replace `AppCompatActivity` with `Activity`.

`UnitBaseActivity` is simply delegating all of its functionality to `UnitActivityDelegate`.


:exclamation: We did not provided UnitBaseActivity as a part of the framework because that way we had to choose to use either `AppCompatActivity` or `Activity` for base class and thus limiting your options as a user of the framework.


## Main unit

As stated in [Code conventions](https://github.com/ogrebgr/forge-android/wiki/Code-conventions) each unit have to have its own directory under `units/` and thus 'Main' unit is residing in `units/main/`.

'Main unit' contains simple functionality for generating random number on start and preserving it during configuration changes like rotation.

### Resident component

'Main' resident component is responsible for generating and preserving the integer  value, i.e. business logic.

It looks like this:

```java
class ResMain extends ResidentComponentAdapter {
    private final Random mRandom = new Random();
    private int mStored;


    ResMain() {
        newValue(); // (1)
    }


    void newValue() {
        mStored = mRandom.nextInt(); // (2)
    }


    int getStored() {
        return mStored; // (3)
    }
}
```

(1) - When resident is created it will generate a random int
(2) - When activity calls `newValue()` the value will be replaced with a new onCreate
(3) - Activity will use `getStored()` to get the stored value

In 'real' applications is good practice to define the resident component's interface to the activity in separate java interface like this:

```java
public interface ResMain {
    void newValue();
    int getStored();
}
```

and then implement it like:

```java
class ResMainImpl extends ResidentComponentAdapter implements ResMain {
```

but since this is simple example we will not bother.


### Unit activity

`ActMain` looks like this:

```java
public class ActMain extends UnitBaseActivity<ResMain> {
    private TextView mTvValue;


    @NonNull
    @Override
    public ResidentComponent createResidentComponent() { // (1)
        return new ResMain();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        initViews(getWindow().getDecorView()); // (2)
    }


    private void initViews(View view) {
        mTvValue = ViewUtils.findTextViewX(view, R.id.tv_value);
        ViewUtils.initButton(view, R.id.btn_new_value,
                new DebouncedOnClickListener() { // (3)

            @Override
            public void onDebouncedClick(View v) {
                getRes().newValue();
                updateValueView();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateValueView(); // (4)
    }


    private void updateValueView() {
        mTvValue.setText(getString(R.string.act__main__tv_value,
                getRes().getStored())); // (5)
    }
}

```

(1) Each UnitActivity must implement `createResidentComponent()` and return new instance of its resident component.

(2) It is good practice to init view in separate method

(3) We use `DebouncedOnClickListener` instead of the normal `OnClickListener` in order to prevent unintentional double clicks

(4) In onResume() we always call `updateValueView()` in order to show the value

(5) We get the stored value from the resident and show it

You can play with the app by switching orientation and see that value is preserved.
