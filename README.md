# Slide-viewpager-library
Import library *
build.gradle (app):
  dependencies {
    compile 'com.github.thanghh:Slide-viewpager-library:v1.2.3'
  }
 
build.gradle (project):
  allprojects {
   repositories {
    maven {
            url "https://jitpack.io"
        }
      }
   }
   
Set slide to xml file:
 <thanghh.slidelibrary.SlideViewPager
        android:id="@+id/..."
        android:layout_width="match_parent"
        android:layout_height="@dimen/218dp"
        app:autoScroll="true"
        />

Set array image to java file:
slideViewPager =  findViewById(...);
slideViewPager.setListImage(List<Integer> mListImage);
