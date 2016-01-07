
# EasyLoading
## Showing LoadingView or EmptyView easily for Activity and Fragment in Android.
##### Update: Now, you can allocate an Animation to view when showing or dismissing. There are two default animation for showing/dismissing,
if you just want to show a simple Animation,you can pass the @param(anim) null to the method, we will start the default Animation for view.
## How to use
### Activity
#####1.Show LoadingView in an Activity
```java
ActivityLoading.showLoading(Activity activity, int layoutId)
ActivityLoading.showLoading(Activity activity, int layoutId, boolean dimBackground, boolean showAnimation, Animation anim)
ActivityLoading.showLoading(Activity activity, View view)
ActivityLoading.showLoading(Activity activity, View view, boolean dimBackground, boolean showAnimation, Animation anim)
```
#####2.Show EmptyView in an Activity
```java
ActivityLoading.showEmpty(Activity activity, int layoutId)
ActivityLoading.showEmpty(Activity activity, View view)
```
#####3.dismiss LoadingView / EmptyView from Activity
```java
ActivityLoading.dismiss(Activity activity)
ActivityLoading.dismiss(Activity activity, boolean showAnim, Animation anim)
```
### Fragment

#####1.Show LoadingView in Fragment
```java
FragmentLoading.showLoading(Fragment fragment, int layoutId)
FragmentLoading.showLoading(Fragment fragment, int layoutId, boolean dimBackground, boolean showAnimation, Animation anim)
FragmentLoading.showLoading(Fragment fragment, View view)
FragmentLoading.showLoading(Fragment fragment, View view, boolean dimBackground, boolean showAnimation, Animation anim)
```
#####2.Show EmptyView in Fragment
```java
FragmentLoading.showEmpty(Fragment fragment, int layoutId)
FragmentLoading.showEmpty(Fragment fragment, View view)
```
#####3.dismiss LoadingView / EmptyView from Fragment
```java
FragmentLoading.dismiss(Fragment fragment)
FragmentLoading.dismiss(Fragment fragment, boolean showAnim, Animation anim)
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

###ScreenShot
![http://img.my.csdn.net/uploads/201601/07/1452128584_2972.gif](http://img.my.csdn.net/uploads/201601/07/1452128584_2972.gif)

