package lt.vu.mif.lino2234.viewModels;

import lt.vu.mif.lino2234.bo.UserBo;
import lt.vu.mif.lino2234.views.BoardView;
import lt.vu.mif.lino2234.views.UserView;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class UsersVm {

	@WireVariable
	private UserBo userBo;

	private UserView user = new UserView();
	private List<UserView> users = new ArrayList<>();
	private boolean adding;

	@Init
	public void init() {
		users = userBo.getAll();
	}

	@Command
	@NotifyChange({"user", "adding", "users"})
	public void doSave() {
		userBo.saveToEntity(user);
		users.add(user);
		user = new UserView();
		adding = false;
	}

	@Command
	@NotifyChange({"adding"})
	public void doAdd() {
		adding = true;
	}

	@Command
	public void doView(@BindingParam("user") UserView user ) {
		Executions.getCurrent().sendRedirect(String.format("/boards.zul?uid=%s", user.getId().toString()));
	}

	public UserView getUser() {
		return user;
	}

	public void setUser(UserView user) {
		this.user = user;
	}

	public List<UserView> getUsers() {
		return users;
	}

	public void setUsers(List<UserView> users) {
		this.users = users;
	}

	public boolean isAdding() {
		return adding;
	}

	public void setAdding(boolean adding) {
		this.adding = adding;
	}
}
