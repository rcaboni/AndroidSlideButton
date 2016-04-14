# AndroidSlideButton
Example how to create a Slide Button in Android like IOS slider (unlock)

The Android Studio project show how to create a composed layout to create a Slide Button : Horizontal (only Left to Right)
and Vertical (only Up to Down).

It's not a real complete widget because is composed with two Views (TextView and SlideButton) into a Layout, 
but it's a easy configurable solution for Slide Button with text inside.
The SlideButton is a widget extends SeekBar with the special behavior that when the user left the thumb it return in start position.

The use into an activity is very easy with a listener :<br>
<pre>
			slideButton = (SlideButton) findViewById(R.id.slideButton);
			slideButton.setSlideButtonListener(new SlideButtonListener() {
				@Override
				public void handleSlide() {
					System.out.println("Unlock");
				}
			});
</pre>

I hope this is useful for someone.

This is what the project create : <br>
<img src="https://raw.githubusercontent.com/rcaboni/AndroidSlideButton/master/screenshot.jpg"/>
