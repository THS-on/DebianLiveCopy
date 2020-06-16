package ch.fhnw.dlcopy.gui.swing;

import ch.fhnw.util.StorageDevice;
import java.util.concurrent.locks.Lock;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * parses udisks output paths and adds the corresponding storage devices to the
 * installation transfer list
 *
 * @author Ronny Standtke <ronny.standtke@gmx.net>
 */
public class InstallTransferStorageDeviceAdder extends StorageDeviceAdder {

    /**
     * creates a new InstallStorageDeviceAdder
     *
     * @param addedPath the added udisks path
     * @param showHardDisks if true, paths to hard disks are processed,
     * otherwise ignored
     * @param dialogHandler the dialog handler for updating storage device lists
     * @param listModel the ListModel of the storage devices JList
     * @param list the storage devices JList
     * @param swingGUI the DLCopySwingGUI
     * @param lock the lock to aquire before adding the device to the listModel
     */
    public InstallTransferStorageDeviceAdder(String addedPath,
            boolean showHardDisks,
            StorageDeviceListUpdateDialogHandler dialogHandler,
            DefaultListModel<StorageDevice> listModel,
            JList<StorageDevice> list, DLCopySwingGUI swingGUI, Lock lock) {

        super(addedPath, showHardDisks, dialogHandler,
                listModel, list, swingGUI, lock);
    }

    @Override
    public void initDevice() {
        addedDevice.getPartitions().forEach((partition) -> {
            partition.getUsedSpace(false);
        });
    }

    @Override
    public void updateGUI() {
        swingGUI.installTransferStorageDeviceListChanged();
    }
}
