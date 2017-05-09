package lt.vu.mif.lino2234.viewModels;

import lt.vu.mif.lino2234.bo.AdvertisementBo;
import lt.vu.mif.lino2234.bo.BoardBo;
import lt.vu.mif.lino2234.bo.UserBo;
import lt.vu.mif.lino2234.views.AdvertisementView;
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
public class AdvertisementsVm {

    @WireVariable
    private AdvertisementBo advertisementBo;
    @WireVariable
    private UserBo userBo;
    @WireVariable
    private BoardBo boardBo;

    private UserView user;
    private BoardView board;
    private List<AdvertisementView> advertisements = new ArrayList<>();
    private boolean adding;
    private Long boardId;
    private Long userId;
    private AdvertisementView newAd = new AdvertisementView();
    private AdvertisementView selectedAd;

    @Init
    public void init() {
        boardId = Long.parseLong(Executions.getCurrent().getParameter("bid"));
        userId = Long.parseLong(Executions.getCurrent().getParameter("uid"));
        if (boardId != null) {
            board = boardBo.findOne(boardId);
            advertisements = advertisementBo.getAllByBoardId(boardId);
        }
        if (userId != null) {
            user = userBo.findOne(userId);
        }
        if (advertisements != null && !advertisements.isEmpty()) {
            selectedAd = advertisements.get(0);
        }
    }

    @Command
    @NotifyChange({"adding"})
    public void doAdd() {
        adding = true;
    }

    @Command
    @NotifyChange({"adding", "newAd"})
    public void doCancel() {
        adding = false;
        newAd = new AdvertisementView();
    }

    @Command
    @NotifyChange({"adding", "advertisements", "newAd", "selectedAd"})
    public void doSave() {
        newAd.setBoard(board);
        newAd.setAuthor(user);
        newAd = advertisementBo.saveToEntity(newAd);
        advertisements.add(newAd);
        adding = false;
        if (selectedAd == null) {
            selectedAd = newAd;
        }
        newAd = new AdvertisementView();
    }

    @Command
    @NotifyChange({"selectedAd"})
    public void doSelect(@BindingParam("ad") AdvertisementView ad ) {
        selectedAd = ad;
    }

    public UserView getUser() {
        return user;
    }

    public void setUser(UserView user) {
        this.user = user;
    }

    public BoardView getBoard() {
        return board;
    }

    public void setBoard(BoardView board) {
        this.board = board;
    }

    public List<AdvertisementView> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<AdvertisementView> advertisements) {
        this.advertisements = advertisements;
    }

    public boolean isAdding() {
        return adding;
    }

    public void setAdding(boolean adding) {
        this.adding = adding;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AdvertisementView getNewAd() {
        return newAd;
    }

    public void setNewAd(AdvertisementView newAd) {
        this.newAd = newAd;
    }

    public AdvertisementView getSelectedAd() {
        return selectedAd;
    }

    public void setSelectedAd(AdvertisementView selectedAd) {
        this.selectedAd = selectedAd;
    }
}
