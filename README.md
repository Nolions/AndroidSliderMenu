# AndroidSliderMenuDemo

Android 實作左右滑動選單& Popup 選單

## Install

在Module下的build.gradle中新增 android material的 implementation dependencies

```
dependencies {
        ...
    implementation 'com.google.android.material:material:1.1.0'
        ...
}
```

## Use

在res/menu下新增一個`bottom.xml`檔案，並在檔案中新增Item項目做為選單項目

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/home"
        android:icon="@drawable/ic_home"
        android:title="@string/home" />

    <item
        android:id="@+id/account"
        android:icon="@drawable/ic_account"
        android:title="@string/account" />

    <item
        android:id="@+id/more"
        android:icon="@drawable/ic_more_horiz"
        android:title="@string/more" />

</menu>
```

使用`DrawerLayout`元素將畫面主要呈現去包起來，並在與主畫面同階層中新增`NavigationView`元件的元素。`NavigationView`元素中的`menu`屬性指向指向新增res/menu/slide.xml

```
<androidx.drawerlayout.widget.DrawerLayout
         android:id="@+id/drawerLayout"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         tools:context=".MainActivity">

        <!--主畫面-->
        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            ... >
            ...
        </RelativeLayout>

        <!-- Slide Menu -->
        <com.google.android.material.navigation.NavigationView
            android:layout_width="240dp"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:background="#cccccc"
            app:menu="@menu/slide" />

</androidx.drawerlayout.widget.DrawerLayout>
```

> DrawerLayout中除了可以使用NavigationView來製作滑動選單外，也可以使用其他View或Layout元件去製作滑動區塊。
> `android:layout_gravity`屬性可以用來控制是要由主畫面右側或左側滑入，其中`start`主畫面左側滑入，`end`則是由右側滑入。

實作左右滑入layout
```
<androidx.drawerlayout.widget.DrawerLayout
    android:id="@+id/drawerLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent">

     <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

           ...

     </RelativeLayout>

     <com.google.android.material.navigation.NavigationView xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/slideMenu"
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="#cccccc"
        app:headerLayout="@layout/slide_header"
        app:menu="@menu/slide_menu" />

    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/infoLayout"
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:layout_gravity="end"
        android:background="#cccccc">
    
        ...
    
    </RelativeLayout>

</androidx.drawerlayout.widget.DrawerLayout>
```

## 替NavigationView加上Header

新增slide_header.xml來作為slide menu header的layout

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="160dp"
    android:background="@drawable/bg_slide_header"
    android:gravity="center_vertical"
    android:orientation="horizontal"
    android:padding="12sp">

    <ImageView
        android:layout_width="72dp"
        android:layout_height="72dp"
        android:contentDescription="@null"
        android:background="@drawable/ic_dashboard"
        android:paddingRight="8dp"
        android:paddingEnd="8dp" />

    <TextView
        android:id="@+id/txtHeader"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:ellipsize="end"
        android:paddingTop="4dp"
        android:textSize="18sp"
        android:text="@string/dashboard"
        android:textColor="#ffffff" />

</LinearLayout>
```

NavigationView元件加上`app:headerLayout`加上元素，然後將值指向`slide_header.xml`

```
<com.google.android.material.navigation.NavigationView
    android:layout_width="240dp"
    android:layout_height="match_parent"
    android:layout_gravity="start"
    android:background="#cccccc"
    app:headerLayout="@layout/slide_header"
    app:menu="@menu/slide" />
```

## 使用Toolbar來對NavigationView進行操作

如果UI設計中取消預設AppBar改用Toolbar，則也可以進一部透過Toolbar對NavigationView進行開關。

在Activity程式碼中加入以下程式碼，使Toolbar與NavigationView整合

```
val actionBarDrawerToggle = ActionBarDrawerToggle(
    this,
    drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close
)

drawerLayout.addDrawerListener(actionBarDrawerToggle)
actionBarDrawerToggle.syncState()
```

## Topbar上添加`popupmenu`

添加以下程式碼到你的activity或fragment java or kotlin 程式碼中

```
override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.popup_menu, menu)

    return true
}
```

### showAsAction param

|value|describe|
|-----|--------|
| ifRoom | 根據寬度來決定是顯示在上層，還是只顯示在下層選單中 |
| withText | 顯示標題，需搭配always或ifRoom使用才有效過(EX: always|withText) |
| never | 隱藏在下層選單中，預設值 |
| always | 總是出現最上增 |
| collapseActionView | |
