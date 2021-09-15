package javaagent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * @author Chen Xiao
 * @since 2021-09-13 16:15
 */
public class AgentAttach {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, InterruptedException {
        // 85355 表示目标进程的PID
        VirtualMachine virtualMachine = VirtualMachine.attach("25850");
        // 指定Java Agent的jar包路径
        try {

            virtualMachine.loadAgent("/Users/chenxiao/IdeaProjects/mylearnproject/demo/agent/target/agent-0.0.1-SNAPSHOT.jar");
            Thread.sleep(3000);

        } catch (AgentLoadException e) {
            e.printStackTrace();
        } catch (AgentInitializationException e) {
            e.printStackTrace();
        } finally {
            virtualMachine.detach();
        }

    }
}
