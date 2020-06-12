package Controller;

import Model.UnitPackage.TickListener;

public interface TickManager {
    boolean addListener(TickListener listener);
    void notifyListeners();
}
