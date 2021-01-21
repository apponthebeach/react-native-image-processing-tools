import { NativeModules } from 'react-native';

const { ImageProcessingTools } = NativeModules;

class RNImageProcessingTools {
    sampleMethod(stringArgument, numberArgument, callback) {
        ImageProcessingTools.sampleMethod(stringArgument, numberArgument, (message) => {
            callback(message);
        });
    }
}

const imageProcessingTools = new RNImageProcessingTools();

export default imageProcessingTools;