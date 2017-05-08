package lt.vu.mif.lino2234.viewModels;

import lt.vu.mif.lino2234.bo.BoardBo;
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
public class BoardsVm {

    @WireVariable
    private UserBo userBo;
    @WireVariable
    private BoardBo boardBo;

    private UserView user;
    private List<BoardView> boards = new ArrayList<>();
    private Long userId;
    private boolean adding;
    private boolean boardsVisible;
    private BoardView newBoard = new BoardView();

    @Init
    public void init() {
        boards = boardBo.getAll();
        userId = Long.parseLong(Executions.getCurrent().getParameter("uid"));
        if (userId != null) {
            user = userBo.findOne(userId);
        }
    }

    @Command
    @NotifyChange({"adding"})
    public void doAdd() {
        adding = true;
    }

    @Command
    @NotifyChange({"boardsVisible"})
    public void doToggleBoardsVisibility() {
        boardsVisible = !boardsVisible;
    }

    @Command
    @NotifyChange({"adding", "boards"})
    public void doSave() {
        boardBo.saveToEntity(newBoard);
        boards.add(newBoard);
        adding = false;
        newBoard = new BoardView();
        boards = boardBo.getAll();
    }

    @Command
    @NotifyChange({"user"})
    public void doSubscribe(@BindingParam("board") BoardView board ) {
        if (!user.getSubscriptions().contains(board)) {
            user.getSubscriptions().add(board);
            board.getSubscribers().add(user);
            userBo.saveToEntity(user);
        }
    }

    public UserView getUser() {
        return user;
    }

    public void setUser(UserView user) {
        this.user = user;
    }

    public List<BoardView> getBoards() {
        return boards;
    }

    public void setBoards(List<BoardView> boards) {
        this.boards = boards;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAdding() {
        return adding;
    }

    public void setAdding(boolean adding) {
        this.adding = adding;
    }

    public boolean isBoardsVisible() {
        return boardsVisible;
    }

    public void setBoardsVisible(boolean boardsVisible) {
        this.boardsVisible = boardsVisible;
    }

    public BoardView getNewBoard() {
        return newBoard;
    }

    public void setNewBoard(BoardView newBoard) {
        this.newBoard = newBoard;
    }
}
