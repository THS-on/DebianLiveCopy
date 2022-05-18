package ch.fhnw.dlcopy.gui.javafx.ui;

import ch.fhnw.dlcopy.gui.javafx.SceneContext;
import ch.fhnw.dlcopy.model.PresentationModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;

/**
 * This class represents a view to be displayed.
 * A view is the wrapper for the content in the screen.
 */
public abstract class View{

    protected SceneContext context;
    protected PresentationModel model = PresentationModel.getInstance();
    protected URL resourcePath;
    protected ResourceBundle stringBundle = ResourceBundle.getBundle("strings/Strings");

    @FXML private Label lblInfo;
    private String defaultInfo = "";

    /**
     * This function is called, when the view should be deinitalized.
     * It has to be called manually!
     */
    public void deinitialize(){}

    @FXML
    /**
     * This methode is called from JavaFX, when a instance of the view is generated.
     */
    public final void initialize(){
        initSelf();
        initControls();
        layoutControls();
        setupBindings();
        setupEventHandlers();
        setupValueChangedListeners();
    }

    /**
    * This methode is called durring the initalize - process.
    * The class itslefe should be initalized here.
    * In here the refferences to the FXML - elements are not null. In the constructor, theses elements are all null - references.
    */
    protected void initSelf(){}

    /**
    * This methode is called durring the initalize - process.
    * All initalizeations of the controlls should be triggered from this methode
    */
    protected void initControls(){}

    /**
     * This methode is called durring the initalize - process.
     * In this methode the size and the position of the controls should be adjusted, if needed.
     */
    protected void layoutControls(){}

    /**
     * This methode is called durring the initalize - process.
     * In this methode JavaFX - bindings are set up
     */
    protected void setupBindings(){}

    /**
    * This methode is called durring the initalize - process.
    * In this methode JavaFX - event handlers are set up
    */
    protected void setupEventHandlers(){}

    /**
    * This methode is called durring the initalize - process.
    * In this methode JavaFX - value change listeners are set up
    */
    protected void setupValueChangedListeners(){}

    /**
     * This methode adds a tooltip to a control.
     * When the mouse hovers over the control, a tooltip with the given text will appear.
     * @param c The control, where the tooltip should be added
     * @param s The string to be shown in the tooltip
     */
    protected void addToolTip(Control c, String s){
        Tooltip tt = new Tooltip(s);
        tt.setPrefWidth(400);
        tt.setWrapText(true);
        c.setTooltip(tt);
    }

     /**
     * Prints the given information on the bottem of the view
     * @param info The information to be printed
     */
    protected final void printInfo(String info){
        if (lblInfo != null){
            defaultInfo = info;
            lblInfo.setText(info);
        }
    }


    /**
    * Returns the root parent. This parent can be displayed in a FX-scene
    * For displaying an other scene, this view can call `context.setScene(new ViewToBeDisplayed())`
    * @param context The context, where to set the scene
    * @return A Parent to be displayed
    */
    public Parent getRoot(SceneContext context) throws IOException{
        this.context = context;

        FXMLLoader loader = new FXMLLoader(resourcePath);
        loader.setController(this);
        loader.setResources(stringBundle);
        return loader.load();
    }
 }
