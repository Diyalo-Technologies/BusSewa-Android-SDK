`Latest realease: 0.0.1-alpha01`

##### Step: 1 Add dependency

Add the following dependency in app gradle file.

```groovy
dependencies {
   implementation 'com.diyalotech:bus-sewa-android-sdk:0.0.1-alpha01'
}
```

##### Step: 2 Launch BusSewaSDK

Register activity callback as property initializer in an instance of AppCompatActivity.

```kotlin
private val busSdkLauncher = registerForActivityResult(
   ActivityResultContracts.StartActivityForResult(),
   ::onBusSewaSdkResult
)
```

BusSewaSDKActivity needs to be called with appropriate data. Allowed params are described in the last section. Environment can be switched between production and test.

```kotlin
private fun launch() {
   val intent = Intent(this, BusSewaSDKActivity::class.java)
   val client = BusSewaClient(
       clientId = "admin",
       clientSecret = "admin",
       environment = BusSewaSdkEnv.TEST,
       busTheme = BusTheme() //info on customization below
   )
   intent.putExtra(BUS_SDK_CLIENT_INFO, client)
   trainSdkLauncher.launch(intent)
}

```

OnActivityResultCallback reponse handling

```kotlin
private fun onBusSewaSdkResult(activityResult: ActivityResult) {
   if (activityResult.resultCode == Activity.RESULT_OK) {
       val data =
           (activityResult.data?.getSerializableExtra(BUS_SDK_RESPONSE) as HashMap<String, Any>?)

       println("RESULT_MAP: $data")
       val jsonString = Gson().toJson(data)
       println(jsonString)
       
       println(data?.get(BUS_SDK_REQ_ID))
       println(data?.get(BUS_SDK_AMOUNT)) //Double

       val props = (data?.get(BUS_SDK_PROPERTIES) as HashMap<String, Any>?)
       println(props?.get(BUS_SDK_TICKET))
       println(props?.get(BUS_SDK_TRIP_ID))
       println(props?.get(BUS_SDK_SERVICE_CODE))

       //iterate on properties
       props?.forEach { map ->
           println("${map.key} - ${map.value}")
       }
   } else if (activityResult.resultCode == Activity.RESULT_CANCELED) {
            val data = 
                activityResult.data?.getStringExtra(BUS_SDK_MESSAGE) ?:
                "Cancelled."
            Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
        }
}
```

For more details on what data is received in the result map, refer to the following json representation.

##### UI Customization

An instance of BusSDKClient needs to be passed in with **intent** data. Where clientUser/clientSecret are client credentials (compulsory). Theme config is optional.

```kotlin
data class TrainSDKClient(
   val user: String,
   val password: String,
   val busTheme: BusTheme = createDefaultTheme()
)
```

In order to customize the theme of the application, following options are available. Refer to the example source code to customize necessary options. And pass in the object along with BusSDKClient as shown in section 2.

```kotlin
//custom theme for intent
val busTheme = BusTheme(
    primary = R.color.purple_200,
    primaryVariant = R.color.purple_200,
    secondary = R.color.purple_200,
    busThemeDarkLight = BusThemeDarkLight.LIGHT
)

data class TrainTheme(
   @ColorRes
   val primary: Int = R.color.design_default_color_primary,
   @ColorRes
   val primaryVariant: Int = R.color.design_default_color_primary_variant,
   @ColorRes
   val secondary: Int = R.color.design_default_color_secondary,
   val trainThemeDarkLight: TrainThemeDarkLight = TrainThemeDarkLight.DEFAULT
)

/**
* @property DEFAULT follows system theme
* */
enum class TrainThemeDarkLight: Parcelable {
   LIGHT,
   DARK,
   DEFAULT
}
```
