# Swipe-Button

Library of a button activated by swipe. 

- Easy to use. 
- Make your app look great
- Better UX in sensitive button

![enter image description here](https://lh3.googleusercontent.com/-pG1QBfiSaIo/WSbswf9TR8I/AAAAAAAAACQ/BITTeBVyhvQHM5o2hTW7x4qsbfW3OJbCQCLcB/s0/button+movie.gif "button movie.gif")

## Instalation

    compile 'com.ebanx:swipe-button:0.1.0'

## How to use

Add the button in your layout file and customize it the way you like it.

    <com.ebanx.swipebtn.SwipeButton
        android:layout_width="match_parent"
        android:layout_height="110dp"
        android:layout_marginStart="20dp"
        android:layout_marginEnd="20dp"
        app:inner_text="SWIPE"
        app:inner_text_color="@android:color/white"
        app:inner_text_size="14sp"
        app:button_image_disabled="@drawable/your_image"
        app:button_image_enabled="@drawable/your_image2"
        />

## Configure XML

 - inner_text: Text in the center of the button. It disapears when swiped
 - inner_text_color: Color of the text
 - inner_text_size: Size of the text
 - button_image_disabled: Icon of the button when disabled. This is the initial state. 
 - button_image_enabled: Icon of the button when disabled. This is the initial expanded state. 

## Bugs and features
For bugs, feature requests, and discussion please use [GitHub Issues](https://github.com/ebanx/swipe-button/issues).

## Credits

 - Design: Diego Martins 
 - Development: [Leandro Borges Ferreira]

(https://github.com/leandroBorgesFerreira)

### And that's it! Enjoy!

> Written with [StackEdit](https://stackedit.io/).
