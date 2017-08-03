[ ![Download](https://api.bintray.com/packages/ebanx/maven/swipe-button/images/download.svg) ](https://bintray.com/ebanx/maven/swipe-button/_latestVersion) [![Build Status](https://travis-ci.org/ebanx/swipe-button.svg?branch=master)](https://travis-ci.org/ebanx/swipe-button)

# Swipe-Button


![enter image description here](https://lh3.googleusercontent.com/-pG1QBfiSaIo/WSbswf9TR8I/AAAAAAAAACQ/BITTeBVyhvQHM5o2hTW7x4qsbfW3OJbCQCLcB/s0/button+movie.gif "button movie.gif")

![enter image description here](https://lh3.googleusercontent.com/-m87DnCN1GwQ/WWuDroJAP5I/AAAAAAAAADE/tI8Tj0HcIHoy8PCsjSUbLq75ugmqtC77ACLcBGAs/s0/swipe_one_state.gif "swipe_one_state.gif")

![enter image description here](https://drive.google.com/file/d/0B0vUiDl0wr87dkJGdVY4NFc4WVU/export?format=gif)

Library of an android button activated by swipe. 

- Easy to use. 
- Makes your app look great
- Better UX in sensitive button


## Installation

    compile 'com.ebanx:swipe-button:[latestVersion]'

## How to use

Add the button in your layout file and customize it the way you like it.

    <com.ebanx.swipebtn.SwipeButton
        android:id="@+id/swipe_btn"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="20dp"
        android:layout_marginEnd="20dp"
        app:inner_text="SWIPE"
        app:inner_text_color="@android:color/white"
        app:inner_text_size="16sp"
        app:inner_text_top_padding="18dp"
        app:inner_text_bottom_padding="18dp"
        app:inner_text_background="@drawable/shape_rounded"
        app:button_image_height="60dp"
        app:button_image_width="100dp"
        app:button_image_disabled="@drawable/ic_lock_open_black_24dp"
        app:button_image_enabled="@drawable/ic_lock_outline_black_24dp"
        app:button_left_padding="20dp"
        app:button_right_padding="20dp"
        app:button_top_padding="20dp"
        app:button_bottom_padding="20dp"
        app:button_background="@drawable/shape_button"
        app:initial_state="disabled"
        app:has_activate_state="true"
        />
        
### Setting the sliding button size
You can set the size of the moving part of the button by changing the app:button_image_width and app:button_image_height properties.

### Setting the text part size
You can set the size of the fixed part of the button by setting the text size of the setting the padding in this part.

### Listening for changes
You can set a listener for state changes

    SwipeButton enableButton = (SwipeButton) findViewById(R.id.swipe_btn);
    enableButton.setOnStateChangeListener(new OnStateChangeListener() {
          @Override 
          public void onStateChange(boolean active) {
               Toast.makeText(MainActivity.this, "State: " + active, Toast.LENGTH_SHORT).show();
          } 
    }); 
    
Or listen for the activation of the button 

    swipeButtonNoState.setOnActiveListener(new OnActiveListener() {
                @Override
                public void onActive() {
                    Toast.makeText(MainActivity.this, "Active!", Toast.LENGTH_SHORT).show();
                }
            });

## Configure XML

 - button_image_width: Change the width of the moving part of the button
 - button_image_height: Change the height of the moving part of the button
 - inner_text: Text in the center of the button. It disapears when swiped
 - inner_text_color: Color of the text
 - inner_text_size: Size of the text
 - inner_text_[direction]_padding: Sets the padding of the text inside the button. You can set how big this part of the button will by setting text size and padding.
 - button_image_disabled: Icon of the button when disabled. This is the initial state. 
 - button_image_enabled: Icon of the button when disabled. This is the initial expanded state. 
 - button_[direction]_padding: Sets the padding of the button the slide with the touch. You can set how big the button will be by setting the image and the padding
 - initial_state: Initial state. Default state is disabled.
 - has_activate_state: Set if the button stops in the "active" state. If false, the button will only come back to the initial state after swiped until the end of its way. Use OnActiveListener if you set the parameter to false.

## CodePen
If you would like to see a front-end version of this button you can check a codepen in this link:

 - http://codepen.io/gpressutto5/pen/NjJobG
 - https://codepen.io/issamu/pen/NgPOKb (with Safari fix)

## Bugs and features
For bugs, feature requests, and discussion please use [GitHub Issues](https://github.com/ebanx/swipe-button/issues).

## Credits

 - Design: [Diego Martins](https://dribbble.com/diegomartins) 
 - Development: [Leandro Borges Ferreira](https://github.com/leandroBorgesFerreira), [Vinicius Nadin](https://github.com/viniciato)
 - Codepen: [Guilherme Pressuto](https://github.com/gpressutto5)
 - Codepen: [João Issamu Francisco](https://github.com/joaoissamu)

### And that's it! Enjoy!

> Written with [StackEdit](https://stackedit.io/).

