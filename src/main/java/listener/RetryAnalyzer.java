package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static int maxTry = 2; // Run the failed test 2 times

    @Override
    public boolean retry(ITestResult iTestResult) {
        try {
            if (!iTestResult.isSuccess()) { // Check if test did not succeed
                if (count < maxTry) { // Check if maxtry count is reached
                    count++; // Increase the maxTry count by 1
                    System.out.println("Retrying test " + iTestResult.getName() + " with status "
                            + getResultStatusName(iTestResult.getStatus()) + " for the " + (count) + " time(s).");
                    iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
                    return true; // Tells TestNG to re-run the test
                } else {
                    iTestResult.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
                }
            } else {
                iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println("Test FAILED: " + iTestResult.getMethod().getMethodName());
        return false;
    }

    private String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }

}
