import { NativeModules } from 'react-native';

const { ImageProcessingTools } = NativeModules;

class RNImageProcessingTools {
    getBlackAndWhiteImageFromUrl(imageUrl, callback) {
        ImageProcessingTools.getBlackAndWhiteImageFromUrl(imageUrl, (response) => {
            const base64Image = 'data:image/png;base64,'+response;
            callback(base64Image);
        });
    }
}

const imageProcessingTools = new RNImageProcessingTools();

export default imageProcessingTools;