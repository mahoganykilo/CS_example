Some design decisions:
* Chose to use a MVVM architecture
* Separated the service from the repository to aid testing

Library choices:
* Detekt: Good linting tool
* Dagger2: I had originally tried to use Hilt, which was much nicer to implement than Dagger2 was,
however it didn't seem to play nicely with the Espresso tests, so in the interest of time reverted
to Dagger2. With most of the boilerplate implemented adding additional modules shouldn't be too
much additional effort.
* Espresso: First time writing UI tests in Android, and found that is was quite nice :)
Unfortunately had some issues combining it with Hilt however hopefully those are easier to solve
in the future.
* Retrofit: Solid API library, the flexibility that comes from using different JSON converters is
really nice. In this case Gson handles our use-case nicely.
* Mockk: I've seen that you can do some very powerful things with Mockito, but also have seen that
it can play poorly with Roboelectric. Because of this I tend to steer away from it just in case.
I wasn't sure if I was going to use it however Mockk has a very clean style that I enjoy using.
