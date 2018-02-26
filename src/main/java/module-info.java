module net.eightlives.periodic.view.fx {
    requires net.eightlives.periodic.core;
    requires javafx.graphics;
    requires javafx.controls;

    exports net.eightlives.periodic.view.fx;

    opens css;

    provides net.eightlives.periodic.core.PeriodicView
            with net.eightlives.periodic.view.fx.FXPeriodicView;
}