package listener;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * The listener provides test re-run in case of failed. And write test in .txt file
 */

public class RetryListener implements TestExecutionExceptionHandler, AfterEachCallback {

    private static final int MAX_RETRIES = 3; //here we declare how much times test retries
    private static final Set<String> failedTestNames = new HashSet<>();

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        for (int i = 0; i < MAX_RETRIES; i++) {
            try {
                context.getRequiredTestMethod().invoke(context.getRequiredTestInstance());
                return;
            } catch (Throwable ex){
                throwable = ex;
            }
        }
        throw throwable;

    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Method method = context.getRequiredTestMethod();
        String testClassName = method.getDeclaringClass().getName();
        String testMethodName = method.getName();
        String testToWrite = String.format(" --tests [%s] - %s", testClassName, testMethodName);
        context.getExecutionException().ifPresent(x -> failedTestNames.add(testToWrite));
    }
@SneakyThrows
    public static void saveFailedTests(){
        String output = System.getProperty("user.dir") + "/src/test/resources/failedTests.txt";
        String result = String.join("\n", failedTestNames);
        FileUtils.writeStringToFile(new File(output), result);
    }
}
