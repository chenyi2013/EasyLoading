
# EasyLoading
## Showing LoadingView or EmptyView easily for Activity and Fragment in Android.
## How to use
### Activity
#####1.Show LoadingView in an Activity
```java
ActivityTool.showLoading(Activity activity, int layoutId)
ActivityTool.showLoading(Activity activity, View view)
ActivityTool.showLoading(Activity activity, int layoutId, boolean dimBackground)
ActivityTool.showLoading(Activity activity, View view, boolean dimBackground)
```
#####2.Show EmptyView in an Activity
```java
ActivityTool.showEmpty(Activity activity, int layoutId)
ActivityTool.showEmpty(Activity activity, View view)
```
#####3.dismiss LoadingView / EmptyView from Activity
```java
ActivityTool.dismiss(Activity activity)
```
### Fragment

#####1.Show LoadingView in Fragment
```java
FragmentV4Tool.showLoading(Fragment fragment, int layoutId)
FragmentV4Tool.showLoading(Fragment fragment, View view)
FragmentV4Tool.showLoading(Fragment fragment, int layoutId, boolean dimBackground)
FragmentV4Tool.showLoading(Fragment fragment, View view, boolean dimBackground)
```
#####2.Show EmptyView in Fragment
```java
FragmentV4Tool.showEmpty(Fragment fragment, int layoutId)
FragmentV4Tool.showEmpty(Fragment fragment, View view)
```
#####3.dismiss LoadingView / EmptyView from Fragment
```java
FragmentV4Tool.dismiss(Activity activity)
```
####Notice
    To showing LoadingView or EmptyView in a Fragment, the Fragment’s rootView must be a FrameLayout or FrameLayout’s subclass, you can define a xml-layout which rootView is FrameLayout for the Fragment or just make your Fragment extends  LoadingSupportFragment / LoadingFragment and achieve the method contentViewLayoutId() to return your xml-layoutId, and you don’t need to override the method onCreateView(); yeah, you can show the EmptyView or LoadingView simply;</br>
    为了让Fragment能够显示LoadingView或EmptyView，Fragment所创建的view必须是FrameLayout或者FrameLayout的子类，这里有两种快捷的方法，一是同往常一样，定义一个layout布局文件然后让Fragment创建view，但该layout的rootView必须为FrameLayout；二是继承LoadingFragment或者LoadingSupportFragment，并且实现方法contentViewLayoutId()，在该方法中返回自定义的layout布局文件，该布局文件则没有rootView的限制，rootView可以是任意的控件，并且，使用这种方法也就不需要再重写Fragment的onCreateView方法；
