package org.darcula.test.kafka;

import java.io.Serializable;

/**
 * @author dk
 */
public class WrapperDTO implements Serializable {

    private String interfaceName;

    private String methodName;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "WrapperDTO{" +
                "interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
