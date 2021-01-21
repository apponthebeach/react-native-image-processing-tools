#import "ImageProcessingTools.h"

@implementation ImageProcessingTools

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(sampleMethod:(NSString *)stringArgument numberParameter:(nonnull NSNumber *)numberArgument callback:(RCTResponseSenderBlock)callback)
{
    // TODO: Implement some actually useful functionality
    int numberArgumentValue = [numberArgument intValue];
    NSNumber *newNumberArgument = [NSNumber numberWithInt:numberArgumentValue*2];
    callback(@[[NSString stringWithFormat: @"numberArgument: %@ stringArgument: %@", newNumberArgument, stringArgument]]);
}

@end
