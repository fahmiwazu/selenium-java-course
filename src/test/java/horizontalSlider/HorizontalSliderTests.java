package horizontalSlider;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HorizontalSliderTests extends BaseTests {

    @Test
    public void testSliderMovesToTargetValue(){
        var horizontalSlider = theInternetHomePage.clickHorizontalSlider();

        // Get initial value
        String initialValue = horizontalSlider.getRangeText();
        System.out.println("Initial slider value: " + initialValue);

        // Move slider to the right until it reaches value "4"
        while(!horizontalSlider.getRangeText().equals("4")) {
            horizontalSlider.moveRightSlider();
            String currentValue = horizontalSlider.getRangeText();
            System.out.println("Current slider value: " + currentValue);

            // Safety check to prevent infinite loop
            if(Double.parseDouble(currentValue) > 5) {
                break;
            }
        }

        // Assert that the final value is "4"
        String finalValue = horizontalSlider.getRangeText();
        Assert.assertEquals(finalValue, "4", "Slider value should be 4");
        System.out.println("Test passed! Final slider value: " + finalValue);
    }

    @Test
    public void testSliderMovesToDifferentValues(){
        var horizontalSlider = theInternetHomePage.clickHorizontalSlider();

        // Test moving to value "2"
        moveSliderToValue(horizontalSlider, "2");
        Assert.assertEquals(horizontalSlider.getRangeText(), "2", "Slider should be at value 2");

        // Test moving to value "3.5"
        moveSliderToValue(horizontalSlider, "3.5");
        Assert.assertEquals(horizontalSlider.getRangeText(), "3.5", "Slider should be at value 3.5");

        // Test moving back to value "1"
        moveSliderToValue(horizontalSlider, "1");
        Assert.assertEquals(horizontalSlider.getRangeText(), "1", "Slider should be at value 1");
    }

    @Test
    public void testSliderBoundaries(){
        var horizontalSlider = theInternetHomePage.clickHorizontalSlider();

        // Move to maximum value (5)
        moveSliderToValue(horizontalSlider, "5");
        Assert.assertEquals(horizontalSlider.getRangeText(), "5", "Slider should be at maximum value 5");

        // Move to minimum value (0)
        moveSliderToValue(horizontalSlider, "0");
        Assert.assertEquals(horizontalSlider.getRangeText(), "0", "Slider should be at minimum value 0");
    }

    /**
     * Helper method to move slider to a specific target value
     * @param horizontalSlider - the slider page object
     * @param targetValue - the target value to move to
     */
    private void moveSliderToValue(pages.HorizontalSliderPage horizontalSlider, String targetValue) {
        String currentValue = horizontalSlider.getRangeText();
        double current = Double.parseDouble(currentValue);
        double target = Double.parseDouble(targetValue);

        int maxAttempts = 20; // Safety limit to prevent infinite loops
        int attempts = 0;

        while(!currentValue.equals(targetValue) && attempts < maxAttempts) {
            if(current < target) {
                horizontalSlider.moveRightSlider();
            } else {
                horizontalSlider.moveLeftSlider();
            }

            currentValue = horizontalSlider.getRangeText();
            current = Double.parseDouble(currentValue);
            attempts++;

            System.out.println("Moving slider... Current value: " + currentValue + ", Target: " + targetValue);
        }

        if(attempts >= maxAttempts) {
            System.out.println("Warning: Maximum attempts reached while trying to move slider to " + targetValue);
        }
    }
}