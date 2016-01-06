
# EasyLoading
## Showing LoadingView or EmptyView easily for Activity and Fragment in Android.
## How to use
### Activity
#####1.Show LoadingView in an Activity
```java
ActivityLoading.showLoading(Activity activity, int layoutId)
ActivityLoading.showLoading(Activity activity, View view)
ActivityLoading.showLoading(Activity activity, int layoutId, boolean dimBackground)
ActivityLoading.showLoading(Activity activity, View view, boolean dimBackground)
```
#####2.Show EmptyView in an Activity
```java
ActivityLoading.showEmpty(Activity activity, int layoutId)
ActivityLoading.showEmpty(Activity activity, View view)
```
#####3.dismiss LoadingView / EmptyView from Activity
```java
ActivityLoading.dismiss(Activity activity)
```
### Fragment

#####1.Show LoadingView in Fragment
```java
FragmentLoading.showLoading(Fragment fragment, int layoutId)
FragmentLoading.showLoading(Fragment fragment, View view)
FragmentLoading.showLoading(Fragment fragment, int layoutId, boolean dimBackground)
FragmentLoading.showLoading(Fragment fragment, View view, boolean dimBackground)
```
#####2.Show EmptyView in Fragment
```java
FragmentLoading.showEmpty(Fragment fragment, int layoutId)
FragmentLoading.showEmpty(Fragment fragment, View view)
```
#####3.dismiss LoadingView / EmptyView from Fragment
```java
FragmentLoading.dismiss(Fragment fragment)
```
####Notice
To showing LoadingView or EmptyView in a Fragment, the Fragment’s rootView must be a FrameLayout or FrameLayout’s subclass, there're two ways to do it.
 </br> <1>.Define a xml-layout which rootView is FrameLayout for the Fragment.
 </br> <2>.Make your Fragment extends LoadingSupportFragment and achieve the method contentViewLayoutId() to return your xml-layout's Id, and you don’t need to override the method onCreateView();
Now, you can show the EmptyView or LoadingView simply;
</br></br>

为了让Fragment能够显示LoadingView或EmptyView，Fragment所创建的view必须是FrameLayout或者FrameLayout的子类，这里有两种快捷的方法.
 <br> <1>.定义一个layout布局文件然后让Fragment创建view，但该layout的rootView必须为FrameLayout；
 <br> <2>.继承LoadingFragment或者LoadingSupportFragment，并且实现方法contentViewLayoutId()，在该方法中返回自定义的layout布局文件，该布局文件则没有rootView的限制，rootView可以是任意的控件，并且，使用这种方法也就不需要再重写Fragment的onCreateView方法；
</br></br>

###截图
![http://img.my.csdn.net/uploads/201509/24/1443103079_9956.jpg](http://img.my.csdn.net/uploads/201509/24/1443103079_9956.jpg)
![http://img.my.csdn.net/uploads/201509/24/1443103078_1740.jpg](http://img.my.csdn.net/uploads/201509/24/1443103078_1740.jpg)
![http://img.my.csdn.net/uploads/201509/24/1443103078_5010.jpg](http://img.my.csdn.net/uploads/201509/24/1443103078_5010.jpg)
![http://img.my.csdn.net/uploads/201509/24/1443103077_4555.jpg](http://img.my.csdn.net/uploads/201509/24/1443103077_4555.jpg)

