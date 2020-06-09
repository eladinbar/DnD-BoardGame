package Controller;

import Model.TickListener;

public interface TickManager {
    boolean addListener(TickListener listener);
    void notifyListeners();
}
