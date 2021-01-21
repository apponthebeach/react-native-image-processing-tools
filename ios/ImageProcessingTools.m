#import "ImageProcessingTools.h"
#import <UIKit/UIKit.h>
#import <CoreImage/CoreImage.h>

@implementation ImageProcessingTools

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(getBlackAndWhiteImageFromUrl:(NSString *)imageUrl callback:(RCTResponseSenderBlock)callback) {
    NSURL *imageNSURL = [NSURL URLWithString:imageUrl];
    CIImage *originalCIImage = [CIImage imageWithContentsOfURL:imageNSURL];
    //CIFilter *blackAndWhiteFilter = [CIFilter filterWithName:@"CIPhotoEffectMono"];
    CIFilter *blackAndWhiteFilter = [CIFilter filterWithName:@"CIPhotoEffectTonal"];
    [blackAndWhiteFilter setValue:originalCIImage forKey:kCIInputImageKey];
    CIImage *blackAndWhiteImage = blackAndWhiteFilter.outputImage;
    UIImage *anUIImage = [UIImage imageWithCIImage:blackAndWhiteImage];
    NSData *imageData = UIImagePNGRepresentation(anUIImage);
    NSString *imageDataAsString = [imageData base64EncodedStringWithOptions:NSDataBase64EncodingEndLineWithLineFeed];
    callback(@[imageDataAsString]);
}

@end
