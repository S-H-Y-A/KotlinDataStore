# KotlinDataStore

Android Preferences DataStore delegation for Kotlin.
This library is inspired by [chibatching / Kotpref](https://github.com/chibatching/Kotpref)

## Install

 ```kotlin:build.gradle.kts
//Kotlin-DSL
repositories {
    google()
    mavenCentral()
    //Add
    maven(url = "https://jitpack.io")
}

//Groovy-DSL
repositories {
    google()
    mavenCentral()
    //Add
    maven { url "https://jitpack.io" }
}

 ```

 ```kotlin:app/build.gradle.kts
dependencies {
    // Kotlin-DSL

    //All in one
    implementation("com.github.S-H-Y-A:KotlinDataStore:1.0")

    //core
    implementation("com.github.S-H-Y-A.KotlinDataStore:core:1.0")
    //optional
    implementation("com.github.S-H-Y-A.KotlinDataStore:compose:1.0")//compose support
    implementation("com.github.S-H-Y-A.KotlinDataStore:enum:1.0")//enum support
    implementation("com.github.S-H-Y-A.KotlinDataStore:moshi:1.0")// moshi support
    implementation("com.github.S-H-Y-A.KotlinDataStore:gson:1.0")//gson support
    implementation("com.github.S-H-Y-A.KotlinDataStore:initializer:1.0")// auto initializer


    //Groovy-DSL
    //All in one
    implementation 'com.github.S-H-Y-A:KotlinDataStore:1.0'

    //core
    implementation 'com.github.S-H-Y-A.KotlinDataStore:core:1.0'
    //optional
    implementation 'com.github.S-H-Y-A.KotlinDataStore:compose:1.0'//compose support
    implementation 'com.github.S-H-Y-A.KotlinDataStore:enum:1.0'//enum support
    implementation 'com.github.S-H-Y-A.KotlinDataStore:moshi:1.0'// moshi support
    implementation 'com.github.S-H-Y-A.KotlinDataStore:gson:1.0'//gson support
    implementation 'com.github.S-H-Y-A.KotlinDataStore:initializer:1.0'// auto initializer
}
 ```

## Create Preferences DataStore model

```kotlin:AccountPref
object AccountPref :
    KotlinDataStoreModel<AccountPref>() {
    val sex by enumNameFlowPref(Sex.NotSelected)
    val age by intFlowPref()
    val name by stringFlowPref()
    val friends by stringSetFlowPref()
}
```

## Initialize KotlinDataStore

use initalizer module (`com.github.User.Repo:Module`) or initialize on App.class

```kotlin:App.kt
//App.kt
class App :Application() {
    override fun onCreate() {
        KotlinDataStore.init(this)
    }
}

//AndroidManifest.xml
<manifest>
    <application
        android:name=".App">
        ...
    </application>
</manifest>
```

## Read and Write

```kotlin
//auto read
coroutineScope.launch {
    AccountPref.name.collect {
        textView.text = it
    }
}

// forceRead
coroutineScope.launch {
    val value = AccountPref.name.let {
        it.firstOrNull() ?: it.defaultValue
    }
    textView.text = value
}


//write
coroutineScope.launch {
    AccountPref.name.emit(value)
}
//or
AccountPref.name.emitIn(coroutineScope, value)

```

### With Jetpack Compose

```kotlin
var name by AccountPref.name.collectAsMutableState()

OutlinedTextField(
    value = name,
    onValueChange = { name = it }
)

```

I recommend you make tempValue

```kotlin
var savedName by AccountPref.name.collectAsMutableState()
var tempValue by remember { mutableStateOf("") }

LaunchedEffect(savedName) {
    tempValue = savedName
}

Column {
    OutlinedTextField(
        value = tempValue,
        onValueChange = { tempValue = it },
        trailingIcon = {
            SaveIcon {
                savedName = tempValue
            }
        }
    )
}
```

## License

```
Copyright 2015-2021 S-H-Y-A

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
