# CircleTextImage

A round head, which can be stored on the text, suitable for contact head

# show
![image](https://github.com/viviant1224/CircleTextImage/blob/master/ShowcircleTextImage.gif)
<br/> 
![image](https://github.com/viviant1224/CircleTextImage/blob/master/PIC1.png)
![image](https://github.com/viviant1224/CircleTextImage/blob/master/PIC2.png)
![image](https://github.com/viviant1224/CircleTextImage/blob/master/PIC3.png)

# Property introduction
```Java
circletextview:random_color="true"            //use random background color, default is not use,false is not use

circletextview:circle_color="#f48fb1"         //def your background color,default color is red

circletextview:circle_text_color="#000000"    //def your text color ,default color is white

circletextview:sub_first_character="true"     //make the first text's character to show in the image, default is show all text.

```



# How to use it
Step 1:

import the lib to your project:
gradle:
```
compile 'circletextimage.viviant.com.circletextimagelib:circletextimagelib:1.2.0'
```
maven:
```
<dependency>
  <groupId>circletextimage.viviant.com.circletextimagelib</groupId>
  <artifactId>circletextimagelib</artifactId>
  <version>1.2.0</version>
  <type>pom</type>
</dependency>
```
lvy:
```
<dependency org='circletextimage.viviant.com.circletextimagelib' name='circletextimagelib' rev='1.2.0'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>
```

Step2:

use in the xml:
```
<circletextimage.viviant.com.circletextimagelib.view.CircleTextImage
        android:id="@+id/ctimage"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        circletextview:random_color="true"/>
```

And you can set Text in java code:

```
CircleTextImage ctimage = (CircleTextImage) findViewById(R.id.ctimage);
ctimage.setText4CircleImage("swift");
```

#Contact
Email:viviant1224@gmail.com   or 18686672327@163.com
user:weiwei.huang


# License

Copyright 2016 viviant1224

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
