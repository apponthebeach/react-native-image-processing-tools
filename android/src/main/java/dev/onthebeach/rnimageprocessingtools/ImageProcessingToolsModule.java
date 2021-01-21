package dev.onthebeach.rnimageprocessingtools;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class ImageProcessingToolsModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public ImageProcessingToolsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ImageProcessingTools";
    }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        int newNumberArgument = numberArgument + 1;
        callback.invoke("Received numberArgument: " + newNumberArgument + " stringArgument: " + stringArgument);
    }
}
