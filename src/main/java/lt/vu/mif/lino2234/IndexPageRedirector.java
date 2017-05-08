package lt.vu.mif.lino2234;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

import java.util.Map;

public class IndexPageRedirector implements Initiator {

    @Override
    public void doInit(Page page, Map<String, Object> args) throws Exception {
        Executions.getCurrent().sendRedirect("/users.zul");
    }
}
