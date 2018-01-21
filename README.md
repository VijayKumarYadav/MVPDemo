## MVPDemo

#### Summary  
This sample is based on MVP Architecture guidelines and consisting of 2 modules - UI Module & Tracking modules.

It implement the following concept -

  *  On the first launch of application, user will see 6 random images coming from **flikr** (we call it category images) and these will be saved by tracking modules to show the same images in next application launches.
  * Clicking any one of them will leads to next screen where 12 random images will be shown from **flikr** again. Clicking further on them will show details of that image.
  * App keeps track of every click user made on the category images.
  * Now when user relaunch the application, he will see a welcome page showing him the most clicked category images and a way to go on category images screen. 
  * One most important thing of category images is that they will be rendered with weight and weight will be decided by the number of click user made to that particular images. 
  * Category images is rendered in a grid and can take max 3 column for max weight and 1 column in normal. 
  So this app is using proximity concept to render them in grid that's means
  if 3 images (lets say a, b, c) have 1 click and next 1 (say d) have 2 click, next 1 (e) have 5 click and last one (f) is having 10 click so a, b, c, d will be rendered with 1 column while e will take 2 and f will take 3 column. Please note that image d is aligned with a, b, c even it has one more click. this is because of limitation that images can expand up to 3 column. 
   
 One important thing to note that this sample project is not using any external lib except **Room**. https://developer.android.com/training/data-storage/room/index.html
 
 ##### Things to note - 
 
 * This application only run and tested for portrait mode.
 * Application provides Accessibility support but not tested for this. 
 * Application is flexible enough to support easily any kind of feature changes and different locale.  
 * Sample is loaded, for showcase, with JUnit (using Robolectric, Mockito),Instrumented test (AndroidJunitRunner), UI and Integration testing With espresso, Although it is possible to add more test cases.
 * _Last but not least_, Worth to mention that --- To get random images application is using -   https://api.flickr.com/services/feeds/photos_public.gne which seems to produce random images on every hit but with some in-appropriate content so to filter the content *tag* is used but with *tag* server return same images for continuous hit.
     
    
<a href="url"><img src="https://github.com/VijayKumarYadav/MVPDemo/blob/master/images/category.png" height="360" width="220" ></a>
<a href="url"><img src="https://github.com/VijayKumarYadav/MVPDemo/blob/master/images/category_weight.png" height="360" width="220" ></a>
<a href="url"><img src="https://github.com/VijayKumarYadav/MVPDemo/blob/master/images/result.png" height="360" width="220" ></a>
## MVPDemo

#### Summary  
This sample is based on MVP Architecture guidelines and consisting of 2 modules - UI Module & Tracking modules.

It implement the following concept -

  *  On the first launch of application, user will see 6 random images coming from **flikr** (we call it category images) and these will be saved by tracking modules to show the same images in next application launches.
  * Clicking any one of them will leads to next screen where 12 random images will be shown from **flikr** again. Clicking further on them will show details of that image.
  * App keeps track of every click user made on the category images.
  * Now when user relaunch the application, he will see a welcome page showing him the most clicked category images and a way to go on category images screen. 
  * One most important thing of category images is that they will be rendered with weight and weight will be decided by the number of click user made to that particular images. 
  * Category images is rendered in a grid and can take max 3 column for max weight and 1 column in normal. 
  So this app is using proximity concept to render them in grid that's means
  if 3 images (lets say a, b, c) have 1 click and next 1 (say d) have 2 click, next 1 (e) have 5 click and last one (f) is having 10 click so a, b, c, d will be rendered with 1 column while e will take 2 and f will take 3 column. Please note that image d is aligned with a, b, c even it has one more click. this is because of limitation that images can expand up to 3 column. 
   
 One important thing to note that this sample project is not using any external lib except **Room**. https://developer.android.com/training/data-storage/room/index.html
 
 ##### Things to note - 
 
 * This application only run and tested for portrait mode.
 * Application provides Accessibility support but not tested for this. 
 * Application is flexible enough to support easily any kind of feature changes and different locale.  
 * Sample is loaded, for showcase, with JUnit (using Robolectric, Mockito),Instrumented test (AndroidJunitRunner), UI and Integration testing With espresso, Although it is possible to add more test cases.
 * _Last but not least_, Worth to mention that --- To get random images application is using -   https://api.flickr.com/services/feeds/photos_public.gne which seems to produce random images on every hit but with some in-appropriate content so to filter the content *tag* is used but with *tag* server return same images for continuous hit.
     
    
<a href="url"><img src="https://github.com/VijayKumarYadav/MVPDemo/blob/master/images/category.png" height="360" width="220" ></a>
<a href="url"><img src="https://github.com/VijayKumarYadav/MVPDemo/blob/master/images/category_weight.png" height="360" width="220" ></a>
<a href="url"><img src="https://github.com/VijayKumarYadav/MVPDemo/blob/master/images/result.png" height="360" width="220" ></a>
<a href="url"><img src="https://github.com/VijayKumarYadav/MVPDemo/blob/master/images/welcome.png?raw=true" height="360" width="220" ></a>  
