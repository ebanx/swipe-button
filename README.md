[ ![Download](https://api.bintray.com/packages/ebanx/maven/swipe-button/images/download.svg) ](https://bintray.com/ebanx/maven/swipe-button/_latestVersion) [![Build Status](https://travis-ci.org/ebanx/swipe-button.svg?branch=master)](https://travis-ci.org/ebanx/swipe-button)

# Swipe-Button

Library of an android button activated by swipe. 

- Easy to use. 
- Make your app look great
- Better UX in sensitive button

![enter image description here](https://lh3.googleusercontent.com/-pG1QBfiSaIo/WSbswf9TR8I/AAAAAAAAACQ/BITTeBVyhvQHM5o2hTW7x4qsbfW3OJbCQCLcB/s0/button+movie.gif "button movie.gif")

## Instalation

    compile 'com.ebanx:swipe-button:0.3.0'

## How to use

Add the button in your layout file and customize it the way you like it.

    <com.ebanx.swipebtn.SwipeButton
        android:id="@+id/swipe_btn"
        android:layout_width="match_parent"
        android:layout_height="110dp"
        android:layout_marginStart="20dp"
        android:layout_marginEnd="20dp"
        app:inner_text="SWIPE"
        app:inner_text_color="@android:color/white"
        app:inner_text_size="16sp"
        app:inner_text_top_padding="18dp"
        app:inner_text_bottom_padding="18dp"
        app:inner_text_background="@drawable/shape_rounded"
        app:button_image_disabled="@drawable/ic_lock_open_black_24dp"
        app:button_image_enabled="@drawable/ic_lock_outline_black_24dp"
        app:button_left_padding="20dp"
        app:button_right_padding="20dp"
        app:button_top_padding="20dp"
        app:button_bottom_padding="20dp"
        app:button_background="@drawable/shape_button"
        />
### Setting the sliding button size
You can set the size of the moving part of the button by changing the icon inside it or changing the padding in the button.

### Setting the text part size
You can set the size of the fixed part of the button by setting the text size of the setting the padding in this part.

## Configure XML

 - inner_text: Text in the center of the button. It disapears when swiped
 - inner_text_color: Color of the text
 - inner_text_size: Size of the text
 - inner_text_[direction]_padding: Sets the padding of the text inside the button. You can set how big this part of the button will by setting text size and padding.
 - button_image_disabled: Icon of the button when disabled. This is the initial state. 
 - button_image_enabled: Icon of the button when disabled. This is the initial expanded state. 
 - button_[direction]_padding: Sets the padding of the button the slide with the touch. You can set how big the button will be by setting the image and the padding

## Bugs and features
For bugs, feature requests, and discussion please use [GitHub Issues](https://github.com/ebanx/swipe-button/issues).

## Credits

 - Design: Diego Martins 
 - Development: [Leandro Borges Ferreira](https://github.com/leandroBorgesFerreira)


### And that's it! Enjoy!

> Written with [StackEdit](https://stackedit.io/).
