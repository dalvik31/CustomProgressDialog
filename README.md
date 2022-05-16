# CustomProgressDialog

> Step 1. Add the JitPack repository to your build file


```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  ```
> Step 2. Add the dependency

```
dependencies {
	        implementation 'com.github.dalvik31:CustomProgressDialog:Tag'
	}
  
  ```
  
> Step 3 Usage

```
 private val progressCustom = ProgressCustom.from(this)
 
   buttonShowProgress.setOnClickListener{
                   progressCustom
                .setCancelable(true)
                .colorText(android.R.color.darker_gray)
                .message("Custom message")
		.lottieAnimation(R.raw.animation)
                .colorBackground(android.R.color.white)
                .showProgress()
        }
        
          buttonHideProgress.setOnClickListener{
            progressCustom.hideProgress()
        }
 
 ```


