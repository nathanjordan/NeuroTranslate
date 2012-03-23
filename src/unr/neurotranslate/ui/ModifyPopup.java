package unr.neurotranslate.ui;
import java.util.ArrayList;

import org.gnome.glade.XML;
import org.gnome.gtk.Button;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreePath;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;


public class ModifyPopup {

	// Reference the two ListStores for active and available 
	private static ListStore activeModel;
	private static ListStore availableModel;
	private static TreeView activeView;
	private static TreeView availableView;
	private static TreeViewColumn activeColumn;
	private static TreeViewColumn availableColumn;
	private static DataColumnString activeHeader;
	private static DataColumnString availableHeader;
	private static ArrayList<String> activeList;
	private static ArrayList<String> availableList;
	private static Window popupWin;
	
	public ModifyPopup( ) {

		// create button references for the modify popup
		final Button cancelButton = (Button) GladeParseUtil.grabWidget( "modifyCancel", "window2" );
		final Button confirmButton = (Button) GladeParseUtil.grabWidget( "modifyConfirm", "window2" );
		final Button addButton = (Button) GladeParseUtil.grabWidget( "modifyAdd", "window2" );
		final Button removeButton = (Button) GladeParseUtil.grabWidget( "modifyRemove", "window2" );
		
		//popupWin = (Window) popup.getWidget("window2");
		popupWin = (Window) GladeParseUtil.grabWidget( "window2", "window2" );			
		
		// Set connects for handling each button event
		// Set cancel event		
		cancelButton.connect( new Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
			
				// Hide the window if user pushes cancel
				popupWin.hide();
				
			}
		});
		
		// Set confirm event
		
		// Set add event
		
		// Set remove event
		
	}
	
	// Call this static function to build the popup
	public static void buildPopup() {
		
		// Grab the two treeview widgets
		activeView = (TreeView) GladeParseUtil.grabWidget( "activeList", "window2" );
		availableView = (TreeView) GladeParseUtil.grabWidget( "availableList", "window2" );
		
		// Append columns to views
		activeColumn = activeView.appendColumn();
		availableColumn = availableView.appendColumn();
		
		// Create lists for each view
		activeList = new ArrayList<String>();		
		availableList = new ArrayList<String>();
		
		// Build and set models
		activeModel = Utils.buildListModel( activeList, activeColumn, activeHeader );
		availableModel = Utils.buildListModel( availableList, availableColumn, availableHeader );
		activeView.setModel( activeModel );
		availableView.setModel( availableModel );
	}
	
	// Call this function in order to modify and update the popup
	public static void updatePopup( String header, ArrayList<String> active, ArrayList<String> available ) {
		
		// Update headers based on what's passed in
		popupWin.setTitle( "Adding " + header );
		activeColumn.setTitle( header );
		availableColumn.setTitle( header );
	
		//activeModel.setValue( row, activeHeader, "hey" );
		active.add("hey");
		Utils.updateModel(active, activeModel, activeColumn, activeHeader);
		
	}
	
}
