package goveed20.BitcoinPaymentService.aspects;

import goveed20.PaymentConcentrator.payment.concentrator.plugin.AsyncLogging;
import goveed20.PaymentConcentrator.payment.concentrator.plugin.LogDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class RetailerAspect {

    @Autowired
    private AsyncLogging asyncLogging;

//    @Before("execution(public * goveed20.BitcoinPaymentService.services.RetailerService.*(..)) || " +
//            "execution(* goveed20.BitcoinPaymentService.controllers.RetailerController.*(..))")
//    public void paymentBefore(JoinPoint joinPoint) {
//        LogDTO logDTO = null;
//        Object[] arguments = joinPoint.getArgs();
//        String className = joinPoint.getTarget().getClass().getSimpleName();
//        String methodName = joinPoint.getSignature().getName();
//        String message = generateMessage(className, methodName, arguments, true);
//        try {
//            logDTO = generateLog(className, methodName, "INFO", message);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        asyncLogging.callLoggingFeignClient(logDTO);
//    }
//
//    @AfterReturning("execution(public * goveed20.BitcoinPaymentService.services.RetailerService.*(..))")
//    public void paymentServiceAfterSuccess(JoinPoint joinPoint) {
//        LogDTO logDTO = null;
//        Object[] arguments = joinPoint.getArgs();
//        String className = joinPoint.getTarget().getClass().getSimpleName();
//        String methodName = joinPoint.getSignature().getName();
//        String message = generateMessage(className, methodName, arguments, false);
//        try {
//            logDTO = generateLog(className, methodName, "INFO", message);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        asyncLogging.callLoggingFeignClient(logDTO);
//    }
//
//    @AfterThrowing(pointcut = "execution(public * goveed20.BitcoinPaymentService.services.RetailerService.*(..)) || " +
//            "execution(* goveed20.BitcoinPaymentService.controllers.RetailerController.*(..))", throwing = "error")
//    public void paymentServiceAfterError(JoinPoint joinPoint, Throwable error) {
//
//        LogDTO logDTO = null;
//        String className = joinPoint.getTarget().getClass().getSimpleName();
//        String methodName = joinPoint.getSignature().getName();
//        String message = error.getMessage();
//        try {
//            logDTO = generateLog(className, methodName, "ERROR", message);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        asyncLogging.callLoggingFeignClient(logDTO);
//    }
//
//    private LogDTO generateLog(String className, String methodName, String logLevel, String message) throws ParseException {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//        return LogDTO.builder()
//                .date(formatter.parse(formatter.format(new Date())))
//                .serviceName("bitcoin-service")
//                .className(className)
//                .methodName(methodName)
//                .logLevel(logLevel)
//                .message(message)
//                .build();
//    }
//
//    private String generateMessage(String className, String methodName, Object[] arguments, boolean isBefore) {
//        String returnMessage;
//        returnMessage = className.equals("PaymentController") ?
//                generateControllerMessage(methodName, arguments, isBefore)
//                :
//                generateServiceMessage(methodName, arguments, isBefore);
//
//        return returnMessage;
//    }
//
//    private String generateServiceMessage(String methodName, Object[] arguments, boolean isBefore) {
//        String message;
//        switch (methodName) {
//            case "checkPaymentServiceFields":
//                message = isBefore ?
//                        "Checking registration fields on bitcoin service"
//                        :
//                        "Bitcoin service registration fields successfully checked";
//                break;
//            default:
//                message = "";
//        }
//
//        return message;
//    }
//
//    private String generateControllerMessage(String methodName, Object[] arguments, boolean isBefore) {
//        String message;
//        switch (methodName) {
//            case "checkPaymentServiceFields":
//                message = isBefore ?
//                        "Starting checking registration fields on bitcoin service"
//                        :
//                        "Successfully checked registration fields on bitcoin service";
//                break;
//            default:
//                message = "";
//        }
//
//        return message;
//    }

}
