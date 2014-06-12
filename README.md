ChangingBackground-Android
==========================
Follow the same instructions as the [iOS programming challenge](https://github.com/jeffreycamealy/ChangingBackground), but fork this project instead.


##Implementation description


Based on the animation available on the iOS programming challenge, these are the requirements I interpreted:

1. The background has to change _after_ the view changes.
2. The views need to transition using that precise left-right, right-left animation shown.
3. The app should be able to show the correct background after pressing back and forth between views multiple times.

###Other Implementation Alternatives.
I explored a few options. The _right_ way to create this app is to use `Fragment`, not `Activity`. To satisfy my own curiosity first, I wanted to see if it was possible to set a background to the whole app and make Activities transparent. You can certainly set a global theme, but any background set is copied by each Activity. If you want to get really hackish you use Activities with the android:windowIsFloating attribute, but then you cannot use simple Intents or the `finish()` method to move between Activities, since they need to be cleared manually. Anything using Activities to solve this challenge is probably wrong.

###Setup
The App is a single Activity composed of a background image, and two fragments that are programatically added at runtime. Because of requirement #1, this code challenge isn't straight forward to do using standard Android techniques. 

###Background Gradient
The background gradient is implemented using an ImageView, setting a TransitionDrawable as its source. It's possible to do this without using an ImageView, and setting the TransitionDrawable as the `Activity`'s background, but this is more straight-forward. The TransitionDrawable has methods to flip between two Drawables. An app using more than 2 background images wouldn't be able to use this technique, and something custom would probably have to be written.

###View Switching
Switching between the two fragments is achieved by using a `FragmentTransaction` with custom animations. To satisfy requirement #1, you'd normally register a listener for when the animation is done, but `FragmentTransaction` doesn't provide a way to register listeners or expose the Animation. `TransitionDrawable` also doesn't provide a way to fire the animation after a few seconds. Therefore, I had to add a Timer which fires the `TransitionDrawable` animation after a second. This is very hackish, and it creates extra work if the app has more than two views. In order to do this correctly, one has to extend and override `FragmentTransation`. I'm surprised Google hasn't added support for this. Most applications probably don't need this.

Because Android phones have back buttons, I also had to block usage of the back button during the transition, to prevent the Timer from firing when it's not supposed to. 



