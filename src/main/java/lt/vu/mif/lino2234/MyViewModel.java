package lt.vu.mif.lino2234;

import lt.vu.mif.lino2234.bo.UserBo;
import lt.vu.mif.lino2234.views.UserView;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class MyViewModel {

	@WireVariable
	private UserBo userBo;

	private UserView user ;

	@Init
	public void init() {
		user = new UserView();
	}

	@Command
	public void doSave() {
		userBo.saveToEntity(user);
	}

	public UserView getUser() {
		return user;
	}

	public void setUser(UserView user) {
		this.user = user;
	}
}
